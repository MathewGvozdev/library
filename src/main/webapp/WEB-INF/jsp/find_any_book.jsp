<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.find.any.book"/>
    </title>
</head>
<body>
<c:if test="${not empty requestScope.book}">
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
    <form action="${pageContext.request.contextPath}/home?cmd=make_order"
          method="post">
        <div style="display: none">
            <input type="hidden" name="bookId" value="${requestScope.book.id}">
            <input type="hidden" name="title" value="${requestScope.book.title}">
        </div>
        <button type="submit">
            <fmt:message key="page.button.find"/>
        </button>
    </form>
</c:if>
<c:if test="${empty requestScope.book}">
    Извините, в данный момент свободной книги нет
</c:if>
</body>
</html>
