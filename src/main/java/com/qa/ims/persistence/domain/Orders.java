package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Orders {

	private Long orderID;
	private Double totalOrderPrice;
	private List<Items> orderItems = new ArrayList<>();
	private Customer orderCustomer;
	
	private Long customerID;
	private Long itemID;
	private String customerFirstName;
	private String customerSurname;
	private String itemName;
	private Double itemPrice;
	private String dummyString;
	
	
	public Orders(Long orderID,  List<Items> orderItems, Customer orderCustomer, Double totalOrderPrice) {
		super();
		this.orderID = orderID;
		this.totalOrderPrice = totalOrderPrice;
		this.orderItems = orderItems;
		this.orderCustomer = orderCustomer;
	}
	
	
	//this one
	public Orders(Long orderID, String customerFirstName, String customerSurname , Long customerID, Long itemID, String itemName, Double itemPrice) {
		super();
		this.orderID = orderID;
		this.customerFirstName = customerFirstName;
		this.customerSurname = customerSurname;
	    this.customerID = customerID;
	    this.itemID = itemID;
	    this.itemName = itemName;
	    this.itemPrice = itemPrice;
	    
	}
	
	//this one
		public Orders(Long orderID, String customerFirstName, String customerSurname , Long customerID, Long itemID, String itemName, Double totalOrderPrice, String dummyString) {
			super();
			this.orderID = orderID;
			this.customerFirstName = customerFirstName;
			this.customerSurname = customerSurname;
		    this.customerID = customerID;
		    this.itemID = itemID;
		    this.totalOrderPrice = totalOrderPrice;
		    this.dummyString = dummyString;
		    
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


	public Orders(Long customerID2) {
		this.customerID = customerID2;
	}
	
	public Orders(Long orderID, Long  customerID2) {
		this.orderID = orderID;
		this.customerID = customerID2;
	}

	public Orders(Double totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	

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
				" Surname: " + customerSurname +  " " +  "Customer ID: " + customerID  + " "  + "Item ID: " + itemID  + " Item Name : " + itemName + " Item Price: " + "�" +   itemPrice
+ " " +"Total Order Price: " +  "�" + totalOrderPrice;
		
	}

	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerFirstName == null) ? 0 : customerFirstName.hashCode());
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((customerSurname == null) ? 0 : customerSurname.hashCode());
		result = prime * result + ((dummyString == null) ? 0 : dummyString.hashCode());
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemPrice == null) ? 0 : itemPrice.hashCode());
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
		if (customerFirstName == null) {
			if (other.customerFirstName != null)
				return false;
		} else if (!customerFirstName.equals(other.customerFirstName))
			return false;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (customerSurname == null) {
			if (other.customerSurname != null)
				return false;
		} else if (!customerSurname.equals(other.customerSurname))
			return false;
		if (dummyString == null) {
			if (other.dummyString != null)
				return false;
		} else if (!dummyString.equals(other.dummyString))
			return false;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice == null) {
			if (other.itemPrice != null)
				return false;
		} else if (!itemPrice.equals(other.itemPrice))
			return false;
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
