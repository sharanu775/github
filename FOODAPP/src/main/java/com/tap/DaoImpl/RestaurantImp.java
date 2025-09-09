package com.tap.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.Dao.RestaurantDao;
import com.tap.models.Restaurant;
import com.tap.util.DBConnection;

public class RestaurantImp implements RestaurantDao {

	private static final String INSERT_RESTAURANT = "INSERT into restaurant(name,address,phone,rating,cusineType,isActive,eta,adminUserId,imagePath) values(?,?,?,?,?,?,?,?,?)";
	private static final String GET_RESTAURANT = "SELECT * FROM restaurant WHERE restaurantId=?";
	private static final String GET_ALL_RESTAURANT = "SELECT * FROM restaurant";
	private static final String DELETE_RESTAURANT = "DELETE FROM restaurant WHERE restaurantId=?";
	private static final String UPDATE_RESTAURANT = "UPDATE restaurant SET name=?,address=?,phone=?,rating=?,cusineType=?,isActive=?,eta=?,adminUserId=?,imagePath=? WHERE restaurantId=? ";

	@Override
	public void addRestaurant(Restaurant restaurant) {
		try (Connection con = DBConnection.DBconnect(); 
		     PreparedStatement pstmt = con.prepareStatement(INSERT_RESTAURANT)) {
			
			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getAddress());
			pstmt.setString(3, restaurant.getPhone());
			pstmt.setFloat(4, restaurant.getRating());
			pstmt.setString(5, restaurant.getCusineType());
			pstmt.setInt(6, restaurant.getIsActive());
			pstmt.setString(7, restaurant.getEta());
			pstmt.setInt(8, restaurant.getAdminUserId());
			pstmt.setString(9, restaurant.getImagePath());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updateRestaurant(Restaurant restaurant) {
		int result = 0;
		try (Connection con = DBConnection.DBconnect(); 
		     PreparedStatement pstmt = con.prepareStatement(UPDATE_RESTAURANT)) {
			
			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getAddress());
			pstmt.setString(3, restaurant.getPhone());
			pstmt.setInt(4, restaurant.getRating());
			pstmt.setString(5, restaurant.getCusineType());
			pstmt.setInt(6, restaurant.getIsActive());
			pstmt.setString(7, restaurant.getEta());
			pstmt.setInt(8, restaurant.getAdminUserId());
			pstmt.setString(9, restaurant.getImagePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteRestaurant(int id) {
		int result = 0;
		try (Connection con = DBConnection.DBconnect(); 
		     PreparedStatement pstmt = con.prepareStatement(DELETE_RESTAURANT)) {

			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Restaurant getRestaurant(int id) {
		Restaurant restaurant = null; // Use a local variable
		try (Connection con = DBConnection.DBconnect(); 
		     PreparedStatement pstmt = con.prepareStatement(GET_RESTAURANT)) {
			
			pstmt.setInt(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			
			if (resultSet.next()) {
				int restaurantId = resultSet.getInt("restaurantId");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String phone = resultSet.getString("phone");
				int rating = resultSet.getInt("rating");
				String cusineType = resultSet.getString("cusineType");
				int isActive = resultSet.getInt("isActive");
				String eta = resultSet.getString("eta");
				int adminUserId = resultSet.getInt("adminUserId");
				String imagePath = resultSet.getString("imagePath");
				
				restaurant = new Restaurant(restaurantId, name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> list = new ArrayList<>(); // Use the interface type
		try (Connection con = DBConnection.DBconnect(); 
		     PreparedStatement pstmt = con.prepareStatement(GET_ALL_RESTAURANT);
			 ResultSet resultSet = pstmt.executeQuery()) {

			while(resultSet.next()) {
				int restaurantId = resultSet.getInt("restaurantId");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String phone = resultSet.getString("phone");
				int rating = resultSet.getInt("rating");
				String cusineType = resultSet.getString("cusineType");
				int isActive = resultSet.getInt("isActive");
				String eta = resultSet.getString("eta");
				int adminUserId = resultSet.getInt("adminUserId");
				String imagePath = resultSet.getString("imagePath");
				
				Restaurant restaurant = new Restaurant(restaurantId, name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath);
				list.add(restaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("List size: " + list.size()); 
		return list;
	}
}