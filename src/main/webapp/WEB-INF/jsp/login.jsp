<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'ru_RU')}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <title><fmt:message key="head.login"/></title>
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
            width: 240px;
            height: 50px;
            font-size: 16px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        input[type=password] {
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

        a{
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
            <label for="login">
                <span>
                    <fmt:message key="page.login.login"/>:
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
                       id="password" required>
            </label><br>
            <button type="submit" class="aqua">
                <fmt:message key="page.button.sign.in"/>
            </button>
            <a href="${pageContext.request.contextPath}/home?cmd=registration">
                <button type="button" class="aqua">
                    <fmt:message key="page.button.registration"/>
                </button>
            </a>
            <c:if test="${param.error != null}">
                <div style="color: red">
            <span>
                <fmt:message key="page.login.incorrect"/>
            </span>
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>
