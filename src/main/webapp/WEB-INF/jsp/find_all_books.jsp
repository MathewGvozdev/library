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
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            background: white;
            width: 100%;
            border-collapse: collapse;
            text-align: left;
            margin: 0 auto 20px;
        }

        tbody {
            display: table-row-group;
            vertical-align: middle;
            border-color: inherit;
        }

        tr {
            display: table-row;
            vertical-align: inherit;
            border-color: inherit;
        }

        th {
            font-size: 14px;
            font-weight: normal;
            color: black;
            border-bottom: 2px solid #158ea2;
            padding: 10px 8px;
        }

        td {
            color: black;
            padding: 9px 8px;
        }

        caption {
            font-size: 30px;
            line-height: 36px;
        }

        button.page {
            border-radius: 4px;
            color: #fff;
            display: block;
            width: 80px;
            height: 30px;
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 12px;
            padding: 6px 10px;
            margin: 20px auto;
            text-decoration: none;
        }

        button.page {
            background-color: black;
            border: 1px solid #890f0f;
        }

        button.page:hover {
            background-color: #158ea2;
        }

        .pagination-content {
            display: inline-block;
            text-align: center;
        }

        .pagination-content span {
            color: black;
            height: 100%;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 22px;
        }
        .centered-content {
            position: relative;
            text-align: center;
            margin: 20px;
        }
    </style>
</head>
<body>
<table>
    <caption>
        <fmt:message key="page.book.examples"/>
    </caption>
    <tbody>
    <tr>
        <th><fmt:message key="page.book.id"/></th>
        <th><fmt:message key="page.book.title"/></th>
        <th><fmt:message key="page.book.author"/></th>
        <th><fmt:message key="page.book.genre"/></th>
        <th><fmt:message key="page.book.series"/></th>
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
    </tbody>
</table>
<div class="centered-content">
    <div class="pagination-content">
        <c:if test="${param.page > 1}">
            <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page - 1}"
                  method="post">
                <button type="submit" class="page">
                    <fmt:message key="page.button.previous"/>
                </button>
            </form>
        </c:if>
    </div>
    <div class="pagination-content">
        <span>
            [${param.page}]
        </span>
    </div>
    <div class="pagination-content">
        <c:if test="${param.page < requestScope.pages}">
            <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page + 1}"
                  method="post">
                <button type="submit" class="page">
                    <fmt:message key="page.button.next"/>
                </button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
