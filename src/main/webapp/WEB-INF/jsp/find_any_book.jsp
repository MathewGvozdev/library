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
<form action="${pageContext.request.contextPath}/home?cmd=find_any_book_by_filter" method="get">
    <c:set var="book" value="${requestScope.book}"/>

    №${book.id}<br>
    Название: ${book.title}<br>
    Автор: ${book.authors}<br>
    Жанр: ${book.genres}<br>
    <c:if test="${not empty book.series}">
        Серия:
        ${book.series}<br>
    </c:if>
    Издатель: ${book.publisher}<br>
    Количество страниц: ${book.pages}<br>
    Год издания: ${book.publicationYear}<br>
</form>
</body>
</html>
