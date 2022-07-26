<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
        <fmt:message key="head.find.all.metas.by.filter"/>
    </title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page + 1}"
      method="post">
    <c:if test="${not empty requestScope.bookMetas}">
        <h1>
            <fmt:message key="page.book.metas.books"/>:
        </h1>
        <ol>
            <c:forEach var="book" items="${requestScope.bookMetas}">
                <li>
                    <fmt:message key="page.book.metas.title"/>: ${book.title}<br>
                    <fmt:message key="page.book.metas.author"/>: ${book.authors}<br>
                    <fmt:message key="page.book.metas.genre"/>: ${book.genres}<br>
                    <c:if test="${not empty book.series}">
                        <fmt:message key="page.book.metas.series"/>:
                        ${book.series}<br>
                    </c:if>
                </li>
            </c:forEach>
        </ol>
        <div style="display: none">
            <input type="hidden"
                   name="title"
                   value="${param.title}"><br>
            <input type="hidden"
                   name="authors"
                   value="${param.authors}"><br>
            <input type="hidden"
                   name="genres"
                   value="${param.genres}"><br>
            <input type="hidden"
                   name="series"
                   value="${param.series}"><br>
        </div>
        <button type="submit">
            <fmt:message key="page.button.next"/>
        </button>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page - 1}"
      method="post">
    <div style="display: none">
        <input type="hidden"
               name="title"
               value="${param.title}"><br>
        <input type="hidden"
               name="authors"
               value="${param.authors}"><br>
        <input type="hidden"
               name="genres"
               value="${param.genres}"><br>
        <input type="hidden"
               name="series"
               value="${param.series}"><br>
    </div>
    <button type="submit">
        <fmt:message key="page.button.previous"/>
    </button>
</form>
</body>
</html>
