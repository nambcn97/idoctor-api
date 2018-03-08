package com.fpt.idoctor.common.util;

import java.util.Base64;

public class PasswordEncryptUtil {
	public static String encryptPassword(String password) {
		byte[] result = Base64.getEncoder().encode(password.getBytes());
		return new String(result);
	}

	public static String decryptPassword(String encryptedPassword) {
		byte[] result = Base64.getDecoder().decode(encryptedPassword.getBytes());
		return new String(result);
	}
}
