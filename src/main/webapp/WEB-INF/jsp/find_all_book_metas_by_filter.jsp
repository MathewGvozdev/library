<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>
        All books with filter
    </title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="find_all_book_metas_by_filter">
    <h1>
        Книги:
    </h1>
    <ol>
        <c:forEach var="book" items="${requestScope.bookMetas}">
            <li>
                Название: ${book.title}<br>
                Автор: ${book.authors}<br>
                Жанр: ${book.genres}<br>
                <c:if test="${not empty book.series}">
                    Серия:
                    ${book.series}<br>
                </c:if>
            </li>
        </c:forEach>
    </ol>
    <input type="hidden" name="page" value="${param.page + 1}">
    <input type="hidden" name="title" value="${param.title}"><br>
    <input type="hidden" name="authors" value="${param.authors}"><br>
    <input type="hidden" name="genres" value="${param.genres}"><br>
    <input type="hidden" name="series" value="${param.series}"><br>
    <input type="submit" value="Вперед">
</form>
<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="find_all_book_metas_by_filter">
    <input type="hidden" name="page" value="${param.page - 1}">
    <input type="hidden" name="title" value="${param.title}"><br>
    <input type="hidden" name="authors" value="${param.authors}"><br>
    <input type="hidden" name="genres" value="${param.genres}"><br>
    <input type="hidden" name="series" value="${param.series}"><br>
    <input type="submit" value="Назад">
</form>
</body>
</html>
