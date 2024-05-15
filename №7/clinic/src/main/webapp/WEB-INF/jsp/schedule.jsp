<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Schedule</title>
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
<h1>Schedule</h1>
<p><c:out value="${schedules}"/> 123  </p>

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
    <c:forEach var="schedule" items="${schedules}">
        <tr>
            <td><c:out value="${schedule.doctor}"/></td>
            <td><c:out value="${schedule.time}"/></td>
            <td>
                <form action="editSchedule" method="GET">
                    <input type="hidden" name="command" value="editSchedule" />
                    <input type="hidden" name="id" value="${schedule.id}" />
                    <input type="submit" value="Edit" />
                </form>
            </td>
            <td>
                <form action="deleteSchedule" method="POST">
                    <input type="hidden" name="command" value="deleteSchedule" />
                    <input type="hidden" name="id" value="${schedule.id}" />
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>
<h2>Add Schedule</h2>
<form action="createSchedule" method="POST">
    <input type="hidden" name="command" value="createSchedule" />
    Doctor:<input type="text" name="doctor" required/><br/>
    Time:<input type="text" name="time" placeholder="YYYY-MM-DD HH:MM:SS" required/><br/>
    <input type="submit" value="Add" />
</form>

</body>
</html>
