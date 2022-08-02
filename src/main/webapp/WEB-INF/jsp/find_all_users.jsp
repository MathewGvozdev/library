<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>Title</title>
    <style>
        table {
            border: 1px solid grey;
        }

        th {
            border: 1px solid grey;
        }

        td {
            border: 1px solid grey;
        }
    </style>
</head>
<body>
<table>
    <caption>
        USERS
    </caption>
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
