<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.find.all.metas.by.filter"/>
    </title>
    <style>
        .centered-wrapper {
            position: relative;
            text-align: center;
        }

        .centered-content {
            display: inline-block;
            margin: 20px;
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

        .first {
            list-style: none;
            padding: 0;
        }

        .first li {
            width: 500px;
            padding: 10px 30px;
            background: #2b6068;
            border-bottom: 1px solid grey;
            color: white;
            font-size: 20px;
            margin-bottom: 5px;
        }

        .first li:hover {
            background: #158ea2;
        }

        .first li:last-child {
            border-bottom: none;
        }

        .first li a {
            color: white;
            text-decoration: none;
        }

        .first li a:hover {
            text-decoration: underline;
        }

        h1 {
            color: black;
            height: 100%;
            display: table-cell;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 32px;
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
        <c:if test="${not empty requestScope.bookMetas}">
        <h1>
            <fmt:message key="page.book.books"/>
        </h1>
        <ul class="first">
            <c:forEach var="bookMeta" items="${requestScope.bookMetas}">
                <li>
                    <img src="${pageContext.request.contextPath}/images/${bookMeta.image}"
                         alt="image"
                         width="250"
                         height="350"><br>
                    <a href="${pageContext.request.contextPath}/home?cmd=make_order&bookMetaId=${bookMeta.id}">
                            ${bookMeta.title}<br>
                    </a>
                    <fmt:message key="page.book.author"/>: ${bookMeta.authors}<br>
                    <fmt:message key="page.book.genre"/>: ${bookMeta.genres}<br>
                    <c:if test="${not empty bookMeta.series}">
                        <fmt:message key="page.book.series"/>:
                        ${bookMeta.series}<br>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>
    <br>
    <div class="pagination-content">
        <c:if test="${param.page > 1}">
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
        <c:if test="${requestScope.hasNextBtn == true}">
            <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page + 1}"
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
                <button type="submit" class="page">
                    <fmt:message key="page.button.next"/>
                </button>
            </form>
        </c:if>
        </c:if>
        <c:if test="${empty requestScope.bookMetas}">
            <span>
                <fmt:message key="page.book.not.found"/>
            </span>
        </c:if>
    </div>
</div>
</body>
</html>
