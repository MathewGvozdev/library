<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.update.order"/>
    </title>
    <style>
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            background: white;
            width: 100%;
            border-collapse: collapse;
            text-align: center;
            margin: 10px auto 20px;
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

<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
      method="post">
    <table>
        <caption>
            <fmt:message key="page.order.order"/>
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
                    <label>
                        <input type="date"
                               name="factDate">
                    </label>
                </c:if>
            </td>
            <td>${requestScope.order.loanType}</td>
            <td>
                <select name="status"
                        id="status">
                    <c:forEach var="status" items="${requestScope.statuses}">
                        <option value="${status}">
                                ${status}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <br>
    <div style="display: none">
        <input type="hidden" name="id" value="${requestScope.order.id}">
        <input type="hidden" name="clientId" value="${requestScope.order.clientId}">
        <input type="hidden" name="bookId" value="${requestScope.order.bookId}">
        <input type="hidden" name="issueDate" value="${requestScope.order.issueDate}">
        <input type="hidden" name="dueDate" value="${requestScope.order.dueDate}">
        <input type="hidden" name="factDate" value="${requestScope.order.factDate}">
        <input type="hidden" name="loanType" value="${requestScope.order.loanType}">
        <input type="hidden" name="status" value="${requestScope.order.status}">
    </div>
    <button type="submit" class="aqua">
        <fmt:message key="page.button.confirm"/>
    </button>
    <br>
</form>
</body>
</html>
