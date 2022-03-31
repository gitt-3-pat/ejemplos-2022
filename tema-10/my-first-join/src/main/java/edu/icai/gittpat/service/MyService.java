package edu.icai.gittpat.service;

import java.util.List;


import edu.icai.gittpat.dto.OrderDTO;


public interface MyService {

    List<OrderDTO> getOrderswithCustomer();
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getAllCustomers();

}
