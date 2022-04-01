package edu.icai.gittpat.service;

import edu.icai.gittpat.service.dto.CustomerDTO;
import edu.icai.gittpat.service.dto.OrderDTO;
import edu.icai.gittpat.service.dto.OrderProjectionDTO;

import java.util.List;

public interface OrderService {

    //All data from Customer Table
    List<OrderDTO> getOrders();

    //INNER-JOIN
    List<OrderProjectionDTO> getOrdersWithCustomer();

    //LEFT-JOIN
    List<OrderProjectionDTO> getAllOrders();

    //SANDBOX
    List<OrderProjectionDTO> getAllOrders2();
}
