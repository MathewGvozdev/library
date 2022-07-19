<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_book"><br>
    <input type="hidden" name="cfm" value=""><br>
    Book:<br>
    <input type="text" name="bookMetaId" placeholder="bookMetaId" value="${param.bookMetaId}"><br>
    <input type="text" name="publisherId" placeholder="publisherId" value="${param.publisherId}"><br>
    <input type="text" name="pages" placeholder="pages" value="${param.pages}"><br>
    <input type="text" name="publicationYear" placeholder="publicationYear" value="${param.publicationYear}"><br>
    <button type="submit">
        Add book
    </button>
    <br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                Экземпляр книги успешно добавлен<br>
                №${requestScope.book.id}<br>
                Название: ${requestScope.book.bookMeta.title}<br>
                Автор:<br>
                    <c:forEach var="author" items="${requestScope.book.bookMeta.authors}">
                        ${author.firstName} ${author.surname}<br>
                    </c:forEach>
                Жанр:<br>
                    <c:forEach var="genre" items="${requestScope.book.bookMeta.genres}">
                        ${genre.title}<br>
                    </c:forEach>
                Издатель: ${requestScope.book.publisher.title}<br>
                ${requestScope.book.pages} стр.<br>
                ${requestScope.book.publicationYear} г.<br>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>Экземпляр не добавлен</span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>${requestScope.error}</span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=add_book" method="post">
    <c:if test="${not empty requestScope.bookDto}">
        Уверены, что хотите добавить книгу?<br>
        № книги:${requestScope.bookDto.bookMetaId}<br>
        № издателя: ${requestScope.bookDto.publisherId}<br>
        Стр: ${requestScope.bookDto.pages}<br>
        Год издания: ${requestScope.bookDto.publicationYear}<br>
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="bookMetaId" value="${param.bookMetaId}"><br>
        <input type="hidden" name="publisherId" value="${param.publisherId}"><br>
        <input type="hidden" name="pages" value="${param.pages}"><br>
        <input type="hidden" name="publicationYear" value="${param.publicationYear}"><br>
        <input type="submit" value="Confirm">
    </c:if>
</form>
</body>
</html>
