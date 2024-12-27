package com.example.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventoryservice.entity.InventoryItem;
import com.example.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	@Autowired
	private final InventoryService service;

	public InventoryController(InventoryService service) {
		this.service = service;
	}

//api/inventory/checkInventory/
	@GetMapping("/checkInventory/{id}")
	public ResponseEntity<InventoryItem> checkInventory(@PathVariable Long id) {

		if (id == null) {
			throw new IllegalArgumentException("Product ID is required");
		}

		InventoryItem item = service.checkInventory(id);
		return ResponseEntity.ok(item);

	}

	@GetMapping("/all")
	public List<InventoryItem> getAllItems() {
		return service.getAllItems();
	}

	@GetMapping("/{id}")
	public InventoryItem getItemById(@PathVariable Long id) {
		return service.getItemById(id);
	}

	@PostMapping
	public InventoryItem addItem(@RequestBody InventoryItem item) {
		return service.addItem(item);
	}

	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable Long id) {
		service.deleteItem(id);
	}
}
