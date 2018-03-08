package com.fpt.idoctor.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import com.fpt.idoctor.api.response.BaseResponse;

public class AppAccessDeniedHandler extends OAuth2AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException)
			throws IOException, ServletException {
		
		super.handle(request, response, authException);
	}

	
	
}
