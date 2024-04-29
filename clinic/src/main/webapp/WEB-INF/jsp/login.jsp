<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<header>
    <c:if test="${empty user}">
        <form class="login-form" action="login" method="POST">
            <input type="text" name="login" value="" placeholder="Username" />
            <input type="password" name="password" value="" placeholder="Password" />
            <input type="submit" value="Log In" />
        </form>
    </c:if>
</header>
</body>
</html>
