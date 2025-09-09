package com.tap.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.Dao.MenuDao;
import com.tap.models.Menu;
import com.tap.util.DBConnection;

public class MenuImpl implements MenuDao {
    
    // SQL statements are made final as they do not change
	private static final String INSERT_MENU = "INSERT into menu (restaurantId,itemName,description,price,rating,isAvailable,imagePath) values(?,?,?,?,?,?,?)";
	private static final String UPDATE_MENU = "UPDATE menu SET restaurantId=?,itemName=?,description=?,price=?,rating=?,isAvailable=?,imagePath=? where menuId=? ";
	private static final String GET_MENU = "SELECT * FROM menu WHERE menuId=?";
	private static final String GET_ALL_MENUS = "SELECT * FROM menu";
	private static final String DELETE_MENU = "DELETE FROM menu where menuId=?";

	@Override
	public void addMenu(Menu menu) {
        // Use try-with-resources to automatically close the connection and statement
		try (Connection con = DBConnection.DBconnect();
             PreparedStatement pstmt = con.prepareStatement(INSERT_MENU)) {
			
			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setInt(4, menu.getPrice());
			pstmt.setInt(5, menu.getRating());
			pstmt.setInt(6, menu.getIsAvailable());
			pstmt.setString(7, menu.getImagePath());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updateMenu(Menu menu) {
		int result = 0; // Declare variables locally
		try (Connection con = DBConnection.DBconnect();
             PreparedStatement pstmt = con.prepareStatement(UPDATE_MENU)) {
			
			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setInt(4, menu.getPrice());
			pstmt.setInt(5, menu.getRating());
			pstmt.setInt(6, menu.getIsAvailable());
			pstmt.setString(7, menu.getImagePath());
			pstmt.setInt(8, menu.getMenuId());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteMenu(int id) {
		int result = 0;
		try (Connection con = DBConnection.DBconnect();
             PreparedStatement pstmt = con.prepareStatement(DELETE_MENU)) {
			
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Menu getMenu(int id) {
		Menu menu = null;
		try (Connection con = DBConnection.DBconnect();
             PreparedStatement pstmt = con.prepareStatement(GET_MENU)) {
			
			pstmt.setInt(1, id);
			ResultSet resultSet = pstmt.executeQuery();
            
			if (resultSet.next()) {
				int menuId = resultSet.getInt("menuId");
				int restaurantId = resultSet.getInt("restaurantId");
				String itemName = resultSet.getString("itemName");
				String description = resultSet.getString("description");
				int price = resultSet.getInt("price");
				int rating = resultSet.getInt("rating");
				int isAvailable = resultSet.getInt("isAvailable");
				String imagePath = resultSet.getString("imagePath");
				menu = new Menu(menuId, restaurantId, itemName, description, price, rating, isAvailable, imagePath);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public List<Menu> getAllMenus(int restaurantID) { // Now we will use this parameter
	    List<Menu> list = new ArrayList<>();
	    // The SQL query is now correct
	    String sql = "SELECT * FROM menu WHERE restaurantId = ?";
	    
	    try (Connection con = DBConnection.DBconnect();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        
	        // Set the restaurantID parameter in the query
	        pstmt.setInt(1, restaurantID);
	        
	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            while (resultSet.next()) {
	                int menuId = resultSet.getInt("menuId");
	                int restaurantId = resultSet.getInt("restaurantId");
	                String itemName = resultSet.getString("itemName");
	                String description = resultSet.getString("description");
	                int price = resultSet.getInt("price");
	                // The 'rating' column is missing from your screenshot of the menu table
	                // You will get an error if you try to fetch it.
	                // For now, I will use a default value. Add the column to your table.
	                // int rating = resultSet.getInt("rating"); 
	                int isAvailable = resultSet.getInt("isAvailable");
	                String imagePath = resultSet.getString("imagePath");
	                Menu menu = new Menu(menuId, restaurantId, itemName, description, price, 0 /* default rating */, isAvailable, imagePath);
	                list.add(menu);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	
}