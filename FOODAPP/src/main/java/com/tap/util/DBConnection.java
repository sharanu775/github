package com.tap.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {



		private static final String URL = "jdbc:mysql://localhost:3306/foodapp";
		private static final String USERNAME = "root";
		private static final String PASSAWORD = "johnwick@3";
		private static Connection con;
		
		
		public static Connection DBconnect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(URL,USERNAME,PASSAWORD);
			
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
}
