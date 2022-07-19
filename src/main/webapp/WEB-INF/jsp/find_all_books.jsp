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
<form action="${pageContext.request.contextPath}/home?cmd=find_all_books" method="get">
    <h1>
        Книги:
    </h1>
    <c:forEach var="book" items="${requestScope.books}">
        <ul>
            <li>
                №${book.id}<br>
                Название: ${book.title}<br>
                Автор: ${book.authors}<br>
                Жанр: ${book.genres}<br>
                <c:if test="${not empty book.series}">
                    Серия: ${book.series}<br>
                </c:if>
                Издатель: ${book.publisher}<br>
                Количество страниц: ${book.pages}<br>
                Год издания: ${book.publicationYear}<br>
            </li>
        </ul>
    </c:forEach>
</form>
</body>
</html>
