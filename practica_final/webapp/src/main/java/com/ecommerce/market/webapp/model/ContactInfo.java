package com.ecommerce.market.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {

	private String phone;

	private String address;

	private String postalCode;

	private String province;

	private String country;

	private String localty;

	private String dni;

	private String additionalInformation;

	private String name;

}
