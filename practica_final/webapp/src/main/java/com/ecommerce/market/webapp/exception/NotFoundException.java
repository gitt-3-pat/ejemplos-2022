package com.ecommerce.market.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "image not found")
public class NotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
