package edu.icai.gittpat.controller;

import edu.icai.gittpat.service.CustomerService;
import edu.icai.gittpat.service.OrderService;
import edu.icai.gittpat.service.dto.OrderDTO;
import edu.icai.gittpat.service.dto.OrderProjectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getOrders() {

        var orders = orderService.getOrders();

        return ResponseEntity.ok().body(orders);
    }

    //INNER-JOIN
    @GetMapping("/orders/with-customer")
    public ResponseEntity<List<OrderProjectionDTO>> getOrdersWithCustomers() {

        var orders = orderService.getOrdersWithCustomer();

        return ResponseEntity.ok().body(orders);
    }

    //LEFT-JOIN
    @GetMapping("/orders/all")
    public ResponseEntity<List<OrderProjectionDTO>> getAllOrders() {

        var orders = orderService.getAllOrders();

        return ResponseEntity.ok().body(orders);
    }

    //SANDBOX
    @GetMapping("/orders/all2")
    public ResponseEntity<List<OrderProjectionDTO>> getAllOrders2() {

        var orders = orderService.getAllOrders2();

        return ResponseEntity.ok().body(orders);
    }
}
