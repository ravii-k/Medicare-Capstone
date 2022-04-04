package com.ss.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ss.model.Customer;

@Entity
public class Purchase {
	@Id
	private int id;
	//private float size;
	private Date dop;
	private int quantity;
	private float totalcost;
	private int productid;
	@OneToOne
	private Customer customer;
	

	public Purchase() {
		super();
	}


	public Purchase(int id,  Date dop, int quantity, float totalcost, int productid, Customer customer) {
		super();
		this.id = id;
		//this.size = size;
		this.dop = dop;
		this.quantity = quantity;
		this.totalcost = totalcost;
		this.productid = productid;
		this.customer = customer;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Date getDop() {
		return dop;
	}

	public void setDop(Date dop) {
		this.dop = dop;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(float totalcost) {
		this.totalcost = totalcost;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
