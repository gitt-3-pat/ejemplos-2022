package com.ecommerce.market.webapp.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	public static String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public static String getRoleFromCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().toString();
	}

}
