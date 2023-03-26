<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Successful Insert</title>
</head>
<body>
	<h1>User inserted successfully</h1>
	<div>
		<p>${requestScope.insertedUserDB.username}</p>
		<p>${requestScope.insertedUserDB.password}</p>
		<p></p>
	</div>	
	 	
	<div>
		<a href="${pageContext.request.contextPath}/teachersusersapp/static/templates/usersmenu.jsp">Return</a>
	</div> 	
</body>
</html>