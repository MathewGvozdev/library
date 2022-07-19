<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add genre</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_genre"><br>
    <input type="hidden" name="cfm" value=""><br>
    Genre:<br>
    <input type="text" name="title" placeholder="title" value="${param.title}"><br>
    <button type="submit">
        Add genre
    </button><br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                Жанр успешно добавлен:<br>
                    ${requestScope.genre.id}<br>
                ${requestScope.genre.title}<br>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>Жанр не добавлен</span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>${requestScope.error}</span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=add_genre" method="post">
    <c:if test="${not empty requestScope.genreDto}">
        Добавить этот жанр?<br>
        ${requestScope.genreDto.title}<br>
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="title" value="${param.title}"><br>
        <input type="submit" value="Confirm">
    </c:if>
</form>
</body>
</html>
