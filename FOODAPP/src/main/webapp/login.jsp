<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="CSS/Login.css">
</head>
<body>
    <div class="login-container">
        <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if(errorMessage == null) {
            // If using session approach
            errorMessage = (String) session.getAttribute("errorMessage");
            session.removeAttribute("errorMessage"); // Remove after displaying
        }
        if(errorMessage != null) {
        %>
        <div class="error-message">
            <%= errorMessage %>
        </div>
        <%
        }
        %>
        
        <h2>Login</h2>
        
        <form action="LoginServlet" method="post">
            <div class="input-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required>
            </div>

            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <button type="submit">Login</button>
        </form>

        <p class="register-link">
            Don't have an account?
            <a href="register.jsp">Create New Account</a>
        </p>
    </div>
</body>
</html>