<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.add.book"/>
    </title>
    <style>
        .centered-wrapper {
            position: relative;
            text-align: center;
        }

        .centered-content {
            display: inline-block;
            vertical-align: middle;
            padding: 30px;
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
            display: table-cell;
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
        <c:if test="${param.cfm == null}">
            <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
                  method="post">
                <span>
                    <fmt:message key="page.book.msg"/>:<br>
                </span>
                <label>
                    <input type="text"
                           name="title"
                           placeholder="title"
                           value="${param.title}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="authors"
                           placeholder="authors"
                           value="${param.authors}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="genres"
                           placeholder="genres"
                           value="${param.genres}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="series"
                           placeholder="series"
                           value="${param.series}">
                </label><br>
                <label>
                    <input type="text"
                           name="publisher"
                           placeholder="publisher"
                           value="${param.publisher}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="publisherCity"
                           placeholder="publisherCity"
                           value="${param.publisherCity}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="pages"
                           placeholder="<fmt:message key="page.book.pages"/>"
                           value="${param.pages}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="publicationYear"
                           placeholder="<fmt:message key="page.book.publication.year"/>"
                           value="${param.publicationYear}" required>
                </label><br>
                <button type="submit" class="aqua">
                    <fmt:message key="page.button.add"/>
                </button>
                <br>
            </form>
        </c:if>
        <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
              method="post">
            <c:if test="${not empty requestScope.bookDto}">
                <span>
                    <fmt:message key="page.book.confirm.msg"/><br>
                    <fmt:message key="page.book.metas.title"/>:${requestScope.bookDto.title}<br>
                    <fmt:message key="page.book.metas.author"/>:${requestScope.bookDto.authors}<br>
                    <fmt:message key="page.book.metas.genre"/>:${requestScope.bookDto.genres}<br>
                    <fmt:message key="page.book.metas.series"/>:${requestScope.bookDto.series}<br>
                    <fmt:message key="page.book.publisher"/>: ${requestScope.bookDto.publisher}<br>
                    city: ${requestScope.bookDto.publisherCity}<br>
                    <fmt:message key="page.book.pages"/>: ${requestScope.bookDto.pages}<br>
                    <fmt:message key="page.book.publication.year"/>: ${requestScope.bookDto.publicationYear}<br>
                </span>
                <div style="display: none">
                    <input type="hidden"
                           name="cfm"
                           value="y"><br>
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
                    <input type="hidden"
                           name="publisher"
                           value="${param.publisher}"><br>
                    <input type="hidden"
                           name="publisherCity"
                           value="${param.publisherCity}"><br>
                    <input type="hidden"
                           name="pages"
                           value="${param.pages}"><br>
                    <input type="hidden"
                           name="publicationYear"
                           value="${param.publicationYear}"><br>
                </div>
                <button type="submit" class="aqua">
                    <fmt:message key="page.button.confirm"/>
                </button>
            </c:if>
        </form>
    </div>
</div>
<c:if test="${not empty requestScope.book}">
    <table>
        <caption>
            Книга добавлена
        </caption>
        <tbody>
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
</c:if>
<c:if test="${not empty requestScope.error}">
    <span>${requestScope.error}</span>
</c:if>
</body>
</html>
