<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.user.info"/>
    </title>
    <style>
        .centered-wrapper {
            position: relative;
            text-align: center;
        }

        .centered-content {
            display: inline-block;
            margin: 20px;
        }

        .status-content {
            display: inline-block;
        }

        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            background: white;
            width: 100%;
            border-collapse: collapse;
            text-align: center;
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

        th {
            font-size: 14px;
            font-weight: normal;
            color: black;
            border-bottom: 2px solid #158ea2;
            padding: 10px 8px;
        }

        td {
            color: black;
            padding: 9px 8px;
        }

        caption {
            font-size: 30px;
            line-height: 36px;
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
            padding: 6px 12px;
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

        button.status {
            border-radius: 4px;
            color: #fff;
            display: block;
            width: 100px;
            height: 40px;
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 12px;
            padding: 12px 12px;
            margin: 30px auto;
            text-decoration: none;
        }

        button.status {
            background-color: #2b6068;
            border: 1px solid #115868;
        }

        button.status:hover {
            background-color: #158ea2;
        }

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
<div class="centered-wrapper">
    <div class="centered-content">
        <span>
            <fmt:message key="page.user.login"/>: ${requestScope.userDto.login}<br>
            <fmt:message key="page.user.name"/>: ${requestScope.userDto.firstName}<br>
            <fmt:message key="page.user.surname"/>: ${requestScope.userDto.surname}<br>
            <fmt:message key="page.user.telephone"/>: ${requestScope.userDto.telephone}<br>
            <fmt:message key="page.user.passport.number"/>: ${requestScope.userDto.passportNumber}<br>
        </span>
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
                <button type="submit" class="aqua">
                    <fmt:message key="page.button.change"/>
                </button>
            </c:if>
        </form>
        <form action="${pageContext.request.contextPath}/home"
              method="get">
            <c:if test="${sessionScope.user.role == 'Админ'}">
                <input type="hidden" name="cmd" value="change_user_role">
                <input type="hidden" name="id" value="${requestScope.userDto.id}">
                <button type="submit" class="aqua">
                    <fmt:message key="page.button.set.status"/>
                </button>
            </c:if>
        </form>
    </div>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}"
          method="post">
        <button type="submit" class="status">
            <fmt:message key="page.order.status.all"/>
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=opened"
          method="post">
        <button type="submit" class="status">
            <fmt:message key="page.order.status.opened"/>
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=closed"
          method="post">
        <button type="submit" class="status">
            <fmt:message key="page.order.status.closed"/>
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=loaned"
          method="post">
        <button type="submit" class="status">
            <fmt:message key="page.order.status.loaned"/>
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=overdue"
          method="post">
        <button type="submit" class="status">
            <fmt:message key="page.order.status.overdue"/>
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&userId=${param.userId}&status=declined"
          method="post">
        <button type="submit" class="status">
            <fmt:message key="page.order.status.declined"/>
        </button>
    </form>
</div>
<table>
    <caption>
        <fmt:message key="page.order.orders"/>
    </caption>
    <tr>
        <th>№</th>
        <th><fmt:message key="page.order.client"/></th>
        <th><fmt:message key="page.order.book.id"/></th>
        <th><fmt:message key="page.book.title"/></th>
        <th><fmt:message key="page.order.issue.date"/></th>
        <th><fmt:message key="page.order.due.date"/></th>
        <th><fmt:message key="page.order.fact.date"/></th>
        <th><fmt:message key="page.order.type"/></th>
        <th><fmt:message key="page.order.status"/></th>
    </tr>
    <c:forEach var="order" items="${requestScope.orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.client}</td>
            <td>${order.bookId}</td>
            <td>${order.bookTitle}</td>
            <td>${order.issueDate}</td>
            <td>${order.dueDate}</td>
            <td>${order.factDate}</td>
            <td>${order.loanType}</td>
            <c:if test="${sessionScope.user.role == 'Админ' || sessionScope.user.role == 'Библиотекарь'}">
                <td>
                    <a href="${pageContext.request.contextPath}/home?cmd=update_order&id=${order.id}">
                            ${order.status}<br>
                    </a>
                </td>
            </c:if>
            <c:if test="${sessionScope.user.role == 'Пользователь'}">
                <td>${order.status}</td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>
