package com.tap.models;

public class Restaurant {
	private int restaurantId;
	private String name;
	private  String address;
	private String phone;
	private int rating;
	private String cusineType;
	private  int isActive;
	private String eta;
	private  int adminUserId;
	private String imagePath;
	
	public Restaurant() {
	}
	
	@Override
	public String toString() {
		return "Restaurant [restaurantID=" + restaurantId + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", rating=" + rating + ", cusineType=" + cusineType + ", isActive=" + isActive + ", eta="
				+ eta + ", adminUserId=" + adminUserId + ", imagePath=" + imagePath + "]";
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantID) {
		this.restaurantId = restaurantID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCusineType() {
		return cusineType;
	}

	public void setCusineType(String cusineType) {
		this.cusineType = cusineType;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Restaurant(int restaurantID, String name, String address, String phone, int rating, String cusineType,
			int isActive, String eta, int adminUserId, String imagePath) {
		super();
		this.restaurantId = restaurantID;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cusineType = cusineType;
		this.isActive = isActive;
		this.eta = eta;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
	}
	
	public Restaurant(String name, String address, String phone, int rating, String cusineType,
			int isActive, String eta, int adminUserId, String imagePath) {
		super();
	
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cusineType = cusineType;
		this.isActive = isActive;
		this.eta = eta;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
	}
	
	
	
}
