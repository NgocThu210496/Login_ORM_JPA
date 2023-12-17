<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ngocthu6778gmail.com
  Date: 2023/12/17
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/userController/login" method="post" modelMap="loginRequest">
  <label for="userName">Username</label>
  <input type="text" id="userName" name="userName"/><br>
  <label for="password">Password</label>
  <input type="password" id="password" name="password"/><br>
  <c:if test="${not empty error}">
    <label style="color: red">${error}</label><br>
  </c:if>
  <input type="submit" value="Login"/>
</form>
</body>
</html>
