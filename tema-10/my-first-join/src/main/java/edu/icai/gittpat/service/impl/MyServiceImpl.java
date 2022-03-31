package edu.icai.gittpat.service.impl;



import edu.icai.gittpat.dto.OrderDTO;
import edu.icai.gittpat.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MyServiceImpl implements MyService {

	@Autowired
    private JdbcTemplate jdbcTemplate;


	@Override
	public List<OrderDTO> getOrderswithCustomer() {
		
		String query="SELECT ORDERS.ID, CUSTOMERS.CONTACT_NAME, CUSTOMERS.CUSTOMER_NAME, CUSTOMERS.COUNTRY, ORDERS.ORDER_DATE "
				+ "FROM ORDERS "
				+ "INNER JOIN CUSTOMERS ON ORDERS.CUSTOMER_ID=CUSTOMERS.ID;";
		
		
		List<OrderDTO> orderList = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new OrderDTO(
                                rs.getLong("ID"),
                                rs.getString("CONTACT_NAME"),
                                rs.getString("CUSTOMER_NAME"),
                                rs.getString("COUNTRY"),
                                (rs.getTimestamp("ORDER_DATE")!=null) ? rs.getTimestamp("ORDER_DATE").toLocalDateTime() : null
                        )
        );
		
		
		return orderList;
	}


	@Override
	public List<OrderDTO> getAllOrders() {
		
		String query="SELECT ORDERS.ID, CUSTOMERS.CONTACT_NAME, CUSTOMERS.CUSTOMER_NAME, CUSTOMERS.COUNTRY, ORDERS.ORDER_DATE "
				+ "FROM ORDERS "
				+ "LEFT JOIN CUSTOMERS ON ORDERS.CUSTOMER_ID=CUSTOMERS.ID;";
		
		List<OrderDTO> orderList = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new OrderDTO(
                                rs.getLong("ID"),
                                rs.getString("CONTACT_NAME"),
                                rs.getString("CUSTOMER_NAME"),
                                rs.getString("COUNTRY"),
                                (rs.getTimestamp("ORDER_DATE")!=null) ? rs.getTimestamp("ORDER_DATE").toLocalDateTime() : null
                        )
        );
		
		
		return orderList;
	}


	@Override
	public List<OrderDTO> getAllCustomers() {
		
		String query="SELECT ORDERS.ID, CUSTOMERS.CONTACT_NAME, CUSTOMERS.CUSTOMER_NAME, CUSTOMERS.COUNTRY, ORDERS.ORDER_DATE "
				+ "FROM ORDERS "
				+ "RIGHT JOIN CUSTOMERS ON ORDERS.CUSTOMER_ID=CUSTOMERS.ID;";
		
		List<OrderDTO> orderList = jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new OrderDTO(
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
