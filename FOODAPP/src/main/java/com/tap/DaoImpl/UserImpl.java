package com.tap.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.Dao.UserDao;
import com.tap.models.User;
import com.tap.util.DBConnection;

public class UserImpl implements UserDao{
	int res =0;
	private String INSERT="INSERT into user (name,username,password,email,phone,address,role,createDate,lastLoginDate) values(?,?,?,?,?,?,?,?,?)";
	private String DELETE_USER = "DELETE FROM user WHERE userId=?";
	private String GET_USER = "SELECT * FROM user WHERE userId=?";
	private String GET_ALL_USER="SELECT * FROM user";
	private String UPDATE_USER="UPDATE user SET name=?,username=?,password=?,email=?,phone=?,address=?,role=? WHERE userId=? ";
	Connection con = DBConnection.DBconnect();

	@Override
	public void addUser(User user) {
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getEmail());
			pstmt.setInt(5, user.getPhone());
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getRole());
			pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

			int res = pstmt.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updateUser(User user) {
		int res =0;
		try {
			PreparedStatement pstmt = con.prepareStatement(UPDATE_USER);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getEmail());
			pstmt.setInt(5, user.getPhone());
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getRole());
			pstmt.setInt(8, user.getUserid());
			
			res = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

	}

	@Override
	public int deleteUser(int id) {
//		Connection con = DBConnection.DBconnect();
		int res =0;
		try {
			PreparedStatement pstmt = con.prepareStatement(DELETE_USER);
			pstmt.setInt(1, id);
			
			res= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public User getUser(int id) {
//		Connection con = DBConnection.DBconnect();
		User user = null;

		try {
			PreparedStatement pstmt = con.prepareStatement(GET_USER);
			pstmt.setInt(1, id);

			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int userId=res.getInt("userId");
				String name=res.getString("name");
				String username=res.getString("username");;
				String password=res.getString("password");;
				String email=res.getString("email");;
				String phone=res.getString("phone");;
				String address=res.getString("address");;
				String role=res.getString("role");;
				Timestamp createDate=res.getTimestamp("createDate");;
				Timestamp lastLoginDate=res.getTimestamp("lastLoginDate");
				user = new User(userId, name, username, password, email, userId, address, role, createDate, lastLoginDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		User user =null;
		
//		Connection con = DBConnection.DBconnect();
		ArrayList<User> list = new ArrayList<User>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_USER);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				int userId=res.getInt("userId");
				String name=res.getString("name");
				String username=res.getString("username");;
				String password=res.getString("password");;
				String email=res.getString("email");;
				int phone=res.getInt("phone");;
				String address=res.getString("address");;
				String role=res.getString("role");;
				Timestamp createDate=res.getTimestamp("createDate");;
				Timestamp lastLoginDate=res.getTimestamp("lastLoginDate");
				user = new User(userId, name, username, password, email, phone, address, role, createDate, lastLoginDate);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}




}