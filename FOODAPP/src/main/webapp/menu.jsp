<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.DaoImpl.MenuImpl" %>
<%@ page import="com.tap.models.Menu" %>
<%@ page import="com.tap.models.Restaurant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodExpress - Menu</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- CSS Link - try one of these based on your project structure -->
    <link rel="stylesheet" href="CSS/Menu.css">
    
    <!-- Alternative paths if the above doesn't work:
    <link rel="stylesheet" href="css/Menu.css">
    <link rel="stylesheet" href="../css/Menu.css">
    <link rel="stylesheet" href="/YourProjectName/css/Menu.css">
    -->
</head>
<body>
   
    <%
    Restaurant restaurant = (Restaurant) request.getAttribute("restList");
    if (restaurant != null) {
    %>
    <div class="restaurant-info">
        <h1><%= restaurant.getName() %></h1>
        <div class="restaurant-details">
            <div class="detail-item">
                <div class="detail-label">Location</div>
                <div class="detail-value">📍 <%= restaurant.getAddress() %></div>
            </div>
            <div class="detail-item">
                <div class="detail-label">Rating</div>
                <div class="detail-value">⭐ <%= restaurant.getRating() %> / 5</div>
            </div>
            <div class="detail-item">
                <div class="detail-label">Cuisine</div>
                <div class="detail-value">🍽️ <%= restaurant.getCusineType() %></div>
            </div>
        </div>
    </div>
    <% } %>

    <div class="menu-section">
        <h2>Our Delicious Menu</h2>

        <div class="menu-grid">
            <%
            List<Menu> menuItems = (List<Menu>) request.getAttribute("menuList");
            
            if (menuItems != null && !menuItems.isEmpty()) {
                for (Menu item : menuItems) {
            %>
            <div class="menu-item">
                <div class="image-container">
                    <img src="<%= request.getContextPath() %>/<%= item.getImagePath() != null && !item.getImagePath().isEmpty() ? item.getImagePath() : "images/default-food.jpg" %>"
                         alt="<%= item.getItemName() %>"
                         onerror="this.style.display='none'; this.parentNode.querySelector('.image-placeholder').style.display='flex';">
                    <div class="image-placeholder" style="display: none;">
                        🍽️
                      
                    </div>
                </div>
                <div class="item-content">
                    <div class="item-header">
                        <div class="item-name"><%= item.getItemName() %></div>
                        <div class="item-description"><%= item.getDescription() != null && !item.getDescription().isEmpty() ? item.getDescription() : "Delicious item from our kitchen" %></div>
                    </div>
                    <div class="item-footer">
                        <div class="item-price">₹<%= item.getPrice() %></div>
                        <form action="cart" method="post" class="add-to-cart-form">
                            <input type="hidden" name="itemId" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                            <input type="hidden" name="action" value="add">
                            <input type="submit" value="Add To Cart">
                        </form>
                    </div>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <div class="coming-soon">
                <h2>🍽️ Menu Coming Soon</h2>
                <p>This restaurant is currently updating their menu. Please check back shortly!</p>
            </div>
            <%
            }
            %>
        </div>
    </div>

    <footer>
        <p>&copy; 2025 FoodExpress. All rights reserved. | <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a></p>
    </footer>
</body>
</html>