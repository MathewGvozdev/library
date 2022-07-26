<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add publisher</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm" method="post">
    <fmt:message key="page.publisher.msg"/>:<br>
    <input type="text" name="title" placeholder="<fmt:message key="page.publisher.title"/>" value="${param.title}"><br>
    <input type="text" name="city" placeholder="<fmt:message key="page.publisher.city"/>" value="${param.city}"><br>
    <button type="submit">
        <fmt:message key="page.button.add"/>
    </button>
    <br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                <fmt:message key="page.publisher.result.success.msg"/>:<br>
                    ${requestScope.publisher.id}<br>
                    ${requestScope.publisher.title}<br>
                    ${requestScope.publisher.city}<br>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>
                <fmt:message key="page.publisher.result.failure.msg"/>
            </span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>${requestScope.error}</span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}" method="post">
    <c:if test="${not empty requestScope.publisherDto}">
        <fmt:message key="page.publisher.confirm.msg"/><br>
        <fmt:message key="page.publisher.title"/>: ${requestScope.publisherDto.title}<br>
        <fmt:message key="page.publisher.city"/>: ${requestScope.publisherDto.city}<br>
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="title" value="${param.title}"><br>
        <input type="hidden" name="city" value="${param.city}"><br>
        <button type="submit">
            <fmt:message key="page.button.confirm"/>
        </button>
    </c:if>
</form>
</body>
</html>
