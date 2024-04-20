<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Doctors</title>
</head>
<body>
<h1>List of Doctors</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Specialty</th>
        <!-- Додайте інші потрібні поля -->
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="doctors" scope="request" type="java.util.List"/>
    <c:forEach items="${doctors}" var="doctor">
        <tr>
            <td>${doctor.id}</td>
            <td>${doctor.name}</td>
            <td>${doctor.specialty}</td>
            <!-- Додайте інші поля, які потрібно відображати -->
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
