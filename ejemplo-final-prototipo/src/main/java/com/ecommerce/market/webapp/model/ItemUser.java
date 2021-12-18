package com.ecommerce.market.webapp.model;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table("ITEM_USER")
@Data
@AllArgsConstructor
public class ItemUser {

	private String userEmail;

}
