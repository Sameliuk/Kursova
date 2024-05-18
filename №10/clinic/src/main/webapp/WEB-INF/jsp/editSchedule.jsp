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
        <input type="hidden" name="timeId" value="${schedule.timeId}" />
        Doctor ID: <input type="number" name="doctorId" <c:out value="${schedule.doctorId}"/> /><br>
        Time: <input type="text" name="time" <c:out value="${schedule.time}"/> /><br>
        <input type="submit" value="Save" />
        <br><br>
</form>
</body>
</html>
