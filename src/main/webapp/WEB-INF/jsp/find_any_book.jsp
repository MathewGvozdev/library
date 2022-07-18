<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        Book with filter
    </title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="find_any_book_by_filter">
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
