<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete book</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm" method="post">
    <fmt:message key="page.book.delete.msg"/>:<br>
    <input type="text" name="bookId" placeholder="bookId" value="${param.bookId}"><br>
    <button type="submit">
        <fmt:message key="page.button.delete"/>
    </button>
    <br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                <fmt:message key="page.book.delete.success.msg"/>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>
                <fmt:message key="page.book.delete.failure.msg"/>
            </span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>
                ${requestScope.error}
        </span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}" method="post">
    <c:if test="${not empty requestScope.book}">
        <fmt:message key="page.book.delete.confirm.msg"/><br>
        ID: ${requestScope.book.id}<br>
        <fmt:message key="page.book.metas.title"/>: ${requestScope.book.title}<br>
        <fmt:message key="page.book.metas.author"/>: ${requestScope.book.authors}<br>
        <fmt:message key="page.book.metas.genre"/>: ${requestScope.book.genres}<br>
        <c:if test="${not empty requestScope.book.series}">
            <fmt:message key="page.book.metas.series"/>:
            ${requestScope.book.series}<br>
        </c:if>
        <fmt:message key="page.book.publisher"/>: ${requestScope.book.publisher}<br>
        <fmt:message key="page.book.pages"/>: ${requestScope.book.pages}<br>
        <fmt:message key="page.book.publication.year"/>: ${requestScope.book.publicationYear}<br>
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="bookId" value="${param.bookId}"><br>
        <button type="submit">
            <fmt:message key="page.button.confirm"/>
        </button>
        <br>
    </c:if>
</form>
</body>
</html>