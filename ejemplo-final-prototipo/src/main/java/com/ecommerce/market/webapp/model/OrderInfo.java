package com.ecommerce.market.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {

	private Integer width;
	private Integer height;
	private Integer lenght;
	private Float weight;
//	@Lob
	private byte[] shippingLabel;

	public String shippingMime;

}
