package com.ecommerce.market.webapp.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.market.webapp.model.ContactInfo;
import com.ecommerce.market.webapp.model.User;
import com.ecommerce.market.webapp.model.User.Provider;
import com.ecommerce.market.webapp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserDetailsManager implements UserDetailsService {

	@Value("${admin.user-list:}")
	private String admins;

	@Autowired
	private UserService userService;

	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;

	private Collection<String> adminUsers;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userService.getUser(username);
		final String role = adminUsers.contains(username) ? "ROLE_ADMIN" : "ROLE_USER";
		log.info("User {} has role {}", username, role);
		return new UserPrincipal(username, "", Arrays.asList(new SimpleGrantedAuthority(role)), user.getFullName());
	}

	@PostConstruct
	public void createDefaultUsers() {
		adminUsers = List.of(admins.split(","));
		if (userService.findAll().isEmpty()) {
			adminUsers.forEach(s -> {
				final User user = new User();
				user.setEmail(s);
				user.setFullName(s.substring(0, s.indexOf("@")));
				user.setPassword(passwordEncoder.encode("Admin2021!"));
				user.setProvider(Provider.LOCAL);
				final ContactInfo info = new ContactInfo();
				info.setAddress("N/A");
				info.setCountry("SPAIN");
				info.setLocalty("Madrid");
				info.setPhone("1111111");
				info.setDni("555555B");
				info.setPostalCode("28080");
				info.setProvince("Madrid");
				userService.create(user);
			});
		}
	}

}
