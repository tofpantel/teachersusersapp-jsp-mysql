<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Νέα Στοιχεία Teacher</h1>
	<p>teacher ${requestScope.teacher.firstname}</p>
	<p>teacher ${requestScope.teacher.lastname}</p>
	<a href="${pageContext.request.contextPath}/teachersusersapp/static/templates/teachersmenu.jsp">Επιστροφή</a>
</body>
</html>
