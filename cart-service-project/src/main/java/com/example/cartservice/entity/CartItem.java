package com.example.cartservice.entity;

import jakarta.persistence.*;

@Entity
public class CartItem {

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Long customerId;
	    private String itemName;
	    private double mrp; // Maximum Retail Price
	    private double productDiscount; // Discount on the product
	    private double deliveryFee; // Delivery charges
	    private double totalAmount; // Total amount after applying discount and adding delivery fee
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public double getMrp() {
			return mrp;
		}
		public void setMrp(double mrp) {
			this.mrp = mrp;
		}
		public double getProductDiscount() {
			return productDiscount;
		}
		public void setProductDiscount(double productDiscount) {
			this.productDiscount = productDiscount;
		}
		public double getDeliveryFee() {
			return deliveryFee;
		}
		public void setDeliveryFee(double deliveryFee) {
			this.deliveryFee = deliveryFee;
		}
		public double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

	    
	    
}
