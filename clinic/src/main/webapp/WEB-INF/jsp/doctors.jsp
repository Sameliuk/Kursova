<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Doctor Details</title>
</head>
<body>
<h1>Doctor Details</h1>
<h3>Name: <%= request.getAttribute("doctorName") %></h3>
<h3>Specialty: <%= request.getAttribute("doctorSpecialty") %></h3>
<h3>Schedule: <%= request.getAttribute("doctorSchedule") %></h3>

<a href="<%= request.getContextPath() %>/do/">Back to Home</a>
</body>
</html>
