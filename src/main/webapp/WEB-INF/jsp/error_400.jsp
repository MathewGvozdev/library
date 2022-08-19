<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        400
    </title>
    <style>
        span {
            color: black;
            height: 100%;
            display: table-cell;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 24px;
        }
    </style>
</head>
<body>
<c:if test="${not empty requestScope.error}">
        <span>
                ${requestScope.error}
        </span>
</c:if>
</body>
</html>
