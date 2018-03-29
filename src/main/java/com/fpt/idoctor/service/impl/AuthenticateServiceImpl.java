package com.fpt.idoctor.service.impl;

import static com.fpt.idoctor.common.constant.MessageContants.PASSWORD_INVALID;
import static com.fpt.idoctor.common.constant.MessageContants.USERNAME_EXIST;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.fpt.idoctor.api.request.SignUpUserRequest;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.common.constant.ApiConstants;
import com.fpt.idoctor.common.constant.ModelConstants.InitRoleId;
import com.fpt.idoctor.common.constant.ModelConstants.UserStatus;
import com.fpt.idoctor.common.util.PasswordEncryptUtil;
import com.fpt.idoctor.model.Location;
import com.fpt.idoctor.model.User;
import com.fpt.idoctor.repository.LocationRepository;
import com.fpt.idoctor.repository.RoleRepository;
import com.fpt.idoctor.repository.SpecialtyRepository;
import com.fpt.idoctor.repository.TokenSessionRepository;
import com.fpt.idoctor.repository.UserRepository;
import com.fpt.idoctor.security.SecurityUtils;
import com.fpt.idoctor.service.AuthenticateService;

@Service
@Transactional(rollbackOn = Exception.class)
public class AuthenticateServiceImpl implements AuthenticateService {

	@Autowired
	TokenSessionRepository tokenSessionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private TokenStore tokenStore;
	@Autowired
	SpecialtyRepository specialtyRepository;
	@Autowired
	private LocationRepository locationRepository;

	@Override
	public BaseResponse signUpUser(SignUpUserRequest req) throws Exception {
		BaseResponse res = new BaseResponse();
		if (userRepository.existUser(req.getUsername())) {
			res.buildFailed(ApiConstants.FAILED_CODE, USERNAME_EXIST);
			return res;
		}
		if (req.getPassword() == null || req.getPassword().isEmpty()) {
			res.buildFailed(ApiConstants.FAILED_CODE, PASSWORD_INVALID);
			return res;
		}
		// TODO: User photo in version 2
		// ImageUtil.saveImage(req.getPhoto(), req.getUsername());
		Location location = new Location(null, req.getLat(), req.getLng());
		locationRepository.addLocation(location);
		User user = new User();
		user.setLocation(location);
		user.setUsername(req.getUsername());
		user.setFullname(req.getFullname());
		user.setPassword(
				PasswordEncryptUtil.encryptPassword(req.getPassword()));
		user.setAddress(req.getAddress());
		user.setPhone(req.getPhone());
		user.setGender(req.getGender());
		user.setStatus(UserStatus.OFFLINE.getValue());
		if (req.getRoleId() == InitRoleId.DOCTOR) {
			user.setRole(roleRepository.getRole(InitRoleId.DOCTOR));
			user.setSpecialty(
					specialtyRepository.getSpecialtyById(req.getSpecialtyId()));
			user.setWorkAddress(req.getWorkAddress());
		} else {
			user.setRole(roleRepository.getRole(InitRoleId.USER));
		}

		userRepository.addUser(user);
		res.buildSuccessful();
		return res;
	}

	@Override
	public BaseResponse logout(String header) {
		BaseResponse baseResponse = new BaseResponse();
		if (header == null) {
			baseResponse.buildFailed();
			return baseResponse;
		}
		String tokenValue = header.replace("Bearer", "").trim();
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
		if (accessToken == null) {
			baseResponse.buildFailed();
			return baseResponse;
		}
		User user = SecurityUtils.getCurrentUser();
		user.setStatus(UserStatus.OFFLINE.getValue());
		userRepository.updateUser(user);
		tokenStore.removeAccessToken(accessToken);
		baseResponse.buildSuccessful();
		return baseResponse;
	}

}
