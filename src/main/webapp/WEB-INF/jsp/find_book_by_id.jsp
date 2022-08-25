<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.book.by.id"/>
    </title>
    <style>
        .centered-wrapper {
            position: relative;
            text-align: center;
        }

        .centered-content {
            margin: 20px;
        }

        button.aqua {
            border-radius: 4px;
            color: #fff;
            display: block;
            width: 140px;
            height: 50px;
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 16px;
            padding: 12px 20px;
            margin: 30px auto;
            text-decoration: none;
        }

        button.aqua {
            background-color: #2b6068;
            border: 1px solid #115868;
        }

        button.aqua:hover {
            background-color: #158ea2;
        }

        input[type=number] {
            width: 240px;
            height: 50px;
            font-size: 16px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

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

        span {
            color: black;
            height: 100%;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 24px;
        }
    </style>
</head>
<body>
<div class="centered-wrapper">
    <div class="centered-content">
        <form action="${pageContext.request.contextPath}/home"
              method="get">
            <span>
                <fmt:message key="page.book.id"/>:<br>
            </span>
            <label>
                <input type="hidden"
                       name="cmd"
                       value="${param.cmd}">
            </label>
            <label for="bookId">
                <input type="number"
                       name="bookId"
                       id="bookId"
                       value="${param.bookId}" required>
            </label>
            <button type="submit" class="aqua">
                <fmt:message key="page.button.find"/>
            </button>
        </form>
        <c:if test="${empty requestScope.book and not empty param.bookId}">
            <span>
                <fmt:message key="page.book.delete.not.found"/><br>
            </span>
        </c:if>
    </div>
</div>
<c:if test="${not empty requestScope.book}">
    <div class="centered-wrapper">
        <div class="centered-content">
            <img src="${pageContext.request.contextPath}/images/${requestScope.book.image}"
                 alt="image"
                 width="250"
                 height="350"><br>
        </div>
    </div>
    <table>
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
        <tr>
            <td>${requestScope.book.id}</td>
            <td>${requestScope.book.title}</td>
            <td>${requestScope.book.authors}</td>
            <td>${requestScope.book.genres}</td>
            <td>${requestScope.book.series}</td>
            <td>${requestScope.book.publisher}</td>
            <td>${requestScope.book.pages}</td>
            <td>${requestScope.book.publicationYear}</td>
        </tr>
        </tbody>
    </table>
    <
</c:if>
<c:if test="${not empty requestScope.order}">
    <table>
        <caption>
            <fmt:message key="page.order.order"/>
        </caption>
        <tbody>
        <tr>
            <th>№</th>
            <th><fmt:message key="page.order.client"/></th>
            <th><fmt:message key="page.order.book.id"/></th>
            <th><fmt:message key="page.book.title"/></th>
            <th><fmt:message key="page.order.issue.date"/></th>
            <th><fmt:message key="page.order.due.date"/></th>
            <th><fmt:message key="page.order.fact.date"/></th>
            <th><fmt:message key="page.order.type"/></th>
            <th><fmt:message key="page.order.status"/></th>
        </tr>
        <tr>
            <td>${requestScope.order.id}</td>
            <td>${requestScope.order.client}</td>
            <td>${requestScope.order.bookId}</td>
            <td>${requestScope.order.bookTitle}</td>
            <td>${requestScope.order.issueDate}</td>
            <td>${requestScope.order.dueDate}</td>
            <td>${requestScope.order.factDate}</td>
            <td>${requestScope.order.loanType}</td>
            <td>${requestScope.order.status}</td>
        </tr>
        </tbody>
    </table>
</c:if>
</body>
</html>
