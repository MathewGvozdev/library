<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        Title
    </title>
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
Login: ${requestScope.userDto.login}<br>
First name: ${requestScope.userDto.firstName}<br>
Surname: ${requestScope.userDto.surname}<br>
Telephone: ${requestScope.userDto.telephone}<br>
Passport: ${requestScope.userDto.passportNumber}<br>
<form action="${pageContext.request.contextPath}/home"
      method="get">
    <div style="display: none">
        <input type="hidden" name="cmd" value="update_user_info">
        <input type="hidden" name="firstName" value="${requestScope.userDto.firstName}">
        <input type="hidden" name="surname" value="${requestScope.userDto.surname}">
        <input type="hidden" name="telephone" value="${requestScope.userDto.telephone}">
        <input type="hidden" name="passportNumber" value="${requestScope.userDto.passportNumber}">
    </div>
    <c:if test="${sessionScope.user.login == requestScope.userDto.login}">
        <button type="submit">
            UPDATE
        </button>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}"
      method="post">
    <button type="submit">
        ALL
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=opened"
      method="post">
    <button type="submit">
        OPENED
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=closed"
      method="post">
    <button type="submit">
        CLOSED
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=loaned"
      method="post">
    <button type="submit">
        LOANED
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=overdue"
      method="post">
    <button type="submit">
        OVERDUE
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=declined"
      method="post">
    <button type="submit">
        DECLINED
    </button>
</form>
<table>
    <caption>
        <fmt:message key="page.order.orders"/>
    </caption>
    <tr>
        <th>№</th>
        <th><fmt:message key="page.order.client"/></th>
        <th><fmt:message key="page.order.book.id"/></th>
        <th><fmt:message key="page.book.metas.title"/></th>
        <th><fmt:message key="page.order.issue.date"/></th>
        <th><fmt:message key="page.order.due.date"/></th>
        <th><fmt:message key="page.order.fact.date"/></th>
        <th><fmt:message key="page.order.type"/></th>
        <th><fmt:message key="page.order.status"/></th>
    </tr>
    <c:forEach var="order" items="${requestScope.orders}">
        <tr>
            <c:if test="${sessionScope.user.role == 'Админ' || sessionScope.user.role == 'Библиотекарь'}">
                <td>
                    <a href="${pageContext.request.contextPath}/home?cmd=update_order&id=${order.id}">
                            ${order.id}<br>
                    </a>
                </td>
            </c:if>
            <c:if test="${sessionScope.user.role == 'Пользователь'}">
                <td>${order.id}</td>
            </c:if>
            <td>${order.client}</td>
            <td>${order.bookId}</td>
            <td>${order.bookTitle}</td>
            <td>${order.issueDate}</td>
            <td>${order.dueDate}</td>
            <td>${order.factDate}</td>
            <td>${order.loanType}</td>
            <td>${order.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
