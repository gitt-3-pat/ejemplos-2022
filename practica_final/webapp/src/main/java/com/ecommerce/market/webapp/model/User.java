package com.ecommerce.market.webapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table("USER")
@EqualsAndHashCode
public class User {

	public enum Provider {
		GOOGLE, LOCAL
	}

	private String password;

	private String fullName;

	@Id
	private String email;

	@Embedded(onEmpty = OnEmpty.USE_EMPTY)
	private ContactInfo contactInfo;

	private Provider provider;

	// TO-DO Datos bancarios
}
