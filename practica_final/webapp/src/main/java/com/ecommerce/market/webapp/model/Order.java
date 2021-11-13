package com.ecommerce.market.webapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table("ORDERS")
@EqualsAndHashCode(exclude = { "items" })
@Data
public class Order {

	public enum DeliveryStatus {
		ENVIADO("Enviado"), NO_ENVIADO("No enviado");
		private String readValue;

		public String getReadValue() {
			return this.readValue;
		}

		private DeliveryStatus(String readValue) {
			this.readValue = readValue;
		}
	}

	public enum OrderState {
		PENDIENTE_CONFIRMACION("Pendiente de confirmación"), CONFIRMADO("Confirmado"), CANCELADO("Cancelado");
		private String readValue;

		public String getReadValue() {
			return this.readValue;
		}

		private OrderState(String readValue) {
			this.readValue = readValue;
		}
	}

	public enum UserState {
		ACEPTADO("Aceptado"), DEVUELTO("Devuelto");
		private String readValue;

		public String getReadValue() {
			return this.readValue;
		}

		private UserState(String readValue) {
			this.readValue = readValue;
		}
	}

	@Id
	private Long id;

	@MappedCollection(idColumn = "ORDER_ID")
	private Set<OrderItem> items = new HashSet<OrderItem>();

	private OrderState orderState;

	private DeliveryStatus deliveryStatus;

	private String deliveryReference;

	private UserState userState;

	@MappedCollection(idColumn = "ORDER_ID")
	private OrderUser user;

	private boolean payed;

	@LastModifiedDate
	@Getter
	@Setter
	private Date payedAt;

	@Embedded(onEmpty = OnEmpty.USE_EMPTY)
	private ContactInfo contactInfo;

	@Embedded(onEmpty = OnEmpty.USE_EMPTY)
	private OrderInfo orderInfo;

	public void addItem(Item item) {
		this.items.add(new OrderItem(item.getId()));
	}

	public void removeItem(Long id) {
		this.items.removeIf(i -> i.getItemId().equals(id));
	}

	public void setUser(User user) {
		this.user = new OrderUser(user.getEmail());
	}
}
