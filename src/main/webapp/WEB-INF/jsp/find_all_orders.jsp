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

        .status-content {
            display: inline-block;
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
            padding: 12px 20px;
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


        .pagination-content {
            display: inline-block;
            text-align: center;
        }

        .pagination-content span {
            color: black;
            height: 100%;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 22px;
        }

        button.page {
            border-radius: 4px;
            color: #fff;
            display: block;
            width: 80px;
            height: 30px;
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 12px;
            padding: 6px 10px;
            margin: 20px auto;
            text-decoration: none;
        }

        button.page {
            background-color: black;
            border: 1px solid #890f0f;
        }

        button.page:hover {
            background-color: #158ea2;
        }
        .centered-content {
            position: relative;
            text-align: center;
            margin: 20px;
        }
    </style>
</head>
<body>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page}&status=all"
          method="post">
        <button type="submit" class="status">
            ALL
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page}&status=opened"
          method="post">
        <button type="submit" class="status">
            OPENED
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page}&status=closed"
          method="post">
        <button type="submit" class="status">
            CLOSED
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page}&status=loaned"
          method="post">
        <button type="submit" class="status">
            LOANED
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page}&status=overdue"
          method="post">
        <button type="submit" class="status">
            OVERDUE
        </button>
    </form>
</div>
<div class="status-content">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page}&status=declined"
          method="post">
        <button type="submit" class="status">
            DECLINED
        </button>
    </form>
</div>
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
<div class="centered-content">
    <div class="pagination-content">
        <c:if test="${param.page > 1}">
            <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page - 1}&status=${param.status}"
                  method="post">
                <button type="submit" class="page">
                    <fmt:message key="page.button.previous"/>
                </button>
            </form>
        </c:if>
    </div>
    <div class="pagination-content">
    <span>
        [${param.page}]
    </span>
    </div>
    <div class="pagination-content">
        <c:if test="${param.page < requestScope.pages}">
            <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page + 1}&status=${param.status}"
                  method="post">
                <button type="submit" class="page">
                    <fmt:message key="page.button.next"/>
                </button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
