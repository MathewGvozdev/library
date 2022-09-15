<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'ru_RU')}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <title>
        <fmt:message key="head.registration"/>
    </title>
    <style>
        .centered-wrapper {
            position: relative;
            text-align: center;
        }

        .centered-content {
            display: inline-block;
            vertical-align: middle;
            margin: 20px;
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

        a {
            text-decoration: none;
        }

        .header {
            width: 100%;
            height: 80px;
            display: block;
            background-color: #2b6068;
        }

        .inner_header {
            width: 1200px;
            height: 100%;
            display: block;
            margin: 0 auto;
        }

        .logo_container {
            height: 100%;
            display: table;
            float: left;
        }

        .logo_container h1 {
            color: white;
            height: 100%;
            display: table-cell;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 32px;
        }

        .lang_container {
            width: 100px;
            height: 100%;
            display: table;
            float: right;
        }

        .lang_container a {
            color: white;
            height: 100%;
            display: table-cell;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 16px;
            text-decoration: none;
        }

        .lang_container a:hover {
            text-decoration: underline;
        }
        .navigation {
            float: right;
            height: 100%;
            margin: 0;
        }
        .navigation li {
            height: 100%;
            display: table;
            float: left;
            padding: 0 20px;
        }
        .navigation li a {
            display: table-cell;
            vertical-align: middle;
            height: 100%;
            color: white;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 24px;
            text-decoration: none;
        }
        .navigation li a:hover {
            text-decoration: underline;
        }

        .validationError span {
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
<div class="header">
    <div class="lang_container">
        <a href="${pageContext.request.contextPath}/home?cmd=change_locale&lang=ru_RU">
            RU
        </a>
        <a href="${pageContext.request.contextPath}/home?cmd=change_locale&lang=en_US">
            EN
        </a>
    </div>
    <div class="inner_header">
        <div class="logo_container">
            <h1>E-LIBRARY</h1>
        </div>
        <ul class="navigation">
            <li>
                <a href="${pageContext.request.contextPath}/home">
                    <fmt:message key="page.header.to.home"/>
                </a>
            </li>
        </ul>
    </div>
</div>
<div class="centered-wrapper">
    <div class="centered-content">
        <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
              method="post">
            <label for="firstName">
                <span>
                    <fmt:message key="page.registration.name"/>:
                </span>
                <input type="text"
                       name="firstName"
                       id="firstName"
                       value="${param.firstName}" required>
            </label><br>
            <label for="surname">
                <span>
                    <fmt:message key="page.registration.surname"/>:
                </span>
                <input type="text"
                       name="surname"
                       id="surname"
                       value="${param.surname}" required>
            </label><br>
            <label for="telephone">
                <span>
                    <fmt:message key="page.registration.telephone"/>:
                </span>
                <input type="text"
                       name="telephone"
                       id="telephone"
                       value="${param.telephone}" required>
            </label><br>
            <label for="passportNumber">
                <span>
                    <fmt:message key="page.registration.passport"/>:
                </span>
                <input type="text"
                       name="passportNumber"
                       id="passportNumber"
                       value="${param.passportNumber}" required>
            </label><br>
            <label for="login">
                <span>
                    <fmt:message key="page.registration.login"/>:
                </span>
                <input type="text"
                       name="login"
                       id="login"
                       value="${param.login}" required>
            </label><br>
            <label for="password">
                <span>
                    <fmt:message key="page.login.password"/>:
                </span>
                <input type="password"
                       name="password"
                       id="password">
            </label><br>
            <button type="submit" class="aqua">
                <fmt:message key="page.button.confirm"/>
            </button>
            <c:if test="${not empty requestScope.errors}">
                <div class="validationError">
                    <c:forEach var="validationError" items="${requestScope.errors}">
                        <span>
                                <fmt:message key="${validationError.key}"/><br>
                        </span>
                    </c:forEach>
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>
