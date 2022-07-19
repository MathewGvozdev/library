<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home?cmd=register" method="post">
    <input type="hidden" name="cfm" value=""><br>
    <label for="firstName">First name:
        <input type="text" name="firstName" id="firstName" required>
    </label><br>
    <label for="surname">Surname:
        <input type="text" name="surname" id="surname" required>
    </label><br>
    <label for="telephone">Telephone:
        <input type="text" name="telephone" id="telephone" required>
    </label><br>
    <label for="passportNumber">Passport number:
        <input type="text" name="passportNumber" id="passportNumber" required>
    </label><br>
    <label for="login">Login:
        <input type="text" name="login" id="login" required>
    </label><br>
    <label for="password">Password:
        <input type="password" name="password" id="password">
    </label><br>
    <button type="submit">Send</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
