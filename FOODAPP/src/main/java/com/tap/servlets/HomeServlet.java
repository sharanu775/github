package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import com.tap.DaoImpl.RestaurantImp;
import com.tap.models.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	System.out.println("home");
	
	 RestaurantImp RestaurantDAO=new RestaurantImp();
	 
	List<Restaurant>allRestaurants= RestaurantDAO.getAllRestaurants();
	System.out.println("Restaurants from DAO: " + (allRestaurants != null ? allRestaurants.size() : "null"));
	
	req.setAttribute("restaurants", allRestaurants);
	
	RequestDispatcher rd=req.getRequestDispatcher("Home.jsp");
	rd.forward(req, resp);
	}
}

