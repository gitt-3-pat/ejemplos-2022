package com.ecommerce.market.webapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@Table("IMAGE_RESOURCE")
public class ImageResource {

	@Id
	private Long id;

	private byte[] bytes;

	private String fileName;

}
