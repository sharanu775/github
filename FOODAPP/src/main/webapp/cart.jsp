<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.tap.models.cart" %>
<%@ page import="com.tap.models.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodExpress - Shopping Cart</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/Cart.css">
    
    <!-- Alternative CSS paths if the above doesn't work:
    <link rel="stylesheet" href="css/Cart.css">
    <link rel="stylesheet" href="../css/Cart.css">
    -->
</head>
<body>
    
    <div class="container">
        <div class="cart-header">
            <h1>🛒 Your Shopping Cart</h1>
            <div class="cart-subtitle">Review your delicious selections</div>
        </div>

        <%
        // Get cart and restaurant ID from session
        cart cart = (cart) session.getAttribute("cart");
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");
        
        // Check if cart exists and is not empty
        if (cart != null && !cart.getItems().isEmpty()) {
        %>
            <div class="cart-items-container">
                <% for (CartItem item : cart.getItems().values()) { %>
                    <div class="cart-item">
                        <div class="item-image">
                            <div class="food-icon">🍽️</div>
                        </div>
                        
                        <div class="cart-item-details">
                            <h3 class="item-name"><%= item.getName() %></h3>
                            <div class="price-info">
                                <span class="unit-price">₹<%= item.getPrice() %> each</span>
                                <span class="total-price">₹<%= item.getPrice() * item.getQuantity() %></span>
                            </div>
                        </div>
                        
                        <div class="quantity-controls">
                            <!-- Decrease quantity form -->
                            <form action="cart" method="post" style="display: inline;">
                                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                                <button type="submit" class="quantity-btn minus" 
                                    <% if(item.getQuantity() == 1) { %> disabled <% } %>>
                                    −
                                </button>
                            </form>
                            
                            <span class="quantity-display"><%= item.getQuantity() %></span>
                            
                            <!-- Increase quantity form -->
                            <form action="cart" method="post" style="display: inline;">
                                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                                <button type="submit" class="quantity-btn plus">+</button>
                            </form>
                        </div>
                        
                        <!-- Remove item form -->
                        <div class="remove-item">
                            <form action="cart" method="post">
                                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                                <input type="hidden" name="action" value="remove">
                                <button type="submit" class="remove-btn" title="Remove item">🗑️</button>
                            </form>
                        </div>
                    </div>
                <% } %>
            </div>
            
            <!-- Cart Summary -->
            <div class="cart-summary">
                <div class="summary-row">
                    <span>Items in cart:</span>
                    <span><%= cart.getItems().size() %></span>
                </div>
                <div class="summary-row total-row">
                    <span>Grand Total:</span>
                    <span class="grand-total">₹<%= cart.getTotalPrice() %></span>
                </div>
            </div>
            
            <!-- Action buttons -->
            <div class="cart-actions">
                <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="btn add-more">
                    ➕ Add More Items
                </a>
                
                <form action="checkout.jsp" method="post" style="display: inline;">
                    <button type="submit" class="btn checkout">
                        🚀 Proceed to Checkout
                    </button>
                </form>
            </div>
            
        <% } else { %>
            <!-- Empty cart message -->
            <div class="empty-cart">
                <div class="empty-cart-icon">🛒</div>
                <h2>Your cart is empty</h2>
                <p>Looks like you haven't added any delicious items yet!</p>
                
                <div class="empty-cart-actions">
                    <% if (session.getAttribute("restaurantId") != null) { %>
                        <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="btn add-items">
                            🍽️ Browse Menu
                        </a>
                    <% } %>
                    <a href="GetAllRestaurants" class="btn browse-restaurants">
                        🏪 Browse Restaurants
                    </a>
                </div>
            </div>
        <% } %>
    </div>

    <footer>
        <p>&copy; 2025 FoodExpress. All rights reserved. | <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a></p>
    </footer>
</body>
</html>