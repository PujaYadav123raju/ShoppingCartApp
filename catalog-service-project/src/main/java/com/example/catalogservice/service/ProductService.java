package com.example.catalogservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.catalogservice.dto.ApplicationDTO;
import com.example.catalogservice.entity.Product;
import com.example.catalogservice.repository.ProductRepository;
import com.example.inventoryservice.entity.InventoryItem;

@Service
public class ProductService {
	 @Autowired
	    private ProductRepository repository;

	    @Autowired
	    private RestTemplate restTemplate;

	    private final String INVENTORY_SERVICE_URL = "http://localhost:8081/api/inventory";

	    // Fetch all products with inventory details
	    public List<Product> getAllProducts() {
	        List<Product> products = repository.findAll();
	        for (Product product : products) {
	            try {
	                InventoryItem inventory = restTemplate.getForObject(INVENTORY_SERVICE_URL + "/" + product.getId(), InventoryItem.class);
	                if (inventory != null) {
	                    product.setDescription("Stock: " + inventory.getQuantity());
	                }
	            } catch (Exception e) {
	                product.setDescription("Inventory service unavailable");
	            }
	        }
	        return products;
	    }

	    // Fetch a product by ID
	    public Product getProductById(Long id) {
	        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
	    }

	    // Add a new product
	    public Product addProduct(Product product) {
	        return repository.save(product);
	    }

	    // Update an existing product
	    public Product updateProduct(Long id, Product updatedProduct) {
	        Product existingProduct = repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

	        existingProduct.setName(updatedProduct.getName());
	        existingProduct.setPrice(updatedProduct.getPrice());
	        existingProduct.setDescription(updatedProduct.getDescription());

	        return repository.save(existingProduct);
	    }

	    // Delete a product by ID
	    public void deleteProduct(Long id) {
	        if (!repository.existsById(id)) {
	            throw new RuntimeException("Product not found with ID: " + id);
	        }
	        repository.deleteById(id);
	    }
	}
