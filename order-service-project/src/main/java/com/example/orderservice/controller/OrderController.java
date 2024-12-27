package com.example.orderservice.controller;

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

//import com.example.orderservice.dto.ApplicationDTO;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	  @Autowired
		    private OrderService service;

		    @GetMapping("/getAll")
		    public ResponseEntity<List<OrderEntity>> getAllOrders() {
		        return ResponseEntity.ok(service.getAllOrders());
		    }

		    @GetMapping("/{id}")
		    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
		        return service.getOrderById(id)
		                .map(ResponseEntity::ok)
		                .orElse(ResponseEntity.notFound().build());
		    }

		    @PostMapping("/addOrder")
		    public ResponseEntity<?> createOrder(@RequestBody OrderEntity order) {
		        String response = service.createOrder(order);
		        if (response.equals("Order placed successfully")) {
		            return ResponseEntity.status(201).body(response);
		        }
		        return ResponseEntity.badRequest().body(response);
		    }

		    @PutMapping("/{id}")
		    public ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id, @RequestBody OrderEntity orderDetails) {
		        return ResponseEntity.ok(service.updateOrder(id, orderDetails));
		    }

		    @DeleteMapping("/{id}")
		    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		        service.deleteOrder(id);
		        return ResponseEntity.noContent().build();
		    }
		}
		    		    	    