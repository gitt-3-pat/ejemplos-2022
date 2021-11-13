package com.ecommerce.market.webapp.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table("ITEM")
@Data
@EqualsAndHashCode(exclude = { "images" })
@ToString(exclude = { "images" })
public class Item {

	public enum Type {
		ARCHIVO_DIGITAL("Archivos digitales"), CAMISETA("Camisetas"), PANTALON("Pantalones"), MISCELANEA("Misceláneos"),
		ABRIGO("Abrigos"), ZAPATO("Zapatos"), VESTIDO("Vestidos"),;
		private String readValue;

		public String getReadValue() {
			return this.readValue;
		}

		private Type(String readValue) {
			this.readValue = readValue;
		}

	}

	public enum ItemState {
		AVAILABLE("Disponible"), PENDING("Pendiente"), PRE_CONFIRMED("Pendiente de generar etiqueta"),
		CONFIRMED("Confirmado"), SOLD("Vendido"), CANCELADO("Cancelado");

		private String readValue;

		public String getReadValue() {
			return this.readValue;
		}

		private ItemState(String readValue) {
			this.readValue = readValue;
		}
	}

	@Id
	private Long id;

	private ItemState itemState;

	private String brand;

	private Integer itemYear;

	private Integer itemSize;

	private String material;

	private String description;

	private Double price;

	@MappedCollection(idColumn = "ITEM_ID")
	private Set<ImageResource> images = new HashSet<ImageResource>();

	private int internalRating = 0;

	private String title;

	private Type type;

	@MappedCollection(idColumn = "ITEM_ID")
	private ItemUser user;

	public void setUser(User user) {
		this.user = new ItemUser(user.getEmail());
	}
}
