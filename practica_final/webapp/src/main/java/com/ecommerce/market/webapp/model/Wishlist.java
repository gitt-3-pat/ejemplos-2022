package com.ecommerce.market.webapp.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Wishlist {

	private Set<Item> items = new HashSet<Item>();

}
