


package com.tap.DaoImpl;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.Dao.OrdersDao;
import com.tap.models.Orders;
import com.tap.util.DBConnection;

public class orderImpl implements OrdersDao {
	Connection con=DBConnection.DBconnect();
	int res=0;
	public Orders orders;
	private String ADD_ORDERS="INSERT into orders(userId,restaurantId,orderDate,totalAmount,status,paymentMode)"
			+ " values(?,?,?,?,?,?)" ;
	private String DELETE_ORDERS="DELETE FROM orders WHERE orderId=?";
	private String GET_ORDERS="SELECT * FROM orders WHERE orderId=?";
	private String GET_ALL_ORDERS="SELECT * FROM orders";
	private String UPDATE_ORDERS="UPDATE orders SET userId=?,restaurantId=?,totalAmount=?,status=?,paymentMode=? WHERE orderId=?";
	private int orderId;
	@Override
	public int addOrders(Orders orders) {
		try {
//			PreparedStatement pstmt = con.prepareStatement(ADD_ORDERS);
			
			PreparedStatement pstmt = con.prepareStatement(ADD_ORDERS,Statement.RETURN_GENERATED_KEYS);
//			pstmt.setInt(1, orders.getOrderId());
			pstmt.setInt(1, orders.getUserId());
			pstmt.setInt(2, orders.getRestaurantId());
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(4, orders.getTotalAmount());
			pstmt.setString(5, orders.getStatus());
			pstmt.setString(6, orders.getPaymentMode());

			res = pstmt.executeUpdate();
			
			ResultSet id = pstmt.getGeneratedKeys();
			while(id.next()) {
				 orderId = id.getInt(1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderId;

	}

	@Override
	public int updateOrders(Orders orders) {
		try {

			PreparedStatement pstmt = con.prepareStatement(UPDATE_ORDERS);
			pstmt.setInt(1, orders.getUserId());
			pstmt.setInt(2, orders.getRestaurantId());
			pstmt.setInt(3, orders.getTotalAmount());
			pstmt.setString(4, orders.getStatus());
			pstmt.setString(5, orders.getPaymentMode());
			pstmt.setInt(6, orders.getOrderId());
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteOrders(int id) {
		try {
			PreparedStatement pstmt = con.prepareStatement(DELETE_ORDERS);
			pstmt.setInt(1, id);

			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Orders getOrders(int id) {
		Orders orders = null;
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ORDERS);

			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int orderId=res.getInt("orderId");
				int userId=res.getInt("userId");
				int restaurantId=res.getInt("restaurantId");
				String orderDate=res.getString("orderDate");
				int totalAmount=res.getInt("totalAmount");
				String status=res.getString("status");
				String paymentMode=res.getString("paymentMode");
				orders = new Orders(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		ArrayList<Orders> list=new ArrayList<Orders>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_ORDERS);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int orderId=res.getInt("orderId");
				int userId=res.getInt("userId");
				int restaurantId=res.getInt("restaurantId");
				String orderDate=res.getString("orderDate");
				int totalAmount=res.getInt("totalAmount");
				String status=res.getString("status");
				String paymentMode=res.getString("paymentMode");
				orders = new Orders(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode);
				list.add(orders);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

