package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Orders {

	private Long orderID;
	private Double totalOrderPrice;
	private List<Items> orderItems = new ArrayList<>();
	private Customer orderCustomer;
	
	private Long  customerID;
	private String customerFirstName;
	private String customerSurname;
	private String itemName;
	private Double itemPrice;
	
	public Orders(Long orderID,  List<Items> orderItems, Customer orderCustomer, Double totalOrderPrice) {
		super();
		this.orderID = orderID;
		this.totalOrderPrice = totalOrderPrice;
		this.orderItems = orderItems;
		this.orderCustomer = orderCustomer;
	}
	
	
	//this one
	public Orders(Long orderID, String customerFirstName, String customerSurname , Long customerID, String itemName, Double itemPrice) {
		super();
		this.orderID = orderID;
		this.customerFirstName = customerFirstName;
		this.customerSurname = customerSurname;
	    this.customerID = customerID;
	    this.itemName = itemName;
	    this.itemPrice = itemPrice;
	    
	}
	
	public Orders(Customer orderCustomer, Long orderID) {
		super();
		this.orderID = orderID;
		this.orderCustomer = orderCustomer;
	}
	
	
	public Orders( List<Items> orderItems, Customer orderCustomer, Double totalOrderPrice) {
		super();
		this.totalOrderPrice = totalOrderPrice;
		this.orderItems = orderItems;
		this.orderCustomer = orderCustomer;
	}
	
	public Orders( Customer orderCustomer, Double totalOrderPrice) {
		super();
		this.totalOrderPrice = totalOrderPrice;
		this.orderCustomer = orderCustomer;
	}
	
	public Orders() {}


	public Long getOrderID() {
		return orderID;
	}


	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}


	public Double getTotalOrderPrice() {
		return totalOrderPrice;
	}


	public void setTotalOrderPrice(Double totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}


	public List<Items> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<Items> orderItems) {
		this.orderItems = orderItems;
	}


	public Customer getOrderCustomer() {
		return orderCustomer;
	}


	public void setOrderCustomer(Customer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}


	@Override
	public String toString() {
		return  "Order ID: " + orderID + " Customer's first name: " + customerFirstName +
				" Surname: " + customerSurname +  " " +  "Customer ID: " + customerID  + " Item Name : " + itemName + " Item Price: " + "£" +   itemPrice;
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderCustomer == null) ? 0 : orderCustomer.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((totalOrderPrice == null) ? 0 : totalOrderPrice.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (orderCustomer == null) {
			if (other.orderCustomer != null)
				return false;
		} else if (!orderCustomer.equals(other.orderCustomer))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (totalOrderPrice == null) {
			if (other.totalOrderPrice != null)
				return false;
		} else if (!totalOrderPrice.equals(other.totalOrderPrice))
			return false;
		return true;
	}
	
	
	
}
