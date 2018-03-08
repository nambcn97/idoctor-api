package com.fpt.idoctor.service;

import java.util.List;

import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.model.User;

public interface UserService {

	public List<User> getAllUser() throws Exception;

	public void addUser(User user) throws Exception;

	public void updateUser(User user) throws Exception;

	public void deleteUser(Long id) throws Exception;

	public BaseResponse getUserInfo() throws Exception;

}
