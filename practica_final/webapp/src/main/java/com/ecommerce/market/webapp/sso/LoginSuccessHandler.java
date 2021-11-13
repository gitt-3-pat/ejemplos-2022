package com.ecommerce.market.webapp.sso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ecommerce.market.webapp.model.Wishlist;
import com.ecommerce.market.webapp.service.UserService;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		final Wishlist list = userService.getWishlist(request);
		if (list != null) {
			request.getSession().setAttribute("wishlist", list);
		}
		super.onAuthenticationSuccess(request, response, authentication);

	}

}
