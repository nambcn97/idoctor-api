package com.fpt.idoctor.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fpt.idoctor.api.request.SendEmergencyRequest;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.api.response.SendEmergencyResponse;
import com.fpt.idoctor.bean.EmergencyBean;
import com.fpt.idoctor.bean.UserBean;
import com.fpt.idoctor.common.constant.ModelConstants.EmergencyStatus;
import com.fpt.idoctor.common.constant.ModelConstants.InitRoleId;
import com.fpt.idoctor.common.constant.ModelConstants.RoleEnum;
import com.fpt.idoctor.common.constant.ModelConstants.UserStatus;
import com.fpt.idoctor.model.EmergencyCall;
import com.fpt.idoctor.model.Location;
import com.fpt.idoctor.model.Role;
import com.fpt.idoctor.model.User;
import com.fpt.idoctor.repository.EmergencyCallRepository;
import com.fpt.idoctor.repository.LocationRepository;
import com.fpt.idoctor.repository.RoleRepository;
import com.fpt.idoctor.repository.UserRepository;
import com.fpt.idoctor.security.SecurityUtils;
import com.fpt.idoctor.service.EmergencyService;
import com.fpt.idoctor.service.FirebaseService;
@Service
@Transactional(rollbackOn = Exception.class)
public class EmergencyServiceImpl implements EmergencyService {
	@Autowired
	private FirebaseService firebaseService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private EmergencyCallRepository emergencyCallRepository;
	@Override
	public BaseResponse sendEmergencyToDoctor(SendEmergencyRequest req) {
		BaseResponse baseRes = new BaseResponse();
		SendEmergencyResponse res = new SendEmergencyResponse();
		Date date = new Date();
		Long doctorId = null;
		try {
			JSONObject json = new JSONObject();

			JSONObject data = new JSONObject();
			User user = null;

			if (req.getLoggedIn()) {
				user = SecurityUtils.getCurrentUser();
				if (user.getRole().getCode().equals(RoleEnum.DOCTOR))
					doctorId = user.getId();
			} else {
				// anonymous emergency
				Location location = new Location(null, req.getLat(),
						req.getLng());
				locationRepository.addLocation(location);
				user = new User();
				user.setLocation(location);
				user.setDeviceId(req.getDeviceId());
				user.setPhone(req.getPhone());
				Role anonymous = roleRepository.getRole(InitRoleId.ANONYMOUS);
				user.setRole(anonymous);
				userRepository.addUser(user);
			}
			UserBean userBean = user.convertToBean();
			JSONObject userObj = new JSONObject(userBean);
			data.put("fromUser", userObj);
			data.put("type", "p2d");

			json.put("data", data);

			JSONObject notification = new JSONObject();
			notification.put("title", "iDoctor - Emergency");
			notification.put("body", "Bạn có lời gọi cấp cứu từ bệnh nhân");
			notification.put("icon", "ic_noti_emergency");
			notification.put("click_action",
					"vn.edu.fpt.idoctor.ui.PatientRequestMapsActivity");
			json.put("notification", notification);

			JSONArray registration_ids = new JSONArray();
			// get all doctor available
			List<User> availDoctors = userRepository.findDoctor(req.getLat(),
					req.getLng(), req.getRadius(),
					new String[]{UserStatus.ONLINE.getValue()}, doctorId);

			for (User doctor : availDoctors) {
				registration_ids.put(doctor.getDeviceId());

			}
			json.put("registration_ids", registration_ids);
			// send noti using firebase
			ResponseEntity<String> sendNotiResponse = firebaseService
					.sendNotification(json);
			if (sendNotiResponse.getStatusCode() != HttpStatus.OK) {
				baseRes.buildFailed();
				baseRes.setResultCode(sendNotiResponse.getStatusCodeValue());
				baseRes.setResultMsg(sendNotiResponse.getBody());
				return baseRes;
			}
			// add emergency call to db
			// init list emergency bean
			List<EmergencyBean> emergencyBeans = new ArrayList<>();
			for (User doctor : availDoctors) {
				EmergencyCall call = new EmergencyCall();
				call.setFromUser(user);
				call.setToUser(doctor);
				call.setTime(date);
				call.setStatus(EmergencyStatus.NEW.getValue());
				emergencyCallRepository.addEmergencyCall(call);
				emergencyBeans.add(call.convertToBean());
			}
			res.setEmergencies(emergencyBeans);
			res.buildSuccessful();

		} catch (Exception e) {
			baseRes.buildFailed();
			baseRes.setResultMsg(e.getMessage());
			e.printStackTrace();
			return baseRes;
		}
		return res;
	}

}
