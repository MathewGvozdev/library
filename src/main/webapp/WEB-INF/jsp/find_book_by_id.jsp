<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book by id</title>
</head>
<body>
<%@ include file="header.jsp"%>
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

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="find_book_by_id">
    Book id:
    <input type="number" name="bookId" value="${param.bookId}" required>
    <input type="submit" value="find">
</form>
</body>
</html>
