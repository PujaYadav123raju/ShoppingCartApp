package com.example.orderservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name ="orders")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productId;
	private String name;
	private int quantity;
	private double price;
	
	public OrderEntity() {
		super();
		
	}

	public OrderEntity(Long id, Long productId, String name, int quantity, double price) {
		super();
		this.id = id;
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", productId=" + productId + ", name=" + name + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
	

}
