package com.example.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.inventoryservice.entity.InventoryItem;
import com.example.inventoryservice.repository.InventoryRepository;

@Service
public class InventoryService {

private final InventoryRepository repository;

		    // Constructor injection for repository
		    public InventoryService(InventoryRepository repository) {
		        this.repository = repository;
		    }

		    // Reduce stock by specified quantity
		    public boolean reduceStock(Long id, int quantity) {
		        InventoryItem item = repository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
		        if (item.getQuantity() >= quantity) {
		            item.setQuantity(item.getQuantity() - quantity);
		            repository.save(item);
		            return true;
		        }
		        return false;
		    }

		    // Get all inventory items
		    public List<InventoryItem> getAllItems() {
		        return repository.findAll();
		    }

		    // Get a specific inventory item by ID
		    public InventoryItem getItemById(Long id) {
		        return repository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
		    }

		    // Add a new inventory item
		    public InventoryItem addItem(InventoryItem item) {
		        return repository.save(item);
		    }

		    // Delete an inventory item by ID
		    public void deleteItem(Long id) {
		        repository.deleteById(id);
		    }

		    // Check inventory for a specific item by ID
		    public InventoryItem checkInventory(Long id) {
		        return repository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
		    }
		}
