<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Schedule</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Doctor</th>
        <th>Time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="schedule" items="${schedules}">
        <tr>
            <td><c:out value="${schedule.doctor}"/></td>
            <td><c:out value="${schedule.time}"/></td>
            <td>
                <form action="do/schedule" method="GET">
                    <input type="hidden" name="command" value="edit" />
                    <input type="hidden" name="id" value="${schedule.id}" />
                    <input type="submit" value="edit" />
                </form>
            </td>
            <td>
                <form action="do/schedule" method="POST">
                    <input type="hidden" name="command" value="delete" />
                    <input type="hidden" name="id" value="${schedule.id}" />
                    <input type="submit" value="delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>
<form action="do/schedule" method="POST">
    <input type="hidden" name="command" value="create" />
    Doctor:<input type="text" name="doctor" value="" /><br/>
    Time:<input type="text" name="time" value="" /><br/>
    <input type="submit" value="Add" />
</form>

</body>
</html>
