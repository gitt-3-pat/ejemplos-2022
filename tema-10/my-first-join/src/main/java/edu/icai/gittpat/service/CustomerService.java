package edu.icai.gittpat.service;

import java.util.List;


import edu.icai.gittpat.service.dto.OrderDTO;
import edu.icai.gittpat.service.dto.OrderProjectionDTO;
import edu.icai.gittpat.service.dto.CustomerDTO;

public interface CustomerService {

    //All data from Customer Table
    List<CustomerDTO> getCustomers();

    //All Customers with Orders
    List<CustomerDTO> getCustomersWithOrders();
}
