package edu.icai.gittpat.controller;

import edu.icai.gittpat.dto.OrderDTO;
import edu.icai.gittpat.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getOrders() {

        var orders = myService.getOrderswithCustomer();

        return ResponseEntity.ok().body(orders);
    }
    
    @GetMapping("/orders/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {

        var orders = myService.getAllOrders();

        return ResponseEntity.ok().body(orders);
    }
    
    @GetMapping("/customers/all")
    public ResponseEntity<List<OrderDTO>> getAllCustomers() {

        var orders = myService.getAllCustomers();

        return ResponseEntity.ok().body(orders);
    }

}
