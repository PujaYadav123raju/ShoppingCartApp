package com.example.cartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.cartservice.entity.CartItem;
import com.example.cartservice.repository.CartRepository;
import com.example.catalogservice.dto.ApplicationDTO;


@Service
public class CartService {
	
@Autowired
	    private RestTemplate restTemplate;

	    @Autowired
	    private CartRepository repository;

	    private final String CATALOG_SERVICE_URL = "http://localhost:8082/api/catalog";

	    // Fetch product details from catalog service
	    public ApplicationDTO getProductFromCatalog(Long productId) {
	        ApplicationDTO product = restTemplate.getForObject(CATALOG_SERVICE_URL + "/" + productId, ApplicationDTO.class);
	        if (product != null) {
	            return product;
	        }
	        throw new RuntimeException("Product not found in catalog service");
	    }

	    // Add an item to the cart
	    public CartItem addItemToCart(Long productId, Long customerId) {
	        ApplicationDTO product = getProductFromCatalog(productId);

	        if (product == null) {
	            throw new RuntimeException("Unable to fetch product details from catalog service");
	        }

	        CartItem cartItem = new CartItem();
	        cartItem.setCustomerId(customerId);
	        cartItem.setItemName(product.getItemName());
	        cartItem.setMrp(product.getMrp());
	        cartItem.setProductDiscount(product.getProductDiscount());
	        cartItem.setDeliveryFee(product.getDeliveryFee());

	        // Calculate totalAmount
	        double totalAmount = (product.getMrp() - product.getProductDiscount()) + product.getDeliveryFee();
	        cartItem.setTotalAmount(totalAmount);

	        return repository.save(cartItem);
	    }

	    // Get all cart items for a specific customer
	    public List<CartItem> getCartByCustomerId(Long customerId) {
	        return repository.findByCustomerId(customerId);
	    }

	    // Remove an item from the cart
	    public void removeItemFromCart(Long id) {
	        repository.deleteById(id);
	    }

	    // Update an item in the cart
	    public CartItem updateCartItem(Long id, CartItem updatedCartItem) {
	        CartItem existingCartItem = repository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Cart item not found"));

	        existingCartItem.setItemName(updatedCartItem.getItemName());
	        existingCartItem.setMrp(updatedCartItem.getMrp());
	        existingCartItem.setProductDiscount(updatedCartItem.getProductDiscount());
	        existingCartItem.setDeliveryFee(updatedCartItem.getDeliveryFee());

	        // Recalculate totalAmount
	        double totalAmount = (updatedCartItem.getMrp() - updatedCartItem.getProductDiscount())
	                + updatedCartItem.getDeliveryFee();
	        existingCartItem.setTotalAmount(totalAmount);

	        return repository.save(existingCartItem);
	    }

	    // Get all cart items
	    public List<CartItem> getAllCartItems() {
	        return repository.findAll();
	    }
	}
