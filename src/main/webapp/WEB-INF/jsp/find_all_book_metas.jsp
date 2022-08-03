<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.find.all.metas"/>
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
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
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
        <c:forEach var="bookMeta" items="${requestScope.bookMetas}">
            <ul class="first">
                <li>
                    <a href="${pageContext.request.contextPath}/home?cmd=make_order&bookMetaId=${bookMeta.id}">
                            ${bookMeta.title}<br>
                    </a>
                    <fmt:message key="page.book.metas.author"/>: ${bookMeta.authors}<br>
                    <fmt:message key="page.book.metas.genre"/>: ${bookMeta.genres}<br>
                    <c:if test="${not empty bookMeta.series}">
                        <fmt:message key="page.book.metas.series"/>: ${bookMeta.series}<br>
                    </c:if>
                </li>
            </ul>
        </c:forEach>
        <c:if test="${not empty requestScope.bookMetas}">
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
    </div>
</div>
</body>
</html>
