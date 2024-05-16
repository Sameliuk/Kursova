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
            color: #008000;
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
                    <p><c:out value="${schedule.time}"/></p>

                    <form action="editSchedule" method="GET">
                        <input type="hidden" name="timeId" value="${schedule.timeId}" />
                        <input type="submit" value="Edit" />
                    </form>

                    <form action="deleteSchedule" method="POST">
                        <input type="hidden" name="doctorId" value="${schedule.doctorId}" />
                        <input type="hidden" name="timeId" value="${schedule.timeId}" />
                        <input type="submit" value="Delete"/>
                    </form>
                </c:forEach>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<hr>
<form action="createSchedule" method="POST">
    Doctor id:<input type="text" name="doctorId" value="" /><br/>
    Time:<input type="text" name="time" value="" /><br/>
    <input type="submit" value="Add" />
</form>
</body>
</html>
