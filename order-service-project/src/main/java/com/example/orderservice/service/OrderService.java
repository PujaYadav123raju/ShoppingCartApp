package com.example.orderservice.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//import com.example.inventoryservice.dto.ApplicationDTO;
import com.example.inventoryservice.entity.InventoryItem;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	private final String INVENTORY_SERVICE_URL = "http://localhost:8080/api/inventory";

	// Create an order
	public String createOrder(OrderEntity order) {

		System.out.println("ORDER" + order);
		if (order.getProductId() == null) {
			throw new IllegalArgumentException("Product ID cannot be null");
		}

		try {
	

			// Step 1: Check inventory
			InventoryItem inventory = restTemplate.getForObject(
					"http://localhost:8080/api/inventory/checkInventory/" + order.getProductId(), InventoryItem.class);

			//System.out.println("order id is : "+order.getProductId());
			// Step 3: Save the order
			repository.save(order);
			return "Order placed successfully";

		} catch (HttpClientErrorException.NotFound e) {
			   System.err.println("Product not found in inventory: " + e.getMessage());
			return "Product not found in inventory: " + e.getMessage();
		} catch (RestClientException e) {
			return "Failed to communicate with Inventory Service: " + e.getMessage();
		}

	}

	// Fetch all orders
	public List<OrderEntity> getAllOrders() {
		return repository.findAll();
	}

	// Fetch order by ID
	public Optional<OrderEntity> getOrderById(Long id) {
		return repository.findById(id);
	}

	// Update an order
	public OrderEntity updateOrder(Long id, OrderEntity orderDetails) {
		OrderEntity order = repository.findById(id).orElseThrow();

		order.setName(orderDetails.getName());
		order.setQuantity(orderDetails.getQuantity());
		order.setPrice(orderDetails.getPrice());
		return repository.save(order);
	}

	// Delete an order
	public void deleteOrder(Long id) {
		OrderEntity order = repository.findById(id).orElseThrow();
		repository.delete(order);
	}
}
