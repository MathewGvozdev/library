<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book by id</title>
</head>
<body>
<%@ include file="header.jsp" %>
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

<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}" method="post">
    <fmt:message key="page.book.id"/>:
    <input type="number" name="bookId" value="${param.bookId}" required>
    <button type="submit">
        <fmt:message key="page.button.find"/>
    </button>
</form>
</body>
</html>
