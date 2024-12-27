package com.example.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerservice.entity.CustomerEntity;
import com.example.customerservice.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	    private CustomerService service;

	    @GetMapping("/getAll")
	    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
	        return ResponseEntity.ok(service.getAllCustomers());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable Long id) {
	        return service.getCustomerById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customer) {
	        CustomerEntity createdCustomer = service.createCustomer(customer);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable Long id, @RequestBody CustomerEntity customerDetails) {
	        CustomerEntity updatedCustomer = service.updateCustomer(id, customerDetails);
	        return ResponseEntity.ok(updatedCustomer);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
	        service.deleteCustomer(id);
	        return ResponseEntity.noContent().build();
	    }
	}


