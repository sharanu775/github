package com.tap.models;

import java.sql.Timestamp;

public class User {
	private int Userid;
	private String name;
	private String username;
	private String password;
	private String email;
	private int phone;
	private String address;
	private String role;
	private Timestamp createDate; 
	private Timestamp lastLoginDate;
	public int getUserid() {
		return Userid;
	}
	public void setUserid(int userid) {
		Userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public User(int userid, String name, String username, String password, String email, int phone, String address,
			String role, Timestamp createDate, Timestamp lastLoginDate) {
		super();
		Userid = userid;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.createDate = createDate;
		this.lastLoginDate = lastLoginDate;
	}
	public User() {
		
	}
	public User(int userid, String name, String username, String password, String email, int phone, String address,
			String role) {
		super();
		Userid = userid;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [Userid=" + Userid + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", role=" + role + ", createDate="
				+ createDate + ", lastLoginDate=" + lastLoginDate + "]";
	}
	public User(String name, String username, String password, String email, int phone, String address, String role) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}
	
	
	
	

	
	
}


