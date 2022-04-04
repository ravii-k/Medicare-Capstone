package com.ss.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cart {
	
	@Id
	private int id;
	private int productId;
	private int quantity;
	//private float size;
	private float price;
	
	public Cart() {
		super();
	}
	
	public Cart(int id, int productId, int quantity,  float price) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		//this.size = size;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
