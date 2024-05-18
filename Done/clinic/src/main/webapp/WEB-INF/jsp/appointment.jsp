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
        title{
            color: mediumseagreen;
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
            background-color: lightgreen;
        }
        .actions {
            display: flex;
            align-items: center;
        }
        .actions form {
            margin-left: 10px;
        }
        .actions form:first-child {
            margin-left: 0;
        }
        .btn {
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            background-color: mediumseagreen; /* Button color */
            color: white;
            text-align: center;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #388e3c; /* Darker green on hover */
        }
        form {
            margin-top: 5px;
            display: inline;
        }
    </style>
    <title>Appointments</title>
<body>
<%@include file="../../header.jspf"%>
<br>
<a href="doctors">View Doctors</a>
<br>
<hr>
<table>
    <thead>
    <tr>
        <th>Patient</th>
        <th>Doctor</th>
        <th>Time</th>
        <th>Action</th>
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
                <div class="actions">
            <form action="deleteAppointment" method="POST">
                <input type="hidden" name="user" value="${appointment.user.name}" />
                <input type="hidden" name="appointmentId" value="${appointment.appointmentId}" />
                <input type="submit" value="Delete" class="btn"/>
            </form>
                    </div>
            </td>
            </c:forEach>
        </tr>

    </tbody>
</table>
<hr>


<form action="createAppointment" method="POST">
    <input type="hidden" name="user" value="${appointment.user.name}" />
    <label for="doctorId">Doctor:</label>
    <select name="doctorId" id="doctorId">
        <option value="">Select Doctor</option>
        <c:forEach items="${doctors}" var="doctor">
            <option value="${doctor.doctorId}">${doctor.name}</option>
        </c:forEach>
    </select><br/>
    <label for="time">Time:</label>
    <select name="time" id="time">
        <option value="">Select Time</option>
<c:forEach items="${doctors}" var="doctor">
        <c:forEach items="${doctor.schedules}" var="schedule">
            <option value="${schedule.time}">${schedule.time} (${doctor.name})</option>
        </c:forEach>
</c:forEach>
    </select><br/>
    <input type="submit" value="Add" class="btn"/>
</form>
</body>
</html>




