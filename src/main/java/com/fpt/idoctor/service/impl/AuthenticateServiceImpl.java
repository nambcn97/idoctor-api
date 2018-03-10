package com.fpt.idoctor.service.impl;

import static com.fpt.idoctor.common.constant.MessageContants.PASSWORD_INVALID;
import static com.fpt.idoctor.common.constant.MessageContants.USERNAME_EXIST;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.idoctor.api.request.SignUpUserRequest;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.common.constant.ApiConstants;
import com.fpt.idoctor.common.constant.ModelConstants.InitRoleId;
import com.fpt.idoctor.common.util.PasswordEncryptUtil;
import com.fpt.idoctor.model.User;
import com.fpt.idoctor.repository.RoleRepository;
import com.fpt.idoctor.repository.SpecialtyRepository;
import com.fpt.idoctor.repository.TokenSessionRepository;
import com.fpt.idoctor.repository.UserRepository;
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
	SpecialtyRepository specialtyRepository;

	// @Override
	// public LoginResponse authenticateLogin(LoginRequest request) throws
	// Exception {
	// LoginResponse res = new LoginResponse();
	// String username = request.getUsername();
	// String pass = request.getPassword();
	// if (username == null || username.isEmpty() || pass == null ||
	// pass.isEmpty()) {
	// res.setResultMsg(ApiConstants.AUTH_INVALID_USER_PASS);
	// res.setResultCode(ApiConstants.FAILED_CODE);
	// }
	// String encryptedPassword =
	// PasswordEncryptUtil.encryptPassword(request.getPassword());
	// User user = userRepository.authenticateUser(request.getUsername(),
	// encryptedPassword);
	// if (user != null) {
	// TokenSession tokenSession = createNewTokenSession(user);
	// tokenSessionRepository.addTokenSession(tokenSession);
	// res.setToken(tokenSession.getToken());
	// res.setRole(user.getRole().getCode());
	// res.setUserName(user.getUsername());
	// res.setResultCode(ApiConstants.SUCCESS_CODE);
	// res.setResultMsg(ApiConstants.SUCCESS_MSG);
	// } else {
	// res.setResultCode(ApiConstants.FAILED_CODE);
	// res.setResultMsg(ApiConstants.FAILED_MSG);
	// }
	// return res;
	// }
	//
	// @Override
	// public AuthTokenResponse authenticateToken(AuthTokenRequest request) {
	// AuthTokenResponse response = new AuthTokenResponse();
	// if (tokenSessionRepository.isExistToken(request.getToken())) {
	// response.buildSuccessful();
	// } else {
	// response.buildFailed(ApiConstants.FAILED_CODE,
	// ApiConstants.AUTH_INVALID_TOKEN);
	// }
	// return response;
	// }

	// private TokenSession createNewTokenSession(User user) {
	// TokenSession newToken = new TokenSession();
	// newToken.setToken(UUID.randomUUID().toString());
	// Date date = new Date();
	// newToken.setLastLogin(date);
	// newToken.setLastAction(date);
	// newToken.setUser(user);
	// return newToken;
	// }

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

		User user = new User();
		user.setPassword(
				PasswordEncryptUtil.encryptPassword(req.getPassword()));
		user.setAddress(req.getAddress());
		user.setPhone(req.getPhone());
		user.setGender(req.getGender());
		if (req.getRoleId() == InitRoleId.DOCTOR) {
			user.setRole(roleRepository.getDoctorRole());
			user.setSpecialty(
					specialtyRepository.getSpecialtyById(req.getSpecialtyId()));
		} else {
			user.setRole(roleRepository.getUserRole());
		}

		userRepository.addUser(user);
		res.buildSuccessful();
		return res;
	}

}
