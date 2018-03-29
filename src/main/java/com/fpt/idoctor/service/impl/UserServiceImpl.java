package com.fpt.idoctor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.idoctor.api.request.FindDoctorRequest;
import com.fpt.idoctor.api.request.UpdateDataRequest;
import com.fpt.idoctor.api.request.UpdateUserStatusRequest;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.api.response.FindDoctorResponse;
import com.fpt.idoctor.api.response.GetUserInfoResponse;
import com.fpt.idoctor.bean.UserBean;
import com.fpt.idoctor.common.constant.ModelConstants.RoleEnum;
import com.fpt.idoctor.common.constant.ModelConstants.UserStatus;
import com.fpt.idoctor.model.Location;
import com.fpt.idoctor.model.User;
import com.fpt.idoctor.repository.LocationRepository;
import com.fpt.idoctor.repository.UserRepository;
import com.fpt.idoctor.security.SecurityUtils;
import com.fpt.idoctor.service.UserService;

@Transactional(rollbackOn = Exception.class)
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public List<User> getAllUser() throws Exception {
		return userRepository.getAllUsers();
	}

	@Override
	public void addUser(User user) throws Exception {
		userRepository.addUser(user);
	}

	@Override
	public void updateUser(User user) throws Exception {
		userRepository.updateUser(user);
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		userRepository.deleteUser(id);
	}

	@Override
	public BaseResponse getUserInfo() throws Exception {
		User user = SecurityUtils.getCurrentUser();
		// UserBean userBean = new UserBean(user.getEmail(),
		// user.getFirstName(), user.getLastName(), user.getEmail(),
		// user.getPhoneNumber());
		GetUserInfoResponse response = new GetUserInfoResponse();
		// response.setMyInfo(userBean);
		// response.buildSuccessful();
		return response;
	}

	@Override
	public BaseResponse updateData(UpdateDataRequest req) throws Exception {
		User user = SecurityUtils.getCurrentUser();
		user.setDeviceId(req.getDeviceId());
		Location location = user.getLocation();

		location.setLatitude(req.getLat());
		location.setLongitude(req.getLng());
		locationRepository.updateLocation(location);
		user.setLocation(location);
		user.setStatus(UserStatus.ONLINE.getValue());
		userRepository.updateUser(user);
		BaseResponse res = new BaseResponse();
		res.buildSuccessful();
		return res;
	}

	@Override
	public BaseResponse findDoctor(FindDoctorRequest req) throws Exception {
		User currentUser = SecurityUtils.getCurrentUser();
		Long doctorId = null;
		if (currentUser.getRole().getCode().equals(RoleEnum.DOCTOR.getValue()))
			doctorId = currentUser.getId();
		FindDoctorResponse res = new FindDoctorResponse();
		List<User> doctors;
		if (req.getSymptomId() != null || req.getOthers() != null) {
			doctors = userRepository.findDoctorBySymptoms(req.getSymptomId(),
					req.getOthers());
		} else {
			doctors = userRepository.findDoctor(req.getLat(), req.getLng(),
					req.getRadius(),
					new String[]{UserStatus.ONLINE.getValue(),
							UserStatus.BUSY.getValue(),
							UserStatus.OFFLINE.getValue()},
					doctorId);
		}

		List<UserBean> doctorBeans = new ArrayList<>();
		for (User doctor : doctors) {
			UserBean bean = doctor.convertToBean();
			doctorBeans.add(bean);
		}
		res.setDoctors(doctorBeans);
		res.buildSuccessful();
		return res;
	}

	@Override
	public BaseResponse updateUserStatus(UpdateUserStatusRequest req) {
		User currentUser = SecurityUtils.getCurrentUser();
		if (req.getOnline()) {
			currentUser.setStatus(UserStatus.ONLINE.getValue());
			currentUser.getLocation().setLatitude(req.getLat());
			currentUser.getLocation().setLongitude(req.getLng());
			userRepository.updateUser(currentUser);
		} else if (req.getOffline()) {
			currentUser.setStatus(UserStatus.OFFLINE.getValue());
			userRepository.updateUser(currentUser);
		}
		BaseResponse response = new BaseResponse();
		response.buildSuccessful();
		return response;
	}

}
