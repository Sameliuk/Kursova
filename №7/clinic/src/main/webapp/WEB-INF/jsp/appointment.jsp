<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
    <title>Appointments</title>
<body>
<%@include file="../../header.jspf"%>
<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

<ul>
    <li><a href="doctors">View Doctors</a></li>
</ul>

<c:if test="${!empty user}">
    <c:forEach var="appointment" items="${user.appointment}">
    <form action="appointment" method="POST">
        <div>${appointment.user.name}</div>
        <input type="hidden" name="userId" value="${appointment.user.userId}" />
        <input type="hidden" name="userId" value="${appointment.time}" />
        <input type="submit" value="Add appointment" />
    </form>
    </c:forEach>
</c:if>
<hr>
<c:forEach var="appointment" items="${user.appointment}">
    <div>
        <c:out value="${appointment.user.name}"/>
        <c:out value="${appointment.time}"/>
    </div>
    <br>
</c:forEach>
</body>
</html>




