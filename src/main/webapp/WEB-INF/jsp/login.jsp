<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home" method="post">
  <input type="hidden" name="cmd" value="login"><br>
  <input type="hidden" name="cfm" value=""><br>
  <label for="login">Login:
    <input type="text" name="login" id="login" value="${param.user}" required>
  </label><br>
  <label for="password">Password:
    <input type="password" name="password" id="password" required>
  </label><br>
  <button type="submit">Submit</button>
  <a href="${pageContext.request.contextPath}/home?cmd=register">
    <button type="button">Register</button>
  </a>
  <c:if test="${not empty requestScope.error}">
    <div style="color: red">
      <span>/>Email or password is not correct</span>
    </div>
  </c:if>
</form>
</body>
</html>
