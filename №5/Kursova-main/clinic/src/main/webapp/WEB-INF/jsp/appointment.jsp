<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment</title>
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
        form {
            margin-bottom: 20px;
        }
        input[type="text"], input[type="submit"] {
            padding: 8px;
            margin: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Appointments</h1>
<table>
    <thead>
    <tr>
        <th>Doctor</th>
        <th>Time</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="appointment" items="${appointments}">
        <tr>
            <td><c:out value="${appointment.doctor}"/></td>
            <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${appointment.time}" /></td>
            <td>
                <form action="do/editAppointment" method="GET">
                    <input type="hidden" name="command" value="editAppointment" />
                    <input type="hidden" name="appointmentId" value="${appointment.appointmentId}" />
                    <input type="submit" value="Edit" />
                </form>
            </td>
            <td>
                <form action="do/deleteAppointment" method="POST">
                    <input type="hidden" name="command" value="deleteAppointment" />
                    <input type="hidden" name="appointmentId" value="${appointment.appointmentId}" />
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>
<h2>Add Appointment</h2>
<form action="do/createAppointment" method="POST">
    <input type="hidden" name="command" value="createAppointment" />
    <label for="doctor">Doctor:</label>
    <input type="text" id="doctor" name="doctor" required/><br/>
    <label for="time">Time:</label>
    <input type="text" id="time" name="time" placeholder="YYYY-MM-DD HH:MM:SS" required/><br/>
    <input type="submit" value="Add" />
</form>
</body>
</html>
