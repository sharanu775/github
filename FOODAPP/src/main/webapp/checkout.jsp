<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.tap.models.cart" %>
<%@ page import="com.tap.models.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodExpress - Checkout</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/checkout.css">
    
    <!-- Alternative CSS paths if the above doesn't work:
    <link rel="stylesheet" href="css/checkout.css">
    <link rel="stylesheet" href="../css/checkout.css">
    -->
</head>
<body>
    <!-- Navigation -->
  

    <!-- Checkout Container -->
    <div class="checkout-container">
        <div class="checkout-header">
            <h1>🛒 Checkout</h1>
            <p>Complete your order details</p>
        </div>

        <div class="checkout-content">
            <!-- Order Summary -->
            <div class="order-summary">
                <h2>📋 Order Summary</h2>
                <%
                cart cart = (cart) session.getAttribute("cart");
                if (cart != null && !cart.getItems().isEmpty()) {
                %>
                    <div class="order-items">
                        <% for (CartItem item : cart.getItems().values()) { %>
                            <div class="order-item">
                                <div class="item-info">
                                    <span class="item-name"><%= item.getName() %></span>
                                    <span class="item-quantity">× <%= item.getQuantity() %></span>
                                </div>
                                <span class="item-total">₹<%= item.getPrice() * item.getQuantity() %></span>
                            </div>
                        <% } %>
                    </div>
                    
                    <div class="order-totals">
                        <div class="total-row">
                            <span>Subtotal:</span>
                            <span>₹<%= cart.getTotalPrice() %></span>
                        </div>
                        <div class="total-row">
                            <span>Delivery Charge:</span>
                            <span>₹40</span>
                        </div>
                        <div class="total-row final-total">
                            <span>Total Amount:</span>
                            <span>₹<%= cart.getTotalPrice() + 40 %></span>
                        </div>
                    </div>
                <% } else { %>
                    <div class="empty-cart-message">
                        <p>No items in cart</p>
                        <a href="GetAllRestaurants" class="btn secondary">Browse Restaurants</a>
                    </div>
                <% } %>
            </div>

            <!-- Checkout Form -->
            <div class="checkout-form">
                <h2>📍 Delivery Details</h2>
                
                <form action="checkout" method="post" id="checkoutForm">
                    <div class="form-group">
                        <label for="address">
                            <span class="label-text">Delivery Address</span>
                            <span class="required">*</span>
                        </label>
                        <textarea 
                            id="address" 
                            name="address" 
                            rows="3" 
                            placeholder="Enter your complete delivery address..." 
                            required
                        ></textarea>
                        <div class="field-hint">Include landmark for easy delivery</div>
                    </div>

                    <div class="form-group">
                        <label for="paymentMode">
                            <span class="label-text">Payment Mode</span>
                            <span class="required">*</span>
                        </label>
                        <div class="select-wrapper">
                            <select id="paymentMode" name="paymentMode" required>
                                <option value="">-- Select Payment Mode --</option>
                                <option value="cod">💵 Cash on Delivery</option>
                                <option value="upi">📱 UPI</option>
                                <option value="card">💳 Credit / Debit Card</option>
                                <option value="netbanking">🏦 Net Banking</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="phone">
                            <span class="label-text">Contact Number</span>
                            <span class="required">*</span>
                        </label>
                        <input 
                            type="tel" 
                            id="phone" 
                            name="phone" 
                            placeholder="Enter your mobile number" 
                            pattern="[0-9]{10}" 
                            required
                        >
                        <div class="field-hint">10-digit mobile number</div>
                    </div>

                    <div class="form-group">
                        <label for="specialInstructions">
                            <span class="label-text">Special Instructions</span>
                            <span class="optional">(Optional)</span>
                        </label>
                        <textarea 
                            id="specialInstructions" 
                            name="specialInstructions" 
                            rows="2" 
                            placeholder="Any special instructions for the restaurant or delivery..."
                        ></textarea>
                    </div>

                    <!-- Delivery Charge Notice -->
                    <div class="delivery-notice">
                        <div class="notice-icon">ℹ️</div>
                        <div class="notice-text">
                            <strong>Note:</strong> ₹40 delivery charge will be added to your order
                        </div>
                    </div>

                    <!-- Place Order Button -->
                    <button type="submit" class="place-order-btn" id="placeOrderBtn">
                        <span class="btn-icon">🚀</span>
                        <span class="btn-text">Place Order</span>
                        <% if (cart != null) { %>
                            <span class="btn-amount">₹<%= cart.getTotalPrice() + 40 %></span>
                        <% } %>
                    </button>
                </form>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2025 FoodExpress. All rights reserved. | <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a></p>
    </footer>

    <script>
        // Form validation and enhancement
        document.getElementById('checkoutForm').addEventListener('submit', function(e) {
            const address = document.getElementById('address').value.trim();
            const paymentMode = document.getElementById('paymentMode').value;
            const phone = document.getElementById('phone').value.trim();
            
            if (!address || !paymentMode || !phone) {
                e.preventDefault();
                alert('Please fill in all required fields');
                return false;
            }
            
            if (phone.length !== 10 || !/^\d+$/.test(phone)) {
                e.preventDefault();
                alert('Please enter a valid 10-digit phone number');
                return false;
            }
            
            // Show loading state
            const btn = document.getElementById('placeOrderBtn');
            btn.innerHTML = '<span class="btn-icon">⏳</span><span class="btn-text">Placing Order...</span>';
            btn.disabled = true;
        });

        // Auto-resize textarea
        document.getElementById('address').addEventListener('input', function() {
            this.style.height = 'auto';
            this.style.height = this.scrollHeight + 'px';
        });
    </script>
</body>
</html>