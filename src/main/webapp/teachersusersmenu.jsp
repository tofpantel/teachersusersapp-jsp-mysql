<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teachers-Users Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/teachersusersapp/static/css/teachersusersmenu.css">
</head>
<body>
<div class="center">
    <p>Hello ${sessionScope.username}</p>
</div>

<div class="center-tu">
    <div class="search-wrapper-tu">
        <div class="bot-gap-tu">
            <span class="title-tu">Select Teachers or Users</span>
        </div>
        <div class="bot-gap-tu">
            <ul>
                <li><a href="${pageContext.request.contextPath}/teachersusersapp/static/templates/teachersmenu.jsp">Teachers menu</a></li>
                <li><a href="${pageContext.request.contextPath}/teachersusersapp/static/templates/usersmenu.jsp">Users menu</a></li>
            </ul>
        </div>
    </div>
</div>

<%--    <div class="center">--%>
<%--        <c:if test="${requestScope.isError}">--%>
<%--            <p>${requestScope.message}</p>--%>
<%--        </c:if>--%>
<%--    </div>--%>

<%--    <div class="center">--%>
<%--        <c:if test="${requestScope.teachersNotFound}">--%>
<%--            <p>No teachers found</p>--%>
<%--        </c:if>--%>

<%--        <p>${requestScope.error}</p>--%>
<%--    </div>--%>

</body>
</html>