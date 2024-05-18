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
<a href="doctors">View Doctors</a>
<table>
    <thead>
    <tr>
        <th>Patient</th>
        <th>Doctor</th>
        <th>Time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="appointment" items="${appointments}">
        <tr>
            <td>
                <p><c:out value="${appointment.user.name}"/></p>
            </td>
            <td>
                <p><c:out value="${appointment.doctor.name}"/></p>
            </td>
            <td>
                <p><c:out value="${appointment.time}"/></p>
            </td>
            <td>
            <form action="deleteAppointment" method="POST">
                <input type="hidden" name="user" value="${appointment.user.name}" />
                <input type="hidden" name="appointmentId" value="${appointment.appointmentId}" />
                <input type="submit" value="Delete"/>
            </form>
            </td>
            </c:forEach>
        </tr>

    </tbody>
</table>
<hr>


<form action="createAppointment" method="POST">
    <input type="hidden" name="user" value="${appointment.user.name}" />
    Doctor Id:<input type="number" name="doctorId" <c:out value="${doctor.doctorId}"/> /><br/>
    Time:<input type="text" name="time" value="" /><br/>
    <input type="submit" value="Add" />
</form>
</body>
</html>




