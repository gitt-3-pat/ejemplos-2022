package info.jab.microservices.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("PURCHASE_ORDER")
public class PurchaseOrder {

	private @Id
	Long id;
	@Column("SHIPPING_ADDRESS")
	private String shippingAddress;
	// Child Entity
	@MappedCollection(keyColumn = "PURCHASE_ORDER", idColumn = "PURCHASE_ORDER")
	private Set<OrderItem> items = new HashSet<>();

	public void addItem(int quantity, String product) {
		items.add(createOrderItem(quantity, product));
	}

	private OrderItem createOrderItem(int quantity, String product) {

		final OrderItem item = new OrderItem();
		item.product = product;
		item.quantity = quantity;
		return item;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
}
