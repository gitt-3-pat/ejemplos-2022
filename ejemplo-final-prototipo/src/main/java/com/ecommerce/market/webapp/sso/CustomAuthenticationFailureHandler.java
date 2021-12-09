package com.ecommerce.market.webapp.sso;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		if (exception instanceof CustomOauth2AuthenticationException) {
			final CustomOauth2AuthenticationException ex = (CustomOauth2AuthenticationException) exception;
			response.sendRedirect("/register?email=" + ex.getEmail() + "&name="
					+ URLEncoder.encode(ex.getName(), StandardCharsets.UTF_8) + "&provider=" + ex.getProvider());
		} else if (exception instanceof BadCredentialsException) {
			response.sendRedirect("/login?error=" + URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8));
		} else {
			response.sendRedirect("/home");
		}
	}

}
