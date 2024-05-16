<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit appointment</title>
</head>
<body>
<form action="saveSchedule" method="POST">
    <c:forEach var="schedule" items="${schedules}">
        <input type="hidden" name="id" value="${schedule.timeId}" />
        <input type="hidden" name="command" value="save" />
        Doctor ID: <input type="number" name="doctorId" value="${schedule.doctorId}" /><br>
        Time: <input type="text" name="time" value="${schedule.time}" /><br>
        <input type="submit" value="Save" />
        <br><br>
    </c:forEach>
</form>
</body>
</html>
