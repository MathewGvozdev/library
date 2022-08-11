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
            padding: 30px;
            margin: 40px;
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

        input[type=password] {
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
            /*display: table-cell;*/
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
                    <fmt:message key="page.book.delete.msg"/>:<br>
                </span>
                <label>
                    <input type="text"
                           name="bookId"
                           placeholder="bookId"
                           value="${param.bookId}">
                </label><br>
                <button type="submit" class="aqua">
                    <fmt:message key="page.button.delete"/>
                </button>
                <br>
            </form>
        </c:if>
        <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
              method="post">
            <c:if test="${not empty requestScope.book}">
            <span>
                <fmt:message key="page.book.delete.confirm.msg"/><br>
                ID: ${requestScope.book.id}<br>
                <fmt:message key="page.book.metas.title"/>: ${requestScope.book.title}<br>
                <fmt:message key="page.book.metas.author"/>: ${requestScope.book.authors}<br>
                <fmt:message key="page.book.metas.genre"/>: ${requestScope.book.genres}<br>
                <c:if test="${not empty requestScope.book.series}">
                    <fmt:message key="page.book.metas.series"/>:
                    ${requestScope.book.series}<br>
                </c:if>
                <fmt:message key="page.book.publisher"/>: ${requestScope.book.publisher}<br>
                <fmt:message key="page.book.pages"/>: ${requestScope.book.pages}<br>
                <fmt:message key="page.book.publication.year"/>: ${requestScope.book.publicationYear}<br>
            </span>
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
    </div>
    <div class="centered-content">
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
        <c:if test="${not empty requestScope.error}">
        <span>
            the book doesn't exist
        </span>
        </c:if>
    </div>
</div>
</body>
</html>