<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        Title
    </title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
      method="post">
    <div>
        NAME: <input type="text" name="firstName" value="${param.firstName}"><br>
        SURNAME: <input type="text" name="surname" value="${param.surname}"><br>
        TELEPHONE: <input type="text" name="telephone" value="${param.telephone}"><br>
        PASSPORT: <input type="text" name="passport" value="${param.passportNumber}"><br>
        PASSWORD: <input type="password" name="password" value="${param.password}"><br>
    </div>
    <div style="display: none">
        <input type="hidden" name="id" value="${sessionScope.user.id}">
        <input type="hidden" name="firstName" value="${requestScope.userDto.firstName}">
        <input type="hidden" name="surname" value="${requestScope.userDto.surname}">
        <input type="hidden" name="telephone" value="${requestScope.userDto.telephone}">
        <input type="hidden" name="passport" value="${requestScope.userDto.passport}">
        <input type="hidden" name="password" value="${requestScope.userDto.password}">
    </div>
    <button type="submit">
        <fmt:message key="page.button.confirm"/>
    </button>
    <br>
</form>
</body>
</html>
