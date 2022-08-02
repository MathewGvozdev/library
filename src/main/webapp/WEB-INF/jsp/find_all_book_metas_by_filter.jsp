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

        .centered-wrapper:before {
            content: "";
            position: relative;
            display: inline-block;
            width: 0;
            height: 100%;
            vertical-align: middle;
        }

        .centered-content {
            display: inline-block;
            vertical-align: middle;
            padding: 30px;
            margin: 40px;
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
            font-family: "Roboto Thin", ui-monospace;
            font-size: 32px;
        }
    </style>
</head>
<body>
<div class="centered-wrapper">
    <div class="centered-content">
        <h1>
            <fmt:message key="page.book.metas.books"/>:
        </h1>
        <ul class="first">
            <c:forEach var="bookMeta" items="${requestScope.bookMetas}">
                <li>
                    <a href="${pageContext.request.contextPath}/home?cmd=find_any_book&bookMetaId=${bookMeta.id}">
                            ${bookMeta.title}<br>
                    </a>
                    <fmt:message key="page.book.metas.author"/>: ${bookMeta.authors}<br>
                    <fmt:message key="page.book.metas.genre"/>: ${bookMeta.genres}<br>
                    <c:if test="${not empty bookMeta.series}">
                        <fmt:message key="page.book.metas.series"/>:
                        ${bookMeta.series}<br>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
        <c:if test="${not empty requestScope.bookMetas}">
            <div>
                [${param.page}]
            </div>
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
                <button type="submit">
                    <fmt:message key="page.button.next"/>
                </button>
            </form>
        </c:if>
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
                <button type="submit">
                    <fmt:message key="page.button.previous"/>
                </button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
