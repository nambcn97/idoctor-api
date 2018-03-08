package com.fpt.idoctor.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.idoctor.model.User;
import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.common.exception.ResponseWriteException;
import com.fpt.idoctor.common.exception.UserNotFoundException;

public final class SecurityUtils {

	/** Logger */
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

	/** Object mapper */
	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Get the login of the current user Throw UserNotFoundException when user can't
	 * be authenticated
	 * 
	 * @return userName String
	 * @throws UserNotFoundException
	 */
	public static User getCurrentUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();

		// In case user can't be authenticated
		if (authentication == null) {
			throw new UserNotFoundException();
		}

		User userName = null;

		if (authentication.getPrincipal() instanceof UserDetails) {
			UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
			userName = userPrincipal.getUser();
		}
		// else if (authentication.getPrincipal() instanceof String) {
		// userName = (String) authentication.getPrincipal();
		// }
		return userName;
	}

	/**
	 * Return error response
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param exception
	 *            Exception
	 * @param status
	 *            int
	 * @param message
	 *            String
	 * @throws IOException
	 *             IOException
	 */
	public static void sendError(HttpServletResponse response, Exception exception, int status, String message) {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(status);
		try (PrintWriter writer = response.getWriter()) {
			writer.write(mapper.writeValueAsString(new BaseResponse(status, message)));
		} catch (IOException ex) {
			if (logger.isErrorEnabled()) {
				logger.error(ex.getMessage(), ex);
			}
			throw new ResponseWriteException(ex);
		}
	}

	/**
	 * Return success response
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param status
	 *            status
	 * @param object
	 *            Object
	 * @throws IOException
	 *             IOException
	 */
	public static void sendResponse(HttpServletResponse response, int status, Object object, String token) {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter writer = response.getWriter()) {
			if (object instanceof Authentication) {
				Authentication authentication = (Authentication) object;
				UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
				Map<String, Object> responseResult = new HashMap<String, Object>();
				responseResult.put("isAuthenticated", authentication.isAuthenticated());
				responseResult.put("fullName", principal.getUser().getFullname());
				responseResult.put("details", authentication.getCredentials());
				responseResult.put("token", token);
				// User 1 - 1 Role
				Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
				SimpleGrantedAuthority authority = (SimpleGrantedAuthority) authorities.toArray()[0];
				String role = authority.toString();
				responseResult.put("role", role);
				writer.write(mapper.writeValueAsString(responseResult));
			} else {
				writer.write(mapper.writeValueAsString(object));
			}
		} catch (IOException ex) {
			if (logger.isErrorEnabled()) {
				logger.error(ex.getMessage(), ex);
			}
			throw new ResponseWriteException(ex);
		}
		response.setStatus(status);
	}

}
