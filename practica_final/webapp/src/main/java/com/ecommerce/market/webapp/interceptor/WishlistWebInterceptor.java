package com.ecommerce.market.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ecommerce.market.webapp.service.ItemService;
import com.ecommerce.market.webapp.utils.SecurityUtils;

@Component
public class WishlistWebInterceptor implements HandlerInterceptor {

	@Autowired
	private ItemService itemService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!request.getRequestURI().startsWith("/css") && !request.getRequestURI().startsWith("/js")
				&& !request.getRequestURI().startsWith("/fonts") && !request.getRequestURI().startsWith("/images")) {
			if (request.getSession(false) != null && SecurityContextHolder.getContext().getAuthentication() != null
					&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)
					&& request.getSession().getAttribute("wishlist") == null) {
				itemService.setWishlistForUser(request, SecurityUtils.getCurrentUser());
			}
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
