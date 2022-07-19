<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All book-metas</title>
</head>
<body>
<%@ include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/home?cmd=find_all_book_metas" method="get">
    <h1>
        Книги:
    </h1>
    <c:forEach var="bookMeta" items="${requestScope.bookMetas}">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/home?cmd=find_any_book&bookMetaId=${bookMeta.id}">
                    №${bookMeta.id}<br>
                </a>
                Название: ${bookMeta.title}<br>
                Автор: ${bookMeta.authors}<br>
                Жанр: ${bookMeta.genres}<br>
                <c:if test="${not empty bookMeta.series}">
                    Серия: ${bookMeta.series}<br>
                </c:if>
            </li>
        </ul>
    </c:forEach>
</form>
</body>
</html>
