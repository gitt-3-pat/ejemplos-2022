package edu.icai.gittpat.service.impl;

import edu.icai.gittpat.repository.OrdersRepository;
import edu.icai.gittpat.service.OrderService;
import edu.icai.gittpat.service.dto.OrderDTO;
import edu.icai.gittpat.service.dto.OrderProjectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	private OrdersRepository ordersRepository;

	@Override
	public List<OrderDTO> getOrders() {
		return StreamSupport.stream(ordersRepository.findAll().spliterator(), false)
				.map(obj -> new OrderDTO(
						obj.getId(),
						obj.getCustomerId(),
						obj.getDate()))
				.toList();
	}

	//INNER-JOIN
	@Override
	public List<OrderProjectionDTO> getOrdersWithCustomer() {

		String query=
				"""
    			SELECT ORDERS.ID, CUSTOMERS.CONTACT_NAME, CUSTOMERS.CUSTOMER_NAME, CUSTOMERS.COUNTRY, ORDERS.ORDER_DATE
				FROM ORDERS
				INNER JOIN CUSTOMERS ON ORDERS.CUSTOMER_ID=CUSTOMERS.ID;
				""";

		List<OrderProjectionDTO> orderList = jdbcTemplate.query(
				query,
				(rs, rowNum) ->
						new OrderProjectionDTO(
								rs.getLong("ID"),
								rs.getString("CONTACT_NAME"),
								rs.getString("CUSTOMER_NAME"),
								rs.getString("COUNTRY"),
								(rs.getTimestamp("ORDER_DATE")!=null) ? rs.getTimestamp("ORDER_DATE").toLocalDateTime() : null
						)
		);

		return orderList;
	}

	//LEFT JOIN
	@Override
	public List<OrderProjectionDTO> getAllOrders() {

		String query=
				"""
    			SELECT ORDERS.ID, CUSTOMERS.CONTACT_NAME, CUSTOMERS.CUSTOMER_NAME, CUSTOMERS.COUNTRY, ORDERS.ORDER_DATE
				FROM ORDERS
				LEFT JOIN CUSTOMERS ON ORDERS.CUSTOMER_ID=CUSTOMERS.ID;
				""";

		List<OrderProjectionDTO> orderList = jdbcTemplate.query(
				query,
				(rs, rowNum) ->
						new OrderProjectionDTO(
								rs.getLong("ID"),
								rs.getString("CONTACT_NAME"),
								rs.getString("CUSTOMER_NAME"),
								rs.getString("COUNTRY"),
								(rs.getTimestamp("ORDER_DATE")!=null) ? rs.getTimestamp("ORDER_DATE").toLocalDateTime() : null
						)
		);

		return orderList;
	}

	//LEFT JOIN
	@Override
	public List<OrderProjectionDTO> getAllOrders2() {

		String query=
				"""
    			SELECT ORDERS.ID, CUSTOMERS.CONTACT_NAME, CUSTOMERS.CUSTOMER_NAME, CUSTOMERS.COUNTRY, ORDERS.ORDER_DATE
				FROM ORDERS
				LEFT OUTER JOIN CUSTOMERS ON ORDERS.CUSTOMER_ID=CUSTOMERS.ID;
				""";

		//CASE 1:
		//LEFT JOIN CUSTOMERS ON ORDERS.CUSTOMER_ID=CUSTOMERS.ID;

		//CASE 2:
		//RIGHT JOIN CUSTOMERS ON ORDERS.CUSTOMER_ID=CUSTOMERS.ID;


		List<OrderProjectionDTO> orderList = jdbcTemplate.query(
				query,
				(rs, rowNum) ->
						new OrderProjectionDTO(
								rs.getLong("ID"),
								rs.getString("CONTACT_NAME"),
								rs.getString("CUSTOMER_NAME"),
								rs.getString("COUNTRY"),
								(rs.getTimestamp("ORDER_DATE")!=null) ? rs.getTimestamp("ORDER_DATE").toLocalDateTime() : null
						)
		);

		return orderList;
	}

}
