<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.make.order"/>
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
<c:if test="${not empty sessionScope.user}">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
          method="post">
        <fmt:message key="page.order.make.msg"/>:<br>
        <label>
            <fmt:message key="page.book.id"/>
            <input type="text"
                   name="bookId"
                   value="${param.bookId}" readonly>
        </label><br>
        <label>
            <fmt:message key="page.book.metas.title"/>
            <input type="text"
                   name="title"
                   value="${param.title}" readonly>
        </label><br>
        <label>
            <fmt:message key="page.order.issue.date"/>
            <input type="date"
                   name="issueDate"
                   value="${param.issueDate}" required>
        </label><br>
        <label>
            <fmt:message key="page.order.due.date"/>
            <input type="date"
                   name="dueDate"
                   value="${param.dueDate}" required>
        </label><br>
        <label for="loanType">
            <fmt:message key="page.order.type"/>
            <select name="loanType"
                    id="loanType">
                <c:forEach var="loanType" items="${requestScope.loanTypes}">
                    <option value="${loanType}">${loanType}</option>
                </c:forEach>
            </select><br>
        </label>
        <button type="submit">
            <fmt:message key="page.button.send"/>
        </button>
        <br>
        <c:if test="${not empty requestScope.result}">
            <c:if test="${requestScope.result == 'success'}">
            <span>
                <fmt:message key="page.order.success.msg"/>:<br>
                <table>
                    <tr>
                        <th><fmt:message key="page.login.login"/></th>
                        <th><fmt:message key="page.book.id"/></th>
                        <th><fmt:message key="page.book.metas.title"/></th>
                        <th><fmt:message key="page.order.issue.date"/></th>
                        <th><fmt:message key="page.order.due.date"/></th>
                        <th><fmt:message key="page.order.type"/></th>
                    </tr>
                    <tr>
                        <th>${requestScope.order.client.login}</th>
                        <th>${requestScope.order.book.id}</th>
                        <th>${requestScope.order.book.bookMeta.title}</th>
                        <th>${requestScope.order.issueDate}</th>
                        <th>${requestScope.order.dueDate}</th>
                        <th>${param.loanType}</th>
                    </tr>
                </table>
<%--                    ${requestScope.order.client.login}<br>--%>
<%--                    ${requestScope.order.book.bookMeta.title}<br>--%>
<%--                    ${requestScope.order.issueDate}<br>--%>
<%--                    ${requestScope.order.dueDate}<br>--%>
<%--                    ${param.loanType}<br>--%>
            </span>
            </c:if>
            <c:if test="${requestScope.result == 'failure'}">
                <span>
                    <fmt:message key="page.order.failure.msg"/>
                </span>
            </c:if>
        </c:if>
        <c:if test="${not empty requestScope.error}">
            <span>${requestScope.error}</span>
        </c:if>
    </form>
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
          method="post">
        <c:if test="${not empty requestScope.orderDto}">
            <fmt:message key="page.order.confirm.msg"/><br>
            <table>
                <tr>
                    <th><fmt:message key="page.book.metas.title"/></th>
                    <th><fmt:message key="page.book.metas.author"/></th>
                    <th><fmt:message key="page.book.metas.genre"/></th>
                    <th><fmt:message key="page.order.issue.date"/></th>
                    <th><fmt:message key="page.order.due.date"/></th>
                    <th><fmt:message key="page.order.type"/></th>
                </tr>
                <tr>
                    <th>${requestScope.book.title}</th>
                    <th>${requestScope.book.authors}</th>
                    <th>${requestScope.book.genres}</th>
                    <th>${requestScope.orderDto.issueDate}</th>
                    <th>${requestScope.orderDto.dueDate}</th>
                    <th>${requestScope.orderDto.loanType}</th>
                </tr>
            </table>
<%--            ${requestScope.book.title}<br>--%>
<%--            ${requestScope.book.authors}<br>--%>
<%--            ${requestScope.book.genres}<br>--%>
<%--            ${requestScope.orderDto.issueDate}<br>--%>
<%--            ${requestScope.orderDto.dueDate}<br>--%>
<%--            ${requestScope.orderDto.loanType}<br>--%>
            <div style="display: none">
                <input type="hidden"
                       name="cfm"
                       value="y"><br>
                <input type="hidden"
                       name="bookId"
                       value="${param.bookId}"><br>
                <input type="hidden"
                       name="title"
                       value="${param.title}"><br>
                <input type="hidden"
                       name="issueDate"
                       value="${param.issueDate}"><br>
                <input type="hidden"
                       name="dueDate"
                       value="${param.dueDate}"><br>
                <input type="hidden"
                       name="loanType"
                       value="${param.loanType}"><br>
            </div>
            <button type="submit">
                <fmt:message key="page.button.confirm"/>
            </button>
            <br>
        </c:if>
    </form>
</c:if>
</body>
</html>
