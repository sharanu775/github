package com.tap.Dao;

import java.util.List;

import com.tap.models.OrderItem;


public interface OrderItemDao {
void addOrderitem(OrderItem orderitem);
	
	int updateOrderitem(OrderItem orderitem);
	int deleteOrderitem(int id);
	
	OrderItem getOrderitem(int id);
	List<OrderItem>getAllOrderitem();
}
