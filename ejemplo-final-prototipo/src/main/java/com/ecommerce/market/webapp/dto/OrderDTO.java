package com.ecommerce.market.webapp.dto;

import com.ecommerce.market.webapp.model.ContactInfo;
import com.ecommerce.market.webapp.model.Item;

import lombok.Data;

@Data
public class OrderDTO {

	private ContactInfo contactInfo;
	private Item item;
}
