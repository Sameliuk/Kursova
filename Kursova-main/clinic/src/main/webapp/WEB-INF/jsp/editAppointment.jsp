<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit appointment</title>
</head>
<body>
<form action="do/editAppointment" method="POST">
    <input type="hidden" name="id" value="${appointment.id}" />
    <input type="hidden" name="command" value="save" />
    Doctor: <input type="text" name="doctor" value="<c:out value="${appointment.doctor}"/>" /><br>
    Time:<input type="text" name="time" value="<c:out value="${appointment.time}"/>" /><br>
    <input type="submit" value="Save" />
</form>
</body>
</html>
