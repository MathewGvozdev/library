<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add publisher</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_publisher"><br>
    <input type="hidden" name="cfm" value=""><br>
    Publisher:<br>
    <input type="text" name="title" placeholder="title" value="${param.title}"><br>
    <input type="text" name="city" placeholder="city" value="${param.city}"><br>
    <button type="submit">
        Add publisher
    </button><br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                Издатель успешно добавлен:<br>
                    ${requestScope.publisher.id}<br>
                ${requestScope.publisher.title}<br>
                ${requestScope.publisher.city}<br>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>Издатель не добавлен</span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>${requestScope.error}</span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=add_publisher" method="post">
    <c:if test="${not empty requestScope.publisherDto}">
        Добавить этого издателя?<br>
        Название: ${requestScope.publisherDto.title}<br>
        Город: ${requestScope.publisherDto.city}<br>
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="title" value="${param.title}"><br>
        <input type="hidden" name="city" value="${param.city}"><br>
        <input type="submit" value="Confirm">
    </c:if>
</form>
</body>
</html>
