package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tap.DaoImpl.MenuImpl;
import com.tap.DaoImpl.RestaurantImp;
import com.tap.models.Menu;
import com.tap.models.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String restaurantIdParam = req.getParameter("restaurantId");
		if (restaurantIdParam != null  && !restaurantIdParam.trim().isEmpty() && !restaurantIdParam.equals("null")) {
		    int restaurantId = Integer.parseInt(restaurantIdParam);
		    // use restaurantId
		    
		    MenuImpl mdi = new MenuImpl();
		    
		    List<Menu> menuList = mdi.getAllMenus(restaurantId);
		    
		    req.setAttribute("menuList", menuList);
		    
		    
		    
		    //		testing worked
		    
		    RestaurantImp rdi = new RestaurantImp();
		    Restaurant restaurant = rdi.getRestaurant(restaurantId);
		    req.setAttribute("restList" , restaurant);
		    
		    RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		    rd.forward(req, resp);
		    
		    
		    
		    
		} else {
		    // handle missing or empty parameter
		    // maybe set a default value or return an error
			PrintWriter out = resp.getWriter();
			out.print("Please first add item from menu to cart");
		}
		
		
		
		}
	

}