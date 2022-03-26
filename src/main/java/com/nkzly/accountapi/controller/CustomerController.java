package com.nkzly.accountapi.controller;

import com.nkzly.accountapi.model.CustomerResponse;
import com.nkzly.accountapi.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<CustomerResponse> getCustomerByCustomerNumber(@PathVariable Integer customerNumber){
        return ResponseEntity.ok(customerService.getCustomerByCustomerNumber(customerNumber));
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }
}
