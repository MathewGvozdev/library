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
    </div>
</div>
<div class="centered-wrapper">
    <div class="centered-content">
        <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
              method="post">
            <label for="firstName">
                <input type="text"
                       name="firstName"
                       placeholder="<fmt:message key="page.registration.name"/>"
                       id="firstName" required>
            </label><br>
            <label for="surname">
                <input type="text"
                       name="surname"
                       placeholder="<fmt:message key="page.registration.surname"/>"
                       id="surname" required>
            </label><br>
            <label for="telephone">
                <input type="text"
                       name="telephone"
                       id="telephone"
                       placeholder="<fmt:message key="page.registration.telephone"/>" required>
            </label><br>
            <label for="passportNumber">
                <input type="text"
                       name="passportNumber"
                       placeholder="<fmt:message key="page.registration.name"/>"
                       id="passportNumber" required>
            </label><br>
            <label for="login">
                <input type="text"
                       name="login"
                       placeholder="<fmt:message key="page.registration.name"/>"
                       id="login" required>
            </label><br>
            <label for="password">
                <input type="password"
                       name="password"
                       placeholder="<fmt:message key="page.login.password"/>"
                       id="password">
            </label><br>
            <button type="submit" class="aqua">
                <fmt:message key="page.button.confirm"/>
            </button>
            <c:if test="${not empty requestScope.errors}">
                <div style="color: red">
                    <c:forEach var="error" items="${requestScope.errors}">
                        <span>${error.message}</span>
                    </c:forEach>
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>
