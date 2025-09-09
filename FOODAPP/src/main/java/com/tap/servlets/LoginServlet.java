package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.tap.models.User;
import com.tap.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private String USER_SQL="SELECT * FROM `user` WHERE `username`=? and `password`=?";
	
	User user;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isVaild=false;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Connection con = DBConnection.DBconnect();
		try {
			PreparedStatement pstmt = con.prepareStatement(USER_SQL);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				int userId = Integer.parseInt(res.getString("userId"));
				String name = res.getString("name");
//				String username = res.getString("username");
//				int password = res.getInt("password");
				String email = res.getString("email");
				int phone = res.getInt("phone");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createDate = res.getTimestamp("createDate");
				Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");

				user = new User(userId, name, username, password, email, phone, address, role, createDate, lastLoginDate);

				isVaild=true;	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(isVaild) {
			HttpSession session = request.getSession();
			
			
			session.setAttribute("user", user);
			
			response.sendRedirect("Home");
		}
//		else if(count>0) {
//				response.setContentType("text/html");
//				PrintWriter out = response.getWriter();
//				out.println("You Entered wrong password");
//				count--;
//				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//				rd.forward(request, response);
////				response.sendRedirect("login.jsp");
//		}
//		else {
//			PrintWriter out = response.getWriter();
//			out.println("You are blocked password");
//		}
		else if(count < 2) {
		    count++;
		    
		    String errorMessage;
		    if(count == 1) {
		        errorMessage = "You entered wrong password. 2 chances left";
		    }
		    else{
		        errorMessage = "You entered wrong password. 1 chance left";
		    }
		    
		    // Set error message as request attribute
		    request.setAttribute("errorMessage", errorMessage);
		    
		    // Forward to login.jsp to preserve the error message
		    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		    rd.forward(request, response);
		}
		else {
		    PrintWriter out = response.getWriter();
		    out.println("You are blocked. Password attempts exceeded. Please Contact Admin");
	
		}
	
	
	}

}