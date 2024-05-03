<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Appointment</title>
</head>
<body>
<%@include file="login.jsp"%>
<table border="1">
    <thead>
    <tr>
        <th>Doctor</th>
        <th>Time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="appointment" items="${appointments}">
        <tr>
            <td><c:out value="${appointment.doctor}"/></td>
            <td><c:out value="${appointment.time}"/></td>
            <td>
                <form action="do/appointment" method="GET">
                    <input type="hidden" name="command" value="edit" />
                    <input type="hidden" name="id" value="${appointment.id}" />
                    <input type="submit" value="edit" />
                </form>
            </td>
            <td>
                <form action="do/appointment" method="POST">
                    <input type="hidden" name="command" value="delete" />
                    <input type="hidden" name="id" value="${appointment.id}" />
                    <input type="submit" value="delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>
<form action="do/appointment" method="POST">
    <input type="hidden" name="command" value="create" />
    Doctor:<input type="text" name="doctor" value="" /><br/>
    Time:<input type="text" name="time" value="" /><br/>
    <input type="submit" value="Add" />
</form>

</body>
</html>
