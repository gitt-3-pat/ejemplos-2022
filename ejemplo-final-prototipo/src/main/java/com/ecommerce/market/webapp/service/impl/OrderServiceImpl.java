package com.ecommerce.market.webapp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.market.webapp.dto.OrderDTO;
import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.model.Order;
import com.ecommerce.market.webapp.model.Item.ItemState;
import com.ecommerce.market.webapp.model.Order.DeliveryStatus;
import com.ecommerce.market.webapp.model.Order.OrderState;
import com.ecommerce.market.webapp.repository.ItemRepository;
import com.ecommerce.market.webapp.repository.OrderRepository;
import com.ecommerce.market.webapp.service.OrderService;
import com.ecommerce.market.webapp.service.UserService;
import com.ecommerce.market.webapp.utils.SecurityUtils;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public void placeOrder(OrderDTO order) {
		Order o = new Order();
		o.setContactInfo(order.getContactInfo());
		o.setDeliveryStatus(DeliveryStatus.NO_ENVIADO);
		o.setOrderState(OrderState.PENDIENTE_CONFIRMACION);
		o.setUser(userService.getUser(SecurityUtils.getCurrentUser()));
		o.setPayed(true);
		o.setPayedAt(new Date());
		final Item i = itemRepository.findById(order.getItem().getId()).orElse(null);
		i.setItemState(ItemState.PENDING);
		itemRepository.save(i);
		o.addItem(order.getItem());
		o = orderRepository.save(o);

	}

	@Override
	public List<Order> getOrdersForCurrentUser() {
		return orderRepository.findByUser(SecurityUtils.getCurrentUser());
	}

	@Override
	public Order getOrder(Long orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	@Override
	public void completeShippingDetails(Order order, Long itemId) {
		final Item i = itemRepository.findById(itemId).orElse(new Item());
		i.setItemState(ItemState.CONFIRMED);
		order.setOrderState(OrderState.CONFIRMADO);
		order.setDeliveryReference(UUID.randomUUID().toString());
		order.setDeliveryStatus(DeliveryStatus.ENVIADO);
		orderRepository.save(order);
		itemRepository.save(i);

	}

	@Override
	public List<Order> getOrdersToAssignLabels() {
		return orderRepository.findOrdersWithouShippingLabels();
	}

	@Override
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public Long getOrderIdFromItemId(Long itemId) {
		return orderRepository.findIdByItemId(itemId);
	}

}
