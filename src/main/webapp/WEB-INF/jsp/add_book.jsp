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
            margin: 10px auto;
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

        input[type=file] {
            display: block;
            margin-left: 650px;
        }

        .error span {
            color: darkred;
            height: 100%;
            display: contents;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="centered-wrapper">
    <div class="centered-content">
        <c:if test="${requestScope.showInput == true}">
            <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
                  method="post"
                  enctype="multipart/form-data">
                <span>
                    <fmt:message key="page.book.msg"/>:<br>
                </span>
                <label>
                    <input type="text"
                           name="title"
                           placeholder="<fmt:message key="page.book.title"/>"
                           value="${param.title}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="authors"
                           placeholder="<fmt:message key="page.book.author"/>"
                           value="${param.authors}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="genres"
                           placeholder="<fmt:message key="page.book.genre"/>"
                           value="${param.genres}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="series"
                           placeholder="<fmt:message key="page.book.series"/>"
                           value="${param.series}">
                </label><br>
                <label>
                    <input type="text"
                           name="publisher"
                           placeholder="<fmt:message key="page.book.publisher"/>"
                           value="${param.publisher}" required>
                </label><br>
                <label>
                    <input type="text"
                           name="publisherCity"
                           placeholder="<fmt:message key="page.book.publisher.city"/>"
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
        <c:if test="${not empty requestScope.errors}">
            <div class="error">
                <c:forEach var="error" items="${requestScope.errors}">
                        <span>
                                <fmt:message key="${error.code}"/><br>
                        </span>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>

<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
      method="post"
      enctype="multipart/form-data">
    <c:if test="${not empty requestScope.bookDto}">
        <table>
            <caption>
                <fmt:message key="page.book.confirm.msg"/>
            </caption>
            <tbody>
            <tr>
                <th><fmt:message key="page.book.title"/></th>
                <th><fmt:message key="page.book.author"/></th>
                <th><fmt:message key="page.book.genre"/></th>
                <th><fmt:message key="page.book.series"/></th>
                <th><fmt:message key="page.book.publisher"/></th>
                <th><fmt:message key="page.book.publisher.city"/></th>
                <th><fmt:message key="page.book.pages"/></th>
                <th><fmt:message key="page.book.publication.year"/></th>
            </tr>
            <tr>
                <td>${requestScope.bookDto.title}</td>
                <td>${requestScope.bookDto.authors}</td>
                <td>${requestScope.bookDto.genres}</td>
                <td>${requestScope.bookDto.series}</td>
                <td>${requestScope.bookDto.publisher}</td>
                <td>${requestScope.bookDto.publisherCity}</td>
                <td>${requestScope.bookDto.pages}</td>
                <td>${requestScope.bookDto.publicationYear}</td>
            </tr>
            </tbody>
        </table>
        <c:if test="${requestScope.isBookExist == false}">
            <label>
                <input type="file"
                       name="image" required>
            </label>
        </c:if>
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
<c:if test="${not empty requestScope.book}">
    <table>
        <caption>
            <fmt:message key="page.book.after.add.msg"/>
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
         width="250"
         height="350"
         alt="image">
        <c:if test="${not empty requestScope.error}">
            <span>${requestScope.error}</span>
        </c:if>
    </div>
</div>
</c:if>
</body>
</html>
