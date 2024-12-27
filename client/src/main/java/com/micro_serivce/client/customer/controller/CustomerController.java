package com.micro_serivce.client.customer.controller;

import com.micro_serivce.client.customer.entity.CustomerEntity;
import com.micro_serivce.client.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Page<CustomerEntity> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "true") boolean state
    ) {
        return customerService.getAllCostomers(page,size, state);
    }

    @GetMapping("/{id}")
    public CustomerEntity getCustomerById(
            @PathVariable String id
    ) {
        return customerService.getCustomerByIdOrClientId(id);
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> saveOrUpdateCustomer(@RequestBody CustomerEntity customer) {
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable Long id, @RequestBody CustomerEntity customer) {
        CustomerEntity updated = customerService.update(id, customer);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable String id
    ) {
        return customerService.delete(id);
    }
}