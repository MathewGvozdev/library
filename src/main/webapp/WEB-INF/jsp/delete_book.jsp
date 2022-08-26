<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.delete.book"/>
    </title>
    <style>
        .centered-wrapper {
            position: relative;
            text-align: center;
        }

        .centered-content {
            display: inline-block;
            vertical-align: middle;
        }

        input[type=text] {
            width: 300px;
            height: 50px;
            font-size: 16px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        label {
            padding: 12px 12px 12px 0;
            display: inline-block;
        }

        span {
            color: black;
            height: 100%;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 24px;
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
    </style>
</head>
<body>
<div class="centered-wrapper">
    <div class="centered-content">
        <c:if test="${param.cfm == null || not empty requestScope.result}">
            <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
                  method="post">
                <span>
                    <fmt:message key="page.book.delete.msg"/>:<br>
                </span>
                <label>
                    <input type="text"
                           name="bookId"
                           placeholder="<fmt:message key="page.book.id"/>"
                           value="${param.bookId}">
                </label><br>
                <button type="submit" class="aqua">
                    <fmt:message key="page.button.delete"/>
                </button>
                <br>
            </form>
        </c:if>
        <c:if test="${not empty requestScope.result}">
            <c:if test="${requestScope.result == 'success'}">
            <span>
                <fmt:message key="page.book.delete.success.msg"/>
            </span>
            </c:if>
            <c:if test="${requestScope.result == 'failure'}">
            <span>
                <fmt:message key="page.book.delete.failure.msg"/>
            </span>
            </c:if>
        </c:if>
        <c:if test="${not empty requestScope.validationError}">
        <span>
            <fmt:message key="page.book.delete.not.found"/>
        </span>
        </c:if>
    </div>
</div>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
      method="post">
    <c:if test="${not empty requestScope.book}">
        <table>
            <caption>
                <fmt:message key="page.book.delete.confirm.msg"/>
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
        <div class="centered-wrapper">
            <div class="centered-content">
                <img src="${pageContext.request.contextPath}/images/${requestScope.book.image}"
                     alt="image"
                     width="250"
                     height="350"><br>
            </div>
        </div>
        <div style="display: none">
            <input type="hidden"
                   name="cfm"
                   value="y"><br>
            <input type="hidden"
                   name="bookId"
                   value="${param.bookId}"><br>
        </div>
        <button type="submit" class="aqua">
            <fmt:message key="page.button.confirm"/>
        </button>
        <br>
    </c:if>
</form>
</body>
</html>