package com.ecommerce.market.webapp.configuration;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecommerce.market.webapp.sso.CustomAuthenticationFailureHandler;
import com.ecommerce.market.webapp.sso.CustomOidcUserService;
import com.ecommerce.market.webapp.sso.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomOidcUserService oidcUserService;
	@Autowired
	private LoginSuccessHandler successHandler;
	@Autowired
	private AuthenticationProvider authProvider;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/publish", "/wishlist**", "/wishlist/**", "/my-buyouts", "/my-publications",
						"/shopping-cart", "/non-approved", "/my-account", "/checkout", "/checkout/**")
				.authenticated().and().authorizeRequests().anyRequest().permitAll().and().formLogin()
				.loginPage("/login").loginProcessingUrl("/login").successHandler(successHandler)
				.failureHandler(new CustomAuthenticationFailureHandler()).and().logout().logoutSuccessUrl("/home")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").and().rememberMe().key("Br1d3M4Rk3t!")
				.userDetailsService(userDetailsService).and().csrf().disable();
		http.oauth2Login().loginPage("/login").userInfoEndpoint().oidcUserService(oidcUserService).and()
				.failureHandler(new CustomAuthenticationFailureHandler()).successHandler(successHandler).and()
				.rememberMe().key("Br1d3M4Rk3t!");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

}
