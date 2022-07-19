<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add author</title>
</head>
<body>
<%@ include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_author"><br>
    <input type="hidden" name="cfm" value=""><br>
    Author:<br>
    <input type="text" name="aName" placeholder="firstName" value="${param.aName}"><br>
    <input type="text" name="aSurname" placeholder="surname" value="${param.aSurname}"><br>
    <button type="submit">
        Add author
    </button><br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                Автор успешно добавлен:<br>
                ${requestScope.author.id}<br>
                ${requestScope.author.firstName} ${requestScope.author.surname}<br>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>Автор не добавлен</span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>${requestScope.error}</span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=add_author" method="post">
    <c:if test="${not empty requestScope.authorDto}">
        Добавить этого автора?<br>
        ${requestScope.authorDto.name} ${requestScope.authorDto.surname}<br>
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="aName" value="${param.aName}"><br>
        <input type="hidden" name="aSurname" value="${param.aSurname}"><br>
        <input type="submit" value="Confirm">
    </c:if>
</form>
</body>
</html>
