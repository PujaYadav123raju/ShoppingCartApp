package com.example.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customerservice.entity.CustomerEntity;
import com.example.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {

	 @Autowired
	    private CustomerRepository repository;

	    public List<CustomerEntity> getAllCustomers() {
	        return repository.findAll();
	    }

	    public Optional<CustomerEntity> getCustomerById(Long id) {
	        return repository.findById(id);
	    }

	    public CustomerEntity createCustomer(CustomerEntity customer) {
	        return repository.save(customer);
	    }

	    public CustomerEntity updateCustomer(Long id, CustomerEntity customerDetails) {
	        CustomerEntity customer = repository.findById(id)
	            .orElseThrow();
	        customer.setName(customerDetails.getName());
	        customer.setMobileNo(customerDetails.getMobileNo());
	        customer.setAddress(customerDetails.getAddress());
	        return repository.save(customer);
	    }

	    public void deleteCustomer(Long id) {
	        CustomerEntity customer = repository.findById(id)
	            .orElseThrow();
	        repository.delete(customer);
	    }
	}


