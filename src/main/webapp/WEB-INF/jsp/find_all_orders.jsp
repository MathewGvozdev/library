<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.find.all.orders"/>
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
            <td>
                <a href="${pageContext.request.contextPath}/home?cmd=update_order&id=${order.id}">
                        ${order.id}<br>
                </a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/home?cmd=${param.cmd}&clientId=${order.clientId}">
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
</table>
</body>
</html>
