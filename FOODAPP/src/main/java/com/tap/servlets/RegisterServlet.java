package com.tap.servlets;

import java.io.IOException;

import com.tap.DaoImpl.UserImpl;
import com.tap.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int phone =Integer.parseInt(request.getParameter("phone"));
        String address = request.getParameter("address");
        String role =  request.getParameter("role");
        
        User user = new User(name, username, password, email, phone, address, role);
        UserImpl udi = new UserImpl();
        udi.addUser(user); 
        
        response.sendRedirect("login.jsp");
	}

}
