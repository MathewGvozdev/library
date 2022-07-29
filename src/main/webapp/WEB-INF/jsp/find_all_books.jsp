<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.find.all.books"/>
    </title>
    <style>
        table {
            border: 1px solid grey;
        }

        th {
            border: 1px solid grey;
        }

        td {
            border: 1px solid grey;
        }
    </style>
</head>
<body>
<table>
    <caption>
        <fmt:message key="page.book.examples"/>
    </caption>
    <tr>
        <th><fmt:message key="page.book.id"/></th>
        <th><fmt:message key="page.book.metas.title"/></th>
        <th><fmt:message key="page.book.metas.author"/></th>
        <th><fmt:message key="page.book.metas.genre"/></th>
        <th><fmt:message key="page.book.metas.series"/></th>
        <th><fmt:message key="page.book.publisher"/></th>
        <th><fmt:message key="page.book.pages"/></th>
        <th><fmt:message key="page.book.publication.year"/></th>
    </tr>
    <c:forEach var="book" items="${requestScope.books}">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.authors}</td>
            <td>${book.genres}</td>
            <td>${book.series}</td>
            <td>${book.publisher}</td>
            <td>${book.pages}</td>
            <td>${book.publicationYear}</td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty requestScope.books}">
    <div>
        [${param.page}]
    </div>
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page + 1}"
          method="post">
        <button type="submit">
            <fmt:message key="page.button.next"/>
        </button>
    </form>
</c:if>
<c:if test="${param.page > 1}">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page - 1}"
          method="post">
        <button type="submit">
            <fmt:message key="page.button.previous"/>
        </button>
    </form>
</c:if>
</body>
</html>
