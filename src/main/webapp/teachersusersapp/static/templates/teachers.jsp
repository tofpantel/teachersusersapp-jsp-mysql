<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Teachers Found</title>

</head>
<body>
  <div>
    <table>
      <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Delete</th>
        <th>Update</th>
      </tr>
      <c:forEach var = "teacher" items = "${requestScope.teachers}">
        <tr>
          <td>${teacher.id}</td>
          <td>${teacher.firstname}</td>
          <td>${teacher.lastname}</td>
          <td><a href="${pageContext.request.contextPath}/teachersusersapp/delete?id=${teacher.id}
                      &firstname=${teacher.firstname}&lastname=${teacher.lastname}">Delete</a></td>
          <td><a href="${pageContext.request.contextPath}/teachersusersapp/static/templates/teacherupdate.jsp?id=${teacher.id}&firstname=${teacher.firstname}&lastname=${teacher.lastname}">Update</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>

  <div>
    <c:if test="${requestScope.deleteAPIError}">
      <p>Something went wrong in Delete</p>
    </c:if>
  </div>

  <div>
    <c:if test="${requestScope.updateAPIError}">
      <p>Something went wrong in Update</p>
    </c:if>
  </div>

</body>
</html>
