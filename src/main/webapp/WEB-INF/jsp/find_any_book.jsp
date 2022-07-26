<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        Book with filter
    </title>
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}" method="get">
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
</form>
</body>
</html>
