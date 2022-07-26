<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All book-metas</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}" method="get">
    <h1>
        <fmt:message key="page.book.metas.books"/>:
    </h1>
    <c:forEach var="bookMeta" items="${requestScope.bookMetas}">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/home?cmd=find_any_book&bookMetaId=${bookMeta.id}">
                    №${bookMeta.id}<br>
                </a>
                <fmt:message key="page.book.metas.title"/>: ${bookMeta.title}<br>
                <fmt:message key="page.book.metas.author"/>: ${bookMeta.authors}<br>
                <fmt:message key="page.book.metas.genre"/>: ${bookMeta.genres}<br>
                <c:if test="${not empty bookMeta.series}">
                    <fmt:message key="page.book.metas.series"/>: ${bookMeta.series}<br>
                </c:if>
            </li>
        </ul>
    </c:forEach>
</form>
</body>
</html>
