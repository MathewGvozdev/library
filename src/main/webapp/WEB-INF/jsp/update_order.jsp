<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update status</title>
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
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm" method="post">
    <table>
        <caption>
            <fmt:message key="page.order.order"/>
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
        <tr>
            <td>${requestScope.order.id}</td>
            <td>${requestScope.order.client}</td>
            <td>${requestScope.order.bookId}</td>
            <td>${requestScope.order.bookTitle}</td>
            <td>${requestScope.order.issueDate}</td>
            <td>${requestScope.order.dueDate}</td>
            <td>
                <c:if test="${not empty requestScope.order.factDate}">
                    ${requestScope.order.factDate}<br>
                </c:if>
                <c:if test="${empty requestScope.order.factDate}">
                    <input type="date" name="factDate"><br>
                </c:if>
            </td>
            <td>${requestScope.order.loanType}</td>
            <td>
                <select name="status" id="status">
                    <c:forEach var="status" items="${requestScope.statuses}">
                        <option value="${status}">${status}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <br>
    <input type="hidden" name="id" value="${requestScope.order.id}">
    <input type="hidden" name="clientId" value="${requestScope.order.clientId}">
    <input type="hidden" name="bookId" value="${requestScope.order.bookId}">
    <input type="hidden" name="issueDate" value="${requestScope.order.issueDate}">
    <input type="hidden" name="dueDate" value="${requestScope.order.dueDate}">
    <input type="hidden" name="factDate" value="${requestScope.order.factDate}">
    <input type="hidden" name="loanType" value="${requestScope.order.loanType}">
    <input type="hidden" name="status" value="${requestScope.order.status}">
    <button type="submit">
        <fmt:message key="page.button.confirm"/>
    </button>
    <br>
</form>
</body>
</html>
