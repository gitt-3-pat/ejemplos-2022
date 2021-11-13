package com.ecommerce.market.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ecommerce.market.webapp.interceptor.WishlistWebInterceptor;

@SpringBootApplication
public class WebappApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Autowired
	private WishlistWebInterceptor wishlistWebInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(wishlistWebInterceptor);

	}

}
