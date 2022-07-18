<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete book</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="delete_book"><br>
    <input type="hidden" name="cfm" value=""><br>
    Book:<br>
    <input type="text" name="bookId" placeholder="bookId" value="${param.bookId}"><br>
    <button type="submit">
        Delete book
    </button><br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>Книга успешна удалена</span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>Книга не удалена</span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>${requestScope.error}</span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=delete_book" method="post">
    <c:set var="book" value="${requestScope.book}"/>
    <c:if test="${not empty book}">
        Уверены, что хотите удалить книгу?<br>
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
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="bookId" value="${param.bookId}"><br>
        <input type="submit" value="Confirm">
    </c:if>
</form>
</body>
</html>