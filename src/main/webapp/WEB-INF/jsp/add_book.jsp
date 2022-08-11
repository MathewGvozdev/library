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
                <c:if test="${not empty requestScope.result}">
                    <c:if test="${requestScope.result == 'success'}">
                <span>
                    <fmt:message key="page.book.result.success.msg"/><br>
                    ID: ${requestScope.book.id}<br>
                    <fmt:message key="page.book.metas.title"/>: ${requestScope.book.bookMeta.title}<br>
                    <fmt:message key="page.book.metas.author"/>:<br>
                        <c:forEach var="author" items="${requestScope.book.bookMeta.authors}">
                            ${author.firstName} ${author.surname}<br>
                        </c:forEach>
                    <fmt:message key="page.book.metas.genre"/>:<br>
                        <c:forEach var="genre" items="${requestScope.book.bookMeta.genres}">
                            ${genre.title}<br>
                        </c:forEach>
                    <fmt:message key="page.book.publisher"/>: ${requestScope.book.publisher.title}<br>
                    <fmt:message key="page.book.pages"/>:${requestScope.book.pages}<br>
                    <fmt:message key="page.book.publication.year"/>:${requestScope.book.publicationYear}<br>
                </span>
                    </c:if>
                    <c:if test="${requestScope.result == 'failure'}">
                <span>
                    <fmt:message key="page.book.result.failure.msg"/>
                </span>
                    </c:if>
                </c:if>
                <c:if test="${not empty requestScope.error}">
                    <span>${requestScope.error}</span>
                </c:if>
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
</body>
</html>
