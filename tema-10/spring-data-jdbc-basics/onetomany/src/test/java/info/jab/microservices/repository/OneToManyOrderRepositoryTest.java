package info.jab.microservices.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import info.jab.microservices.model.PurchaseOrder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class OneToManyOrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	@Test
	// ONE TO MANY TEST WITH AGGREGATE
	public void whenPurchaseOrderIscreated_OrderItemsAreCreatedToo() {
		final PurchaseOrder order = new PurchaseOrder();

		order.addItem(4, "Captain Future Comet Lego set");
		order.addItem(2, "Cute blue angler fish plush toy");
		order.setShippingAddress("N/A");

		final PurchaseOrder saved = orderRepository.save(order);
		assertThat(orderRepository.count(), is(equalTo(1L)));
		assertThat(orderRepository.countItems(), is(equalTo(2)));

		orderRepository.delete(saved);
		assertThat(orderRepository.count(), is(equalTo(0L)));
		assertThat(orderRepository.countItems(), is(equalTo(0)));

	}

	@Transactional
	@Test
	// ONE TO MANY TEST WITH AGGREGATE
	public void whenPurchaseOrderIscreated_ThenIsRetrievedByAddress() {
		final PurchaseOrder order = new PurchaseOrder();

		order.addItem(4, "Captain Future Comet Lego set");
		order.addItem(2, "Cute blue angler fish plush toy");
		order.setShippingAddress("N/A");

		final PurchaseOrder saved = orderRepository.save(order);

		final List<PurchaseOrder> orders = orderRepository.findByShippingAddress("N/A");
		assertTrue(orders.size() == 1);
		orderRepository.delete(saved);

	}

}
