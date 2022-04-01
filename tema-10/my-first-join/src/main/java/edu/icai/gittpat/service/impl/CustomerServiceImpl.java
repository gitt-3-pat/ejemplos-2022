package edu.icai.gittpat.service.impl;

import edu.icai.gittpat.repository.OrdersRepository;
import edu.icai.gittpat.service.dto.OrderDTO;
import edu.icai.gittpat.service.dto.OrderProjectionDTO;
import edu.icai.gittpat.service.dto.CustomerDTO;
import edu.icai.gittpat.repository.CustomersRepository;
import edu.icai.gittpat.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomersRepository customersRepository;

	@Override
	public List<CustomerDTO> getCustomers() {
		return StreamSupport.stream(customersRepository.findAll().spliterator(), false)
				.map(obj -> new CustomerDTO(
						obj.getId(),
						obj.getCustomerName(),
						obj.getContactName(),
						obj.getCountry()))
				.toList();
	}

	@Override
	public List<CustomerDTO> getCustomersWithOrders() {

		String query=
    			"""
    			SELECT C.ID, C.CONTACT_NAME, C.CUSTOMER_NAME, C.COUNTRY
				FROM CUSTOMERS C, ORDERS O
				WHERE C.ID=O.CUSTOMER_ID;
				""";

		List<CustomerDTO> orderList = jdbcTemplate.query(
				query,
				(rs, rowNum) ->
						new CustomerDTO(
								rs.getLong("ID"),
								rs.getString("CONTACT_NAME"),
								rs.getString("CUSTOMER_NAME"),
								rs.getString("COUNTRY"))
		);

		return orderList;
	}
}
