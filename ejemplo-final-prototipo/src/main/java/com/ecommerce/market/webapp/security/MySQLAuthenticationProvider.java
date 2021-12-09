package com.ecommerce.market.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.market.webapp.model.User;
import com.ecommerce.market.webapp.model.User.Provider;
import com.ecommerce.market.webapp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MySQLAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsManager userDetailsManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final User user = this.userService.getUser(authentication.getName());
		if (user != null && authentication.getCredentials() instanceof String) {
			if (passwordEncoder.matches((String) authentication.getCredentials(), user.getPassword())) {
				final UserDetails details = userDetailsManager.loadUserByUsername(authentication.getName());
				return new UsernamePasswordAuthenticationToken(details, details.getPassword(),
						details.getAuthorities());
			} else {
				if (!user.getProvider().equals(Provider.LOCAL)) {
					log.error("User {} belongs to social media provider {}", user.getEmail(),
							user.getProvider().name());
					throw new BadCredentialsException("Su cuenta está registrada con " + user.getProvider().name()
							+ ". Inicie sesión con dicha red social.");
				}
				log.error("Password {} of user {} does not match", authentication.getCredentials(),
						authentication.getName());
				throw new BadCredentialsException("Usuario o contraseña incorrectos.");
			}
		} else {
			log.error("User {} does not exist", authentication.getName());
			throw new BadCredentialsException("Usuario o contraseña incorrectos.");
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
