<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.index"/>
    </title>
    <link rel="stylesheet" href="<c:url value='${pageContext.request.contextPath}/css/style.css'/>">
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

        button.aqua {
            border-radius: 4px;
            color: #fff;
            display: block;
            width: 240px;
            height: 80px;
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 24px;
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

        input[type=text] {
            width: 240px;
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
            font-family: "Roboto Thin", ui-monospace;
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
                    Поиск по фильтру
                </span>
            <div style="display: none">
                <input type="hidden"
                       name="cmd"
                       value="find_all_book_metas_by_filter">
                <input type="hidden"
                       name="page"
                       value="1">
            </div>
            <label>
                <input type="text"
                       name="title"
                       placeholder="<fmt:message key="page.index.book.title"/>">
            </label><br>
            <label>
                <input type="text"
                       name="authors"
                       placeholder="<fmt:message key="page.index.book.authors"/>">
            </label><br>
            <label>
                <input type="text"
                       name="genres"
                       placeholder="<fmt:message key="page.index.book.genres"/>">
            </label><br>
            <label>
                <input type="text"
                       name="series"
                       placeholder="<fmt:message key="page.index.book.series"/>">
            </label><br>
            <button type="submit" class="aqua">
                <fmt:message key="page.index.find"/>
            </button>
        </form>
    </div>
    <div class="centered-content">
        <form action="${pageContext.request.contextPath}/home"
              method="get">
            <div style="display: none">
                <input type="hidden"
                       name="cmd"
                       value="find_all_book_metas">
                <input type="hidden"
                       name="page"
                       value="1">
            </div>
            <button type="submit" class="aqua">
                <fmt:message key="page.index.show.books"/>
            </button>
        </form>
    </div>
    <c:if test="${sessionScope.role == 'Админ' || sessionScope.role == 'Библиотекарь'}">
        <div class="centered-content">
            <form action="${pageContext.request.contextPath}/home"
                  method="get">
                <div style="display: none">
                    <input type="hidden"
                           name="cmd"
                           value="admin_menu">
                </div>
                <button type="submit" class="aqua">
                    Меню админа
                </button>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>