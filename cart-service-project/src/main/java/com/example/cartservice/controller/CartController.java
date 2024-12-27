package com.example.cartservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.cartservice.entity.CartItem;
import com.example.cartservice.service.CartService;
//import com.example.catalogservice.dto.ApplicationDTO;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	

	    private final CartService service;

	    public CartController(CartService service) {
	        this.service = service;
	    }

	    // Get all cart items
	    @GetMapping("/items")
	    public ResponseEntity<List<CartItem>> getAllCartItems() {
	        List<CartItem> cartItems = service.getAllCartItems();
	        return ResponseEntity.ok(cartItems);
	    }

	    // Get cart items for a specific customer
	    @GetMapping("/{customerId}")
	    public ResponseEntity<List<CartItem>> getCartByCustomerId(@PathVariable Long customerId) {
	        return ResponseEntity.ok(service.getCartByCustomerId(customerId));
	    }

	    // Add an item to the cart
	    @PostMapping("/AddData")
	    public ResponseEntity<CartItem> addItemToCart(@PathVariable Long productId, @PathVariable Long customerId) {
	        CartItem cartItem = service.addItemToCart(productId, customerId);
	        return ResponseEntity.ok(cartItem);
	    }

	    // Update an item in the cart
	    @PutMapping("/{id}")
	    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem) {
	        CartItem cartItem = service.updateCartItem(id, updatedCartItem);
	        return ResponseEntity.ok(cartItem);
	    }

	    // Remove an item from the cart
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long id) {
	        service.removeItemFromCart(id);
	        return ResponseEntity.noContent().build();
	    }
	}
