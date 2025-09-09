<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.models.Orders" %>
<%@ page import="com.tap.models.User" %>
<%@ page import="com.tap.models.cart" %>
<%@ page import="com.tap.models.CartItem" %>
<%@ page import="com.tap.models.Restaurant" %>
<%@ page import="com.tap.DaoImpl.RestaurantImp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmed - FoodExpress</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/orders.css">
    
  
</head>
<body>
   
  
    <%
        
        Orders orders = (Orders) session.getAttribute("orders");
        User user = (User) session.getAttribute("user");
        cart cart = (cart) session.getAttribute("cart");
        Integer restaurantIdObj = (Integer) session.getAttribute("restaurantId");
        int restaurantId = (restaurantIdObj != null) ? restaurantIdObj : 0;
        
        Restaurant restaurant = null;
        if (restaurantId > 0) {
            RestaurantImp rdi = new RestaurantImp();
            restaurant = rdi.getRestaurant(restaurantId);
        }
    %>
    
    <div class="container">
        <% if (orders != null) { %>
           
            <div class="header">
                <div class="success-animation">
                    <div class="checkmark-circle">
                        <div class="checkmark"></div>
                    </div>
                </div>
                <h1>🎉 Order Confirmed!</h1>
                <p class="subtitle">Thank you for your order. We're preparing your delicious meal!</p>
                <div class="order-id">
                    Order #<%= String.format("%06d", orders.getOrderId()) %>
                </div>
            </div>
            
           
            <div class="content-grid">
              
                <div class="left-column">
                   
                    <div class="card order-details">
                        <div class="card-header">
                            <i class="fas fa-receipt"></i>
                            <h3>Order Details</h3>
                        </div>
                        <div class="card-content">
                            <div class="detail-row">
                                <span class="label">Order Date:</span>
                                <span class="value"><%= orders.getOrderDate() %></span>
                            </div>
                            <div class="detail-row">
                                <span class="label">Payment Mode:</span>
                                <span class="value payment-mode">
                                    <i class="fas fa-credit-card"></i>
                                    <%= orders.getPaymentMode().toUpperCase() %>
                                </span>
                            </div>
                            <div class="detail-row">
                                <span class="label">Order Status:</span>
                                <span class="value status <%= orders.getStatus().toLowerCase() %>">
                                    <%= orders.getStatus().replace("_", " ").toUpperCase() %>
                                </span>
                            </div>
                            <div class="detail-row">
                                <span class="label">Estimated Delivery:</span>
                                <span class="value delivery-time">
                                    <i class="fas fa-clock"></i>
                                    30-45 minutes
                                </span>
                            </div>
                        </div>
                    </div>

                   
                    <div class="card delivery-address">
                        <div class="card-header">
                            <i class="fas fa-map-marker-alt"></i>
                            <h3>Delivery Address</h3>
                        </div>
                        <div class="card-content">
                            <div class="address-text">
                                <%= request.getParameter("address") != null ? request.getParameter("address") : "Address not provided" %>
                            </div>
                            
                            <% if (user != null) { %>
                                <div class="user-details">
                                    <div class="detail-row">
                                        <span class="label">Customer:</span>
                                        <span class="value"><%= user.getName() %></span>
                                    </div>
                                    <div class="detail-row">
                                        <span class="label">Email:</span>
                                        <span class="value"><%= user.getEmail() %></span>
                                    </div>
                                    <div class="detail-row">
                                        <span class="label">Phone:</span>
                                        <span class="value">
                                            <i class="fas fa-phone"></i>
                                            <%= user.getPhone() %>
                                        </span>
                                    </div>
                                </div>
                            <% } %>
                        </div>
                    </div>

                    <!-- Restaurant Details Card -->
                    <% if (restaurant != null) { %>
                        <div class="card restaurant-info">
                            <div class="card-header">
                                <i class="fas fa-store"></i>
                                <h3>Restaurant Details</h3>
                            </div>
                            <div class="card-content">
                                <div class="restaurant-name"><%= restaurant.getName() %></div>
                                <div class="detail-row">
                                    <span class="label">Address:</span>
                                    <span class="value"><%= restaurant.getAddress() %></span>
                                </div>
                                <div class="detail-row">
                                    <span class="label">Contact:</span>
                                    <span class="value">
                                        <i class="fas fa-phone"></i>
                                        <%= restaurant.getPhone() %>
                                    </span>
                                </div>
                                <div class="detail-row">
                                    <span class="label">Rating:</span>
                                    <span class="value rating">
                                        <i class="fas fa-star"></i>
                                        <%= restaurant.getRating() %>/5
                                    </span>
                                </div>
                            </div>
                        </div>
                    <% } %>
                </div>

                <!-- Right Column -->
                <div class="right-column">
                    <!-- Order Items Card -->
                    <div class="card order-items">
                        <div class="card-header">
                            <i class="fas fa-shopping-bag"></i>
                            <h3>Your Order</h3>
                        </div>
                        <div class="card-content">
                            <% if (cart != null && !cart.getItems().isEmpty()) { %>
                                <div class="items-list">
                                    <% for (CartItem item : cart.getItems().values()) { %>
                                        <div class="order-item">
                                            <div class="item-icon">🍽️</div>
                                            <div class="item-details">
                                                <div class="item-name"><%= item.getName() %></div>
                                                <div class="item-info">
                                                    <span class="quantity">Qty: <%= item.getQuantity() %></span>
                                                    <span class="unit-price">@ ₹<%= item.getPrice() %></span>
                                                </div>
                                            </div>
                                            <div class="item-total">₹<%= item.getPrice() * item.getQuantity() %></div>
                                        </div>
                                    <% } %>
                                </div>
                            <% } else { %>
                                <div class="no-items">
                                    <i class="fas fa-shopping-bag"></i>
                                    <p>No items found in order</p>
                                </div>
                            <% } %>
                        </div>
                    </div>

                    <!-- Order Summary Card -->
                    <div class="card order-summary">
                        <div class="card-header">
                            <i class="fas fa-calculator"></i>
                            <h3>Order Summary</h3>
                        </div>
                        <div class="card-content">
                            <div class="summary-row">
                                <span>Subtotal:</span>
                                <span>₹<%= orders.getTotalAmount() %></span>
                            </div>
                            <div class="summary-row">
                                <span>Delivery Fee:</span>
                                <span>₹40</span>
                            </div>
                            <div class="summary-row taxes">
                                <span>Taxes & Charges:</span>
                                <span>Included</span>
                            </div>
                            <div class="summary-row total">
                                <span>Total Amount:</span>
                                <span>₹<%= orders.getTotalAmount() + 40 %></span>
                            </div>
                        </div>
                    </div>

                    <!-- Order Tracking Card -->
                    <div class="card tracking-section">
                        <div class="card-header">
                            <i class="fas fa-truck"></i>
                            <h3>Order Tracking</h3>
                        </div>
                        <div class="card-content">
                            <p class="tracking-desc">Your order is being prepared. Track the progress below:</p>
                            
                            <div class="tracking-steps">
                                <div class="step completed">
                                    <div class="step-icon">
                                        <i class="fas fa-check"></i>
                                    </div>
                                    <div class="step-content">
                                        <div class="step-title">Order Placed</div>
                                        <div class="step-time">Just now</div>
                                    </div>
                                </div>
                                
                                <div class="step <%= orders.getStatus().equalsIgnoreCase("preparing") || orders.getStatus().equalsIgnoreCase("confirmed") ? "active" : "" %>">
                                    <div class="step-icon">
                                        <i class="fas fa-utensils"></i>
                                    </div>
                                    <div class="step-content">
                                        <div class="step-title">Preparing</div>
                                        <div class="step-time">5-15 mins</div>
                                    </div>
                                </div>
                                
                                <div class="step <%= orders.getStatus().equalsIgnoreCase("out_for_delivery") ? "active" : "" %>">
                                    <div class="step-icon">
                                        <i class="fas fa-motorcycle"></i>
                                    </div>
                                    <div class="step-content">
                                        <div class="step-title">Out for Delivery</div>
                                        <div class="step-time">20-30 mins</div>
                                    </div>
                                </div>
                                
                                <div class="step <%= orders.getStatus().equalsIgnoreCase("delivered") ? "active completed" : "" %>">
                                    <div class="step-icon">
                                        <i class="fas fa-home"></i>
                                    </div>
                                    <div class="step-content">
                                        <div class="step-title">Delivered</div>
                                        <div class="step-time">30-45 mins</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Action Buttons -->
           
            <!-- Footer Note -->
            <div class="footer-note">
                <div class="note-content">
                    <i class="fas fa-info-circle"></i>
                    <div class="note-text">
                        <p><strong>Stay Updated:</strong> You will receive SMS and email updates about your order status.</p>
                        <p><strong>Need Help?</strong> Contact our support team at <strong>+91 1800 123 4567</strong> or email <strong>support@foodexpress.com</strong></p>
                    </div>
                </div>
            </div>
            
            <%
                // Clean up session after displaying order confirmation
                session.removeAttribute("cart"); 
                session.removeAttribute("orders"); 
            %>
            
        <% } else { %>
            <!-- No Order Found -->
            <div class="error-container">
                <div class="error-icon">
                    <i class="fas fa-exclamation-triangle"></i>
                </div>
                <h2>No Order Found</h2>
                <p>Sorry, we couldn't find your order details. Please try placing your order again.</p>
                <div class="error-actions">
                    <a href="GetAllRestaurants" class="btn btn-primary">
                        <i class="fas fa-home"></i>
                        Browse Restaurants
                    </a>
                    <a href="OrdersHistory" class="btn btn-secondary">
                        <i class="fas fa-history"></i>
                        Order History
                    </a>
                </div>
            </div>
        <% } %>
    </div>

    <footer>
        <p>&copy; 2025 FoodExpress. All rights reserved. | <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a></p>
    </footer>

    <script>
        // Add some interactive effects
        document.addEventListener('DOMContentLoaded', function() {
            // Animate success checkmark
            const checkmark = document.querySelector('.checkmark');
            if (checkmark) {
                setTimeout(() => {
                    checkmark.style.opacity = '1';
                    checkmark.style.transform = 'scale(1)';
                }, 500);
            }

            // Animate cards on load
            const cards = document.querySelectorAll('.card');
            cards.forEach((card, index) => {
                card.style.animationDelay = `${index * 0.1}s`;
            });

           
        });
    </script>
</body>
</html>