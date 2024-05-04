<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>
<header>
    <c:if test="${!empty user}">
        <form class="login-form" action="logout" method="POST">
            <c:out value="${user.name}"/><br>
            <input type="submit" value="Log Out" />
        </form>
    </c:if>
    <c:if test="${empty user}">
        <form class="login-form" action="login" method="POST">
            <input type="text" name="login" value="" />
            <input type="password" name="password" value="" />
            <input type="submit" value="Log In" />
        </form>
    </c:if>
</header>