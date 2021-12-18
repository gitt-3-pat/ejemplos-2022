package com.ecommerce.market.webapp.service;

import java.util.List;

import com.ecommerce.market.webapp.dto.OrderDTO;
import com.ecommerce.market.webapp.model.Order;

public interface OrderService {

	void placeOrder(OrderDTO order);

	List<Order> getOrdersForCurrentUser();

	Order getOrder(Long orderId);

	void completeShippingDetails(Order order, Long itemId);

	List<Order> getOrdersToAssignLabels();

	void updateOrder(Order order);

	Long getOrderIdFromItemId(Long itemId);

}
