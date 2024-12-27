package com.example.inventoryservice.entity;

import jakarta.persistence.*;

@Entity
public class InventoryItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    private double price;
    
    
	public InventoryItem() {
		super();
		
	}
	
	public InventoryItem(Long id, String name, int quantity, double price) {
		super();
		this.id = id;
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
	public String getname() {
		return name;
	}
	public void setname(String name) {
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
		return "InventoryItem [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	
    
    
}
