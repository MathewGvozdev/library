<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'ru_RU')}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <title><fmt:message key="head.login"/></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
      method="post">
    <label for="login">
        <fmt:message key="page.login.login"/>:
        <input type="text"
               name="login"
               id="login"
               value="${param.user}" required>
    </label><br>
    <label for="password">
        <fmt:message key="page.login.password"/>:
        <input type="password"
               name="password"
               id="password" required>
    </label><br>
    <button type="submit">
        <fmt:message key="page.button.sign.in"/>
    </button>
    <a href="${pageContext.request.contextPath}/home?cmd=register">
        <button type="button">
            <fmt:message key="page.button.registration"/>
        </button>
    </a>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span>
                <fmt:message key="page.login.incorrect"/>
            </span>
        </div>
    </c:if>
</form>
</body>
</html>
