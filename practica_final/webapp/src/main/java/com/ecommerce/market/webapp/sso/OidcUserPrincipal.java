package com.ecommerce.market.webapp.sso;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import lombok.Getter;

public class OidcUserPrincipal extends DefaultOidcUser {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	private final String shortName;

	public OidcUserPrincipal(Collection<? extends GrantedAuthority> authorities, OidcIdToken idToken,
			OidcUserInfo userInfo, String nameAttributeKey, String fullName) {
		super(authorities, idToken, userInfo, nameAttributeKey);
		this.shortName = fullName.split(" ")[0];
	}

}
