package com.example.catalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.catalogservice.entity.Product;
import com.example.catalogservice.service.ProductService;

@RestController
@RequestMapping("/productData")
public class ProductController {
	
 @Autowired
	    private ProductService service;

	    // GET /products - Fetch all products
	    @GetMapping("/getAll")
	    public Object getAll() {
	        return service.getAllProducts();
	    }

	    // GET /products/{id} - Fetch product by ID
	    @GetMapping("/{id}")
	    public Product getById(@PathVariable Long id) {
	        return service.getProductById(id);
	    }

	    // POST /products/add - Add a new product
	    @PostMapping("/addProduct")
	    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
	        Product savedProduct = service.addProduct(product);
	        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	    }

	    // PUT /products/{id} - Update a product
	    @PutMapping("/updateProduct/{id}")
	    public Product update(@PathVariable Long id, @RequestBody Product product) {
	        return service.updateProduct(id, product);
	    }

	    // DELETE /products/{id} - Delete a product
	    @DeleteMapping("/deleteProduct/{id}")
	    public String delete(@PathVariable Long id) {
	        service.deleteProduct(id);
	        return "Product deleted successfully";
	    }
	}

		