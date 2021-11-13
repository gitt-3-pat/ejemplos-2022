package com.ecommerce.market.webapp.model;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table("ORDER_USER")
@Data
@AllArgsConstructor
public class OrderUser {

	private String userEmail;

}
