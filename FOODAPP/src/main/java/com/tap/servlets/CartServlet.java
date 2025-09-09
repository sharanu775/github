package com.tap.servlets;

import java.io.IOException;

import com.tap.DaoImpl.MenuImpl;
import com.tap.models.cart;
import com.tap.models.CartItem;
import com.tap.models.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	private static final long serialVersionUID = -3860998832952027740L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		cart cart =(cart) session.getAttribute("cart");



//		int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));
//		Integer currentRestaurantId =(Integer) session.getAttribute("restaurantId");
//
//		if(cart==null || newRestaurantId != currentRestaurantId ) {
//			cart=new Cart();
//			session.setAttribute("cart", cart);
//			session.setAttribute("restaurantId", newRestaurantId);
//		}
		
		
		
		 String restaurantIdParam = req.getParameter("restaurantId");
	        if (restaurantIdParam != null) {
	            int newRestaurantId = Integer.parseInt(restaurantIdParam);
	            Integer currentRestaurantId = (Integer) session.getAttribute("restaurantId");

	            if (cart == null || newRestaurantId != currentRestaurantId) {
	                cart = new cart();
	                session.setAttribute("cart", cart);
	                session.setAttribute("restaurantId", newRestaurantId);
	            }
	        }
		
		String action = req.getParameter("action");
		
		if(action.equals("add")) {
			addItemToCart(req,cart);
		}
		else if(action.equals("update")) {
			updateCartItem(req,cart);
		}
		else if(action.equals("remove")) {
			deleteItemFromCart(req,cart);
		}
		
		

		//		if(restaurantIdParam !=null) {
		//			int newRestaurantId=Integer.parseInt(restaurantIdParam);
		//			
		//			if(currentRestaurantId == null || !currentRestaurantId.equals(newRestaurantId)) {
		//				cart=new Cart();
		//				session.setAttribute("cart", cart);
		//				session.setAttribute("restaurantId", newRestaurantId);
		//			}
		//		}
		
//		resp.sendRedirect("cart.jsp");
		
		RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
		rd.forward(req, resp);
		


	}
	private void addItemToCart(HttpServletRequest req, cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuImpl mdi = new MenuImpl();
		Menu menu = mdi.getMenu(itemId);
//		System.out.println(menu);
		
		if(menu !=null) {
			CartItem item = new CartItem(
					menu.getMenuId(),
					menu.getItemName(),
					menu.getPrice(),
					quantity
					);
			cart.addCartItem(item);
		}
		
		
	}


	private void updateCartItem(HttpServletRequest req, cart cart) {
			int itemId = Integer.parseInt(req.getParameter("itemId"));
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			cart.updateCartItem(itemId, quantity);
	
	}
	
	

	private void deleteItemFromCart(HttpServletRequest req, cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeCartItem(itemId);
		
		
	}
		
	
	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		cart cart =(cart) session.getAttribute("cart");



//		int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));
//		Integer currentRestaurantId =(Integer) session.getAttribute("restaurantId");
//
//		if(cart==null || newRestaurantId != currentRestaurantId ) {
//			cart=new Cart();
//			session.setAttribute("cart", cart);
//			session.setAttribute("restaurantId", newRestaurantId);
//		}
		
		
		
		 String restaurantIdParam = req.getParameter("restaurantId");
	        if (restaurantIdParam != null) {
	            int newRestaurantId = Integer.parseInt(restaurantIdParam);
	            Integer currentRestaurantId = (Integer) session.getAttribute("restaurantId");

	            if (cart == null || newRestaurantId != currentRestaurantId) {
	                cart = new cart();
	                session.setAttribute("cart", cart);
	                session.setAttribute("restaurantId", newRestaurantId);
	            }
	        }
		
		String action = req.getParameter("action");
		
		if(action.equals("add")) {
			addItemToCart(req,cart);
		}
		else if(action.equals("update")) {
			updateCartItem(req,cart);
		}
		else if(action.equals("remove")) {
			deleteItemFromCart(req,cart);
		}
		else if(action.equals("show")) {
//			RequestDispatcher rd = req.getRequestDispatcher("cart.jsp");
//			rd.forward(req, resp);
			resp.sendRedirect("cart.jsp");
			return;
		}
		else {
			
		}
		//		if(restaurantIdParam !=null) {
		//			int newRestaurantId=Integer.parseInt(restaurantIdParam);
		//			
		//			if(currentRestaurantId == null || !currentRestaurantId.equals(newRestaurantId)) {
		//				cart=new Cart();
		//				session.setAttribute("cart", cart);
		//				session.setAttribute("restaurantId", newRestaurantId);
		//			}
		//		}
		
		resp.sendRedirect("cart.jsp");
	}

}