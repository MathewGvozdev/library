<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        Title
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
            font-family: "Roboto Thin", ui-monospace;
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
        <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
              method="post">
            <div>
                <label>
                    <span><fmt:message key="page.user.name"/>: </span>
                    <input type="text" name="firstName" value="${param.firstName}">
                </label><br>
                <label>
                    <span><fmt:message key="page.user.surname"/>: </span>
                    <input type="text" name="surname" value="${param.surname}">
                </label><br>
                <label>
                    <span><fmt:message key="page.user.telephone"/>: </span>
                    <input type="text" name="telephone" value="${param.telephone}">
                </label><br>
                <label>
                    <span><fmt:message key="page.user.passport.number"/>: </span>
                    <input type="text" name="passport" value="${param.passportNumber}">
                </label><br>
                <label>
                    <span><fmt:message key="page.user.password"/>: </span>
                    <input type="password" name="password" value="${param.password}">
                </label><br>
            </div>
            <div style="display: none">
                <input type="hidden" name="id" value="${sessionScope.user.id}">
                <input type="hidden" name="firstName" value="${requestScope.userDto.firstName}">
                <input type="hidden" name="surname" value="${requestScope.userDto.surname}">
                <input type="hidden" name="telephone" value="${requestScope.userDto.telephone}">
                <input type="hidden" name="passport" value="${requestScope.userDto.passport}">
                <input type="hidden" name="password" value="${requestScope.userDto.password}">
            </div>
            <button type="submit" class="aqua">
                <fmt:message key="page.button.confirm"/>
            </button>
        </form>
    </div>
</div>
</body>
</html>
