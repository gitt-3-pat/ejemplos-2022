package edu.icai.gittpat.controller;

import edu.icai.gittpat.service.dto.CustomerDTO;
import edu.icai.gittpat.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers2() {

        var orders = customerService.getCustomers();

        return ResponseEntity.ok().body(orders);
    }

    //CUSTOMER-ORDER
    @GetMapping("/customers/with-orders")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {

        var orders = customerService.getCustomersWithOrders();

        return ResponseEntity.ok().body(orders);
    }

}
