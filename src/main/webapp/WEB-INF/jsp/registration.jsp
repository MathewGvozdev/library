<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="head.registration"/></title>
</head>
<body>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'ru_RU')}"/>
<fmt:setBundle basename="translations"/>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
      method="post">
    <label for="firstName">
        <fmt:message key="page.registration.name"/>:
        <input type="text"
               name="firstName"
               id="firstName" required>
    </label><br>
    <label for="surname">
        <fmt:message key="page.registration.surname"/>:
        <input type="text"
               name="surname"
               id="surname" required>
    </label><br>
    <label for="telephone">
        <fmt:message key="page.registration.telephone"/>:
        <input type="text"
               name="telephone"
               id="telephone"
               placeholder="+375 XX XXX-XX-XX" required>
    </label><br>
    <label for="passportNumber">
        <fmt:message key="page.registration.passport"/>:
        <input type="text"
               name="passportNumber"
               id="passportNumber" required>
    </label><br>
    <label for="login">
        <fmt:message key="page.login.login"/>:
        <input type="text"
               name="login"
               id="login" required>
    </label><br>
    <label for="password">
        <fmt:message key="page.login.password"/>:
        <input type="password"
               name="password"
               id="password">
    </label><br>
    <button type="submit">
        <fmt:message key="page.button.confirm"/>
    </button>
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
