<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.find.all.orders"/>
    </title>
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
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
      method="post">
    <button type="submit">
        ALL
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&status=opened"
      method="post">
    <button type="submit">
        OPENED
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&status=closed"
      method="post">
    <button type="submit">
        CLOSED
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&status=loaned"
      method="post">
    <button type="submit">
        LOANED
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&status=overdue"
      method="post">
    <button type="submit">
        OVERDUE
    </button>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&status=declined"
      method="post">
    <button type="submit">
        DECLINED
    </button>
</form>
<table>
    <caption>
        <fmt:message key="page.order.orders"/>
    </caption>
    <tbody>
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
            <td>
                <a href="${pageContext.request.contextPath}/home?cmd=update_order&id=${order.id}">
                        ${order.id}<br>
                </a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/home?cmd=find_user_info&userId=${order.clientId}">
                        ${order.client}<br>
                </a>
            </td>
            <td>${order.bookId}</td>
            <td>${order.bookTitle}</td>
            <td>${order.issueDate}</td>
            <td>${order.dueDate}</td>
            <td>${order.factDate}</td>
            <td>${order.loanType}</td>
            <td>${order.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
