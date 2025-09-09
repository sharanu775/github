<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create New Account</title>
    <link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
    <link rel="stylesheet" href="CSS/Account.css">
</head>
<body>
    <div class="registration-container">
        <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if(errorMessage == null) {
            errorMessage = (String) session.getAttribute("errorMessage");
            session.removeAttribute("errorMessage");
        }
        if(errorMessage != null) {
        %>
        <div class="error-message">
            <%= errorMessage %>
        </div>
        <%
        }
        %>

        <h2>Create New Account</h2>
        
        <form action="RegisterServlet" method="post">
            <div class="form-row">
                <div class="form-group name">
                    <label for="name">Full Name:</label>
                    <input type="text" id="name" name="name" placeholder="Enter Your Name" required>
                </div>

                <div class="form-group username">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" placeholder="Choose a username" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group password">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" placeholder="Create a password" required>
                </div>

                <div class="form-group email">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group phone">
                    <label for="phone">Phone:</label>
                    <input type="tel" id="phone" name="phone" placeholder="Enter Phone No" required>
                </div>

                <div class="form-group role">
                    <label for="role">Role:</label>
                    <select id="role" name="role" required>
                        <option value="">-- Select Role --</option>
                        <option value="customer">Customer</option>
                        <option value="superAdmin">Admin</option>
                        <option value="restaurantAdmin">Restaurant Admin</option>
                        <option value="deliveryAgent">Delivery Agent</option>
                    </select>
                </div>
            </div>

            <div class="form-group address full-width">
                <label for="address">Address:</label>
                <textarea id="address" name="address" rows="3" placeholder="Enter address" required></textarea>
            </div>

            <button type="submit">Register</button>
        </form>

        <p class="login-link">
            Already have an account? 
            <a href="login.jsp">Login Here</a>
        </p>
    </div>
</body>
</html>