<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Error</h1>

<p style="color: red;"><%= request.getAttribute("message") %></p>

<p><a href="<%= request.getContextPath() %>">Back to Home</a></p>
</body>
</html>
