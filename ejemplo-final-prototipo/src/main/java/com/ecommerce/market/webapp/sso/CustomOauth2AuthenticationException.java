package com.ecommerce.market.webapp.sso;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

import com.ecommerce.market.webapp.model.User.Provider;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomOauth2AuthenticationException extends OAuth2AuthenticationException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String name;
	private Provider provider;

	public CustomOauth2AuthenticationException(OAuth2Error error, String email, String name, Provider provider) {
		super(error);
		this.email = email;
		this.name = name;
		this.provider = provider;
	}

}
