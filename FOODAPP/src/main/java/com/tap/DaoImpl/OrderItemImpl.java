package com.tap.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.tap.Dao.OrderItemDao;
import com.tap.models.OrderItem;
import com.tap.util.DBConnection;


public class OrderItemImpl implements OrderItemDao {

   
    private static final String INSERT_ORDER_ITEM = "INSERT into orderitem( menuId, quantity, totalPrice) values(?,?,?)";
    private static final String DELETE_ORDER_ITEM = "DELETE FROM orderitem WHERE orderItemId=?";
    private static final String GET_ORDER_ITEM = "SELECT * FROM orderitem WHERE orderItemId=?";
    private static final String GET_ALL_ORDER_ITEMS = "SELECT * FROM orderitem";
    private static final String UPDATE_ORDER_ITEM = "UPDATE orderitem SET orderId=?, menuId=?, quantity=?, totalPrice=? WHERE orderItemId=?";

    @Override
    public void addOrderitem(OrderItem orderItem) { 
       
        try (Connection con = DBConnection.DBconnect();
             PreparedStatement pstmt = con.prepareStatement(INSERT_ORDER_ITEM)) {
            
//            pstmt.setInt(, orderItem.getOrderId());
            pstmt.setInt(1, orderItem.getMenuId());
            pstmt.setInt(2, orderItem.getQuantity());
            pstmt.setInt(3, orderItem.getTotalPrice());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateOrderitem(OrderItem orderItem) {
        int result = 0; // 4. Use a local variable
        try (Connection con = DBConnection.DBconnect();
             PreparedStatement pstmt = con.prepareStatement(UPDATE_ORDER_ITEM)) {
            
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setInt(4, orderItem.getTotalPrice());
            pstmt.setInt(5, orderItem.getOrderitemID());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteOrderitem(int id) {
        int result = 0; // Use a local variable
        try (Connection con = DBConnection.DBconnect();
             PreparedStatement pstmt = con.prepareStatement(DELETE_ORDER_ITEM)) {
            
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public OrderItem getOrderitem(int id) { // Corrected the return type here
        OrderItem item = null; // Use a local variable
        try (Connection con = DBConnection.DBconnect();
             PreparedStatement pstmt = con.prepareStatement(GET_ORDER_ITEM)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    item = new OrderItem(
                        rs.getInt("orderItemId"), 
                        rs.getInt("orderId"), 
                        rs.getInt("menuId"),
                        rs.getInt("quantity"), 
                        rs.getInt("totalPrice"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<OrderItem> getAllOrderitem() {
        List<OrderItem> itemList = new ArrayList<>();
        try (Connection con = DBConnection.DBconnect();
             PreparedStatement pstmt = con.prepareStatement(GET_ALL_ORDER_ITEMS);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                // 5. IMPORTANT: Create a NEW local object for each row
                OrderItem item = new OrderItem(
                    rs.getInt("orderItemId"), 
                    rs.getInt("orderId"), 
                    rs.getInt("menuId"),
                    rs.getInt("quantity"), 
                    rs.getInt("totalPrice"));
                itemList.add(item); // Add the new, unique object
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}