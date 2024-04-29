<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Doctor List</title>
</head>
<body>
<section>
    <table class="doctors-table">
        <thead>
        <tr>
            <th>Doctor Name</th>
            <th>Specialization</th>
            <th>Available Days</th>
            <th>Available Times</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="doctor" items="${doctors}">
            <tr>
                <td>${doctor.name}</td>
                <td>${doctor.specialization}</td>
                <td>
                    <c:forEach var="day" items="${doctor.availableDays}">
                        ${day}
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="time" items="${doctor.availableTimes}">
                        ${time}
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
<br>
</body>
</html>
