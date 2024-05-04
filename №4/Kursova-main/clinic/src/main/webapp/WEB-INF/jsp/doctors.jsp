<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Doctors</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Speciality</th>
        <th>Name</th>
        <th>Schedule</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="doctor" items="${doctors}">
        <tr>
            <td><c:out value="${doctor.speciality}"/></td>
            <td><c:out value="${doctor.name}"/></td>
            <td><c:out value="${doctor.schedule}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
