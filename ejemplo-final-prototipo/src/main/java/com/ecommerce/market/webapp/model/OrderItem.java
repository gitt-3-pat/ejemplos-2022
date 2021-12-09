package com.ecommerce.market.webapp.model;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Table("ORDER_ITEM")
@Data
@AllArgsConstructor
public class OrderItem {

	private Long itemId;

}
