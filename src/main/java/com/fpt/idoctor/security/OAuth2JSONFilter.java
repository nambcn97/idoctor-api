package com.fpt.idoctor.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OAuth2JSONFilter implements Filter {

	private final String APPLICATION_JSON = "application/json";
	private final String URL_LOGIN = "/oauth/token";
	private final String URL_REFRESH_TOKEN = "/auth/token";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequestWrapper reqWrapper = (HttpServletRequestWrapper) req;
		boolean iss = isLoginOrRefreshToken(reqWrapper);
		if (iss) {
			InputStream is = reqWrapper.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			byte[] JSON = buffer.toByteArray();

			HashMap<String, String> result = new ObjectMapper().readValue(JSON,
					HashMap.class);
			HashMap<String, String[]> r = new HashMap<>();
			for (String key : result.keySet()) {
				String[] val = new String[1];
				val[0] = result.get(key);
				r.put(key, val);
			}

			String[] val = new String[1];
			val[0] = reqWrapper.getMethod();
			// custom hook
			r.put("_method", val);
			if (!result.containsKey("refresh_token")) {
				r.put("grant_type", new String[]{"password"});
				r.put("username", new String[]{result.get("user")});
				r.put("password", new String[]{result.get("password")});
			} else {
				r.put("refresh_token",
						new String[]{result.get("refresh_token")});
				r.put("grant_type", new String[]{"refresh_token"});
			}
			HttpServletRequestWrapper newReq = new OAuthHttpServletRequestWrapper(
					(HttpServletRequest) req, r);
			chain.doFilter(newReq, res);
		} else {
			chain.doFilter(req, res);
		}
	}

	public boolean isLoginOrRefreshToken(HttpServletRequestWrapper reqWrapper) {
		return APPLICATION_JSON.equals(reqWrapper.getContentType())
				&& reqWrapper.getMethod().equals(HttpMethod.POST.toString())
				&& URL_LOGIN.equals(reqWrapper.getServletPath());
	}

	@Override
	public void destroy() {

	}

}
