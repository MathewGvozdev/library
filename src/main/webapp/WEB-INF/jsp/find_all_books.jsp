<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        All books
    </title>
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}" method="get">
    <h1>
        <fmt:message key="page.book.examples"/>:
    </h1>
    <c:forEach var="book" items="${requestScope.books}">
        <ul>
            <li>
                ID: ${book.id}<br>
                <fmt:message key="page.book.metas.title"/>: ${book.title}<br>
                <fmt:message key="page.book.metas.author"/>: ${book.authors}<br>
                <fmt:message key="page.book.metas.genre"/>: ${book.genres}<br>
                <c:if test="${not empty book.series}">
                    <fmt:message key="page.book.metas.series"/>: ${book.series}<br>
                </c:if>
                <fmt:message key="page.book.publisher"/>: ${book.publisher}<br>
                <fmt:message key="page.book.pages"/>: ${book.pages}<br>
                <fmt:message key="page.book.publication.year"/>: ${book.publicationYear}<br>
            </li>
        </ul>
    </c:forEach>
</form>
</body>
</html>
