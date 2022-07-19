<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book-meta</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_book_meta"><br>
    <input type="hidden" name="cfm" value=""><br>
    Book:<br>
    <input type="text" name="title" placeholder="title"><br>
    <input type="text" name="authors" placeholder="authors"><br>
    <input type="text" name="genres" placeholder="genres"><br>
    <input type="text" name="series" placeholder="series"><br>
    <button type="submit">
        Add book-meta
    </button><br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                Книга успешно добавлена:<br>
                    №${requestScope.bookMeta.id}<br>
                Название: ${requestScope.bookMeta.title}<br>
                Автор:<br>
                    <c:forEach var="author" items="${requestScope.bookMeta.authors}">
                        ${author.firstName} ${author.surname}<br>
                    </c:forEach>
                Жанр:<br>
                    <c:forEach var="genre" items="${requestScope.bookMeta.genres}">
                        ${genre.title}<br>
                    </c:forEach>
                <c:if test="${not empty requestScope.bookMeta.series}">
                    Серия: ${requestScope.bookMeta.series}<br>
                </c:if>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>Автор не добавлен</span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>${requestScope.error}</span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=add_book_meta" method="post">
    <c:if test="${not empty requestScope.bookMetaDto}">
        Добавить книгу?<br>
        Название: ${requestScope.bookMetaDto.title}<br>
        Автор: ${requestScope.bookMetaDto.authors}<br>
        Жанр: ${requestScope.bookMetaDto.genres}<br>
        <c:if test="${not empty requestScope.bookMetaDto.series}">
            Серия: ${requestScope.bookMetaDto.series}<br>
        </c:if>
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="title" value="${param.title}"><br>
        <input type="hidden" name="authors" value="${param.authors}"><br>
        <input type="hidden" name="genres" value="${param.genres}"><br>
        <input type="hidden" name="series" value="${param.series}"><br>
        <input type="submit" value="Confirm">
    </c:if>
</form>
</body>
</html>
