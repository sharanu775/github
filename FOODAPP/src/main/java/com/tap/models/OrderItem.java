package com.tap.models;

public class OrderItem {

	private int orderitemID;
	private int orderId;
	private int menuId;
	private int quantity;
	private int totalPrice;
	
	public OrderItem() {
	}

	@Override
	public String toString() {
		return "Orderitem [orderitemID=" + orderitemID + ", orderId=" + orderId + ", menuId=" + menuId + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + "]";
	}

	public int getOrderitemID() {
		return orderitemID;
	}

	public void setOrderitemID(int orderitemID) {
		this.orderitemID = orderitemID;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderItem(int orderitemID, int orderId, int menuId, int quantity, int totalPrice) {
		super();
		this.orderitemID = orderitemID;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	public OrderItem(int orderId, int menuId, int quantity, int totalPrice) {
		super();
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	
	
}
