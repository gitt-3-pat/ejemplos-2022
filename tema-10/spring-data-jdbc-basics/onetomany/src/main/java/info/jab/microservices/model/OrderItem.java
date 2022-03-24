package info.jab.microservices.model;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("ORDER_ITEM")
public class OrderItem {
	int quantity;
	String product;
}