<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctors</title>
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
</head>
<body>
<h1>Doctors</h1>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Speciality</th>
        <th>Schedule</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="doctor" items="${doctors}">
        <tr>
            <td><c:out value="${doctor.name}"/></td>
            <td><c:out value="${doctor.specialty}"/></td>
            <td>
                <c:forEach var="schedule" items="${doctor.schedules}">
                    <p><c:out value="${schedule.time}"/></p>
                    <form action="doctors" method="GET">
                        <input type="hidden" name="command" value="showEditFormSchedule" />
                        <input type="hidden" name="timeId" value="${schedule.timeId}" />
                        <input type="submit" value="Edit" />
                    </form>
                    <form action="doctors" method="POST">
                        <input type="hidden" name="command" value="deleteSchedule" />
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
</body>
</html>
