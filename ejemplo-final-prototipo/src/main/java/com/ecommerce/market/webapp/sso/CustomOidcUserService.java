package com.ecommerce.market.webapp.sso;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ecommerce.market.webapp.model.User;
import com.ecommerce.market.webapp.model.User.Provider;
import com.ecommerce.market.webapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomOidcUserService extends OidcUserService {

	@Autowired
	private UserRepository userRepository;

	@Value("${admin.user-list:}")
	private String admins;

	private Collection<String> adminUsers;

	@PostConstruct
	public void setAdminList() {
		adminUsers = List.of(admins.split(","));
	}

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

		Assert.notNull(userRequest, "userRequest cannot be null");
		final String email = (String) userRequest.getIdToken().getClaims().get("email");
		final User user = userRepository.findByEmail(email);
		if (user == null) {
			final String name = (String) userRequest.getIdToken().getClaims().get("name");
			throw new CustomOauth2AuthenticationException(new OAuth2Error("403"), email, name,
					Provider.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase()));
		} else {
			return getUser(userRequest, null, user);
		}
	}

	private OidcUser getUser(OidcUserRequest userRequest, OidcUserInfo userInfo, User user) {
		final String role = adminUsers.contains(user.getEmail()) ? "ROLE_ADMIN" : "ROLE_USER";
		log.info("User {} has role {}", user.getEmail(), role);
		final ProviderDetails providerDetails = userRequest.getClientRegistration().getProviderDetails();
		final String userNameAttributeName = providerDetails.getUserInfoEndpoint().getUserNameAttributeName();
		if (StringUtils.hasText(userNameAttributeName)) {
			return new OidcUserPrincipal(Arrays.asList(new SimpleGrantedAuthority(role)), userRequest.getIdToken(),
					userInfo, "email", user.getFullName());
		}
		return new DefaultOidcUser(Arrays.asList(new SimpleGrantedAuthority(role)), userRequest.getIdToken(), userInfo);
	}

}
