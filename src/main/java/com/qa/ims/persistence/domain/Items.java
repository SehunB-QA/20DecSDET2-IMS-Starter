package com.qa.ims.persistence.domain;

public class Items {
	
	private Long itemsID;
	private String itemsName;
	private Double itemsPrice;

	public Items(String itemsName, Double itemsPrice) {
		this.setItemsName(itemsName);
		this.setItemsPrice(itemsPrice);
	}

	public Items(Long itemsID, String itemsName, Double itemsPrice) {
		this.setItemsID(itemsID);
		this.setItemsName(itemsName);
		this.setItemsPrice(itemsPrice);
	}

	public Long getItemsId() {
		return itemsID;
	}

	public void setItemsID(Long itemsID) {
		this.itemsID = itemsID;
	}

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public double getItemsPrice() {
		return itemsPrice;
	}

	public void setItemsPrice(Double itemsPrice) {
		this.itemsPrice = itemsPrice;
	}

	@Override
	public String toString() {
		return "Items ID:" + itemsID + " Item name:" + itemsName + " Item Price:" + itemsPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemsName == null) ? 0 : itemsName.hashCode());
		result = prime * result + ((itemsID == null) ? 0 : itemsID.hashCode());
		result = prime * result + ((itemsPrice == null) ? 0 : itemsPrice.hashCode());
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
		Items other = (Items) obj;
		if (getItemsName() == null) {
			if (other.getItemsName() != null)
				return false;
		} else if (!getItemsName().equals(other.getItemsName()))
			return false;
		if (itemsID == null) {
			if (other.itemsID != null)
				return false;
		} else if (!itemsID.equals(other.itemsID))
			return false;
		if (itemsPrice == null) {
			if (other.itemsPrice != null)
				return false;
		} else if (!itemsPrice.equals(other.itemsPrice))
			return false;
		return true;
	}

}
