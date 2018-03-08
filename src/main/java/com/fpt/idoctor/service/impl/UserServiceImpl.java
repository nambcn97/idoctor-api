package com.fpt.idoctor.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.api.response.GetUserInfoResponse;
import com.fpt.idoctor.model.User;
import com.fpt.idoctor.repository.UserRepository;
import com.fpt.idoctor.security.SecurityUtils;
import com.fpt.idoctor.service.UserService;

@Transactional(rollbackOn = Exception.class)
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

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

}
