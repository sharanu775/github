package com.tap.Dao;

import java.util.List;
import com.tap.models.Orders;

public interface OrdersDao {
	int addOrders(Orders orders);
	
	int updateOrders(Orders orders);
	int deleteOrders(int id);
	
	Orders getOrders(int id);
	List<Orders>getAllOrders();

}
