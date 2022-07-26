<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        <fmt:message key="head.add.genre"/>
    </title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
      method="post">
    <fmt:message key="page.genre.msg"/>:<br>
    <label>
        <input type="text"
               name="title"
               placeholder="<fmt:message key="page.book.metas.title"/>"
               value="${param.title}">
    </label><br>
    <button type="submit">
        <fmt:message key="page.button.add"/>
    </button>
    <br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                <fmt:message key="page.genre.result.success.msg"/>:<br>
                    ${requestScope.genre.id} - ${requestScope.genre.title}<br>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>
                <fmt:message key="page.genre.result.failure.msg"/>
            </span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>
                ${requestScope.error}
        </span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
      method="post">
    <c:if test="${not empty requestScope.genreDto}">
        <fmt:message key="page.genre.confirm.msg"/><br>
        ${requestScope.genreDto.title}<br>
        <div style="display: none">
            <input type="hidden"
                   name="cfm"
                   value="y"><br>
            <input type="hidden"
                   name="title"
                   value="${param.title}"><br>
        </div>
        <button type="submit">
            <fmt:message key="page.button.confirm"/>
        </button>
        <br>
    </c:if>
</form>
</body>
</html>
