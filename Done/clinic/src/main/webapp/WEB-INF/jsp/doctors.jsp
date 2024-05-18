<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clinic</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: seagreen;
        }
        h2 {
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

        .schedule-actions {
            display: flex;
            align-items: center;
        }
        .schedule-actions form {
            margin-left: 10px;
        }
        .schedule-actions form:first-child {
            margin-left: 0;
        }
    </style>
</head>
<body>
<h1>CLINIC</h1>
<%@include file="../../header.jspf"%>
<br>
<a href="appointment">Appointment</a>
<br>
<h2>Doctors</h2>
<hr>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Speciality</th>
        <th>Schedule</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="doctor" items="${doctors}">
        <tr>
            <td><c:out value="${doctor.doctorId}"/></td>
            <td><c:out value="${doctor.name}"/></td>
            <td><c:out value="${doctor.specialty}"/></td>
            <td>
                <c:forEach var="schedule" items="${doctor.schedules}">
                    <div class="schedule-actions">
                        <p><c:out value="${schedule.time}"/></p>

                        <form action="editSchedule" method="GET">
                            <input type="hidden" name="timeId" value="${schedule.timeId}" />
                            <input type="submit" value="Edit" class="btn" />
                        </form>

                        <form action="deleteSchedule" method="POST">
                            <input type="hidden" name="doctorId" value="${doctor.doctorId}" />
                            <input type="hidden" name="timeId" value="${schedule.timeId}" />
                            <input type="submit" value="Delete" class="btn" />
                        </form>
                    </div>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<hr>
<form action="createSchedule" method="POST">
    <label for="doctorId">Doctor:</label>
    <select name="doctorId" id="doctorId">
        <option value="">Select Doctor</option>
        <c:forEach items="${doctors}" var="doctor">
            <option value="${doctor.doctorId}">${doctor.name}</option>
        </c:forEach>
    </select><br/>
    Time:<input type="text" name="time" value="" /><br/>
    <input type="submit" value="Add" class="btn" />
</form>
</body>
</html>
