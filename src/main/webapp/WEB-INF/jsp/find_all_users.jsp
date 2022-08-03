<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>Title</title>
    <style>
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            background: white;
            width: 100%;
            border-collapse: collapse;
            text-align: left;
            margin: 0 auto 20px;
        }

        tbody {
            display: table-row-group;
            vertical-align: middle;
            border-color: inherit;
        }

        tr {
            display: table-row;
            vertical-align: inherit;
            border-color: inherit;
        }

        .table th {
            font-size: 14px;
            font-weight: normal;
            color: #039;
            border-bottom: 2px solid #6678b1;
            padding: 10px 8px;
        }

        .table td {
            color: #669;
            padding: 9px 8px;
        }
    </style>
</head>
<body>
<table>
    <caption>
        USERS
    </caption>
    <tbody>
    <tr>
        <th>№</th>
        <th>LOGIN</th>
        <th>FIRST NAME</th>
        <th>SURNAME</th>
        <th>TELEPHONE</th>
        <th>PASSPORT NUMBER</th>
        <th>ROLE</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td>
                    ${user.id}<br>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/home?cmd=find_user_info&userId=${user.id}">
                        ${user.login}<br>
                </a>
            </td>
            <td>${user.firstName}</td>
            <td>${user.surname}</td>
            <td>${user.telephone}</td>
            <td>${user.passportNumber}</td>
            <td>${user.role}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${not empty requestScope.users}">
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
</body>
</html>
