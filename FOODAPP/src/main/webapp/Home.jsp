<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.tap.models.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Featured Restaurants</title>
    <link rel="stylesheet" href="CSS/Home.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1><i class="fas fa-utensils"></i> Featured Restaurants</h1>
            <p>Discover amazing culinary experiences near you</p>
        </div>

        <%
        // Retrieve the list of restaurants passed from the servlet
        List<Restaurant> restaurants = (List<Restaurant>)request.getAttribute("restaurants");

        // Check if the list exists and contains any restaurants
        if (restaurants != null && !restaurants.isEmpty()) {
        %>
            <div class="restaurants-grid">
            <%
            // Iterate over each Restaurant object in the list
            for(Restaurant r : restaurants) {
            %>
                <div class="restaurant-card">
                    <div class="card-image-container">
                        <img class="card-image" 
                             src="<%= r.getImagePath() %>" 
                             alt="<%= r.getName() %>"
                             onerror="this.onerror=null; this.src='data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iI2Y4ZjlmYSIvPjx0ZXh0IHg9IjUwJSIgeT0iNTAlIiBmb250LXNpemU9IjE4IiBmaWxsPSIjNmM3NTdkIiB0ZXh0LWFuY2hvcj0ibWlkZGxlIiBkeT0iLjNlbSI+SW1hZ2UgTm90IEF2YWlsYWJsZTwvdGV4dD48L3N2Zz4=';">
                        <div class="image-overlay"></div>
                        <div class="rating-badge">
                            <i class="fas fa-star"></i>
                            <%= r.getRating() %>
                        </div>
                    </div>

                    <div class="card-content">
                        <a href="menu?restaurantId=<%= r.getRestaurantId() %>" class="restaurant-name">
                            <%= r.getName() %>
                        </a>

                        <div class="info-grid">
                            <div class="info-item">
                                <i class="fas fa-utensils info-icon cuisine-icon"></i>
                                <span class="info-label">Cuisine:</span>
                                <span class="info-value"><%= r.getCusineType() %></span>
                            </div>

                            <div class="info-item">
                                <i class="fas fa-clock info-icon time-icon"></i>
                                <span class="info-label">Delivery:</span>
                                <span class="info-value"><%= r.getEta() %></span>
                            </div>

                            <div class="info-item">
                                <i class="fas fa-hashtag info-icon id-icon"></i>
                                <span class="info-label">ID:</span>
                                <span class="info-value"><%= r.getRestaurantId() %></span>
                            </div>
                        </div>
                    </div>
                </div>
            <%
            } // End of for loop
            %>
            </div>
        <%
        } else {
        %>
            <div class="no-restaurants">
                <i class="fas fa-utensils"></i>
                <h2>No Restaurants Available</h2>
                <p>We're currently updating our restaurant listings. Please check back soon!</p>
            </div>
        <%
        } // End of if-else block
        %>
    </div>
</body>
</html>