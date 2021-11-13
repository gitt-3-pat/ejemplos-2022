package com.ecommerce.market.webapp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

public class UserPrincipal extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	private final String shortName;
	@Getter
	private final String name;

	public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String fullName) {
		super(username, password, authorities);
		this.shortName = fullName.split(" ")[0];
		this.name = fullName;
	}

}
