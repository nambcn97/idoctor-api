package com.fpt.idoctor.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fpt.idoctor.common.util.PasswordEncryptUtil;


public class Base64PasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return PasswordEncryptUtil.encryptPassword(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return PasswordEncryptUtil.encryptPassword(rawPassword.toString()).equals(encodedPassword);
	}

}
