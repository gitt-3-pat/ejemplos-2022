package com.ecommerce.market.webapp.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.market.webapp.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

	@Query("select * from orders where orders.id in (select order_user.order_id from order_user where order_user.user_email= :email)")
	List<Order> findByUser(@Param("email") String email);

	@Query("SELECT * from orders o WHERE order.order_state='CONFIRMADO' AND order.shipping_label is NULL")
	List<Order> findOrdersWithouShippingLabels();

	@Query("SELECT orders.id from orders where orders.id in (select order_item.order_id from order_item where order_item.item_id= :itemId)")
	Long findIdByItemId(@Param("itemId") Long itemId);

	@Query("SELECT * from orders where orders.id in (select order_item.order_id from order_item where order_item.item_id= :itemId)")
	Order findByItemId(@Param("itemId") Long itemId);

	@Transactional
	@Modifying
	@Query("delete from order_item where order_item.item_id= :itemId")
	int deleteItemFromOrderByItemId(@Param("itemId") Long itemId);

}
