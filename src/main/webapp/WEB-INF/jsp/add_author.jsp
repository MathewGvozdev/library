<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add author</title>
</head>
<body>
<%@ include file="header.jsp" %>

<form
        action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
        method="post">
    <fmt:message key="page.author.msg"/>:<br>
    <input type="text" name="aName" placeholder="<fmt:message key="page.author.name"/>" value="${param.aName}"><br>
    <input type="text" name="aSurname" placeholder="<fmt:message key="page.author.surname"/>" value="${param.aSurname}"><br>
    <button type="submit">
        <fmt:message key="page.button.add"/>
    </button>
    <br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                <fmt:message key="page.author.result.success.msg"/>:<br>
                    ${requestScope.author.id}<br>
                ${requestScope.author.firstName} ${requestScope.author.surname}<br>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span><fmt:message key="page.author.result.failure.msg"/></span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>${requestScope.error}</span>
    </c:if>
</form>
<form
        action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
        method="post">
    <c:if test="${not empty requestScope.authorDto}">
        <fmt:message key="page.author.confirm.msg"/><br>
        ${requestScope.authorDto.name} ${requestScope.authorDto.surname}<br>
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="aName" value="${param.aName}"><br>
        <input type="hidden" name="aSurname" value="${param.aSurname}"><br>
        <button type="submit">
            <fmt:message key="page.button.confirm"/>
        </button>
    </c:if>
</form>
</body>
</html>
