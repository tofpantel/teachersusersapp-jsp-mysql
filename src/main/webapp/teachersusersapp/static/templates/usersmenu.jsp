<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Users Search</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/teachersusersapp/static/css/usersmenu.css">
</head>
<body>
  <div class="center">
    <p>Hello ${sessionScope.username}</p>
  </div>

  <div class="center">
    <div class="search-wrapper">
      <div class="bot-gap">
        <span class="title">Users Search</span>
      </div>
      <div class="bot-gap">
        <form method="POST" action="${pageContext.request.contextPath}/teachersusersapp/users/search">
          <input name="username" type="text" class="search rounded" placeholder="Insert users's username"
                 autofocus/>
          <br><br>
          <button class="search-btn rounded color-btn" type="submit">Search</button>
        </form>
      </div>
    </div>

    <div class="insert-wrapper">
      <div class="bot-gap">
        <span class="title">User Insert</span>
      </div>
      <div class="bot-gap">
        <form method="POST" action="${pageContext.request.contextPath}/teachersusersapp/insert">
          <input name="username" type="text" value="${requestScope.insertedUserDB.username}"
                 class="insert rounded" placeholder="Username" autofocus/><br>
          <input name="password" type="text" value="${requestScope.insertedUserDB.password}"
                 class="insert rounded" placeholder="Password" autofocus/>
          <br><br>
          <button class="search-btn rounded color-btn" type="submit">Insert</button>
        </form>
      </div>
    </div>
  </div>

  <div class="center">
    <c:if test="${requestScope.isError}">
      <p>${requestScope.message}</p>
    </c:if>
  </div>

  <div class="center">
    <c:if test="${requestScope.teachersNotFound}">
      <p>No teachers found</p>
    </c:if>

    <p>${requestScope.error}</p>
  </div>
</body>
</html>


