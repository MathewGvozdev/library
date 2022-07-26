<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Make order</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:if test="${not empty sessionScope.user}">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm" method="post">
        <fmt:message key="page.order.make.msg"/>:<br>
        <fmt:message key="page.book.id"/>
        <input type="text" name="bookId" value="${param.bookId}"><br>
        <fmt:message key="page.order.issue.date"/>
        <input type="date" name="issueDate" value="${param.issueDate}"><br>
        <fmt:message key="page.order.due.date"/>
        <input type="date" name="dueDate" value="${param.dueDate}"><br>
        <fmt:message key="page.order.type"/>
        <select name="loanType" id="loanType">
            <c:forEach var="loanType" items="${requestScope.loanTypes}">
                <option value="${loanType}">${loanType}</option>
            </c:forEach>
        </select><br>
        <button type="submit">
            <fmt:message key="page.button.send"/>
        </button>
        <br>
        <c:if test="${not empty requestScope.result}">
            <c:if test="${requestScope.result == 'success'}">
            <span>
                <fmt:message key="page.order.success.msg"/>:<br>
                    ${requestScope.order.client.login}<br>
                    ${requestScope.order.book.bookMeta.title}<br>
                    ${requestScope.order.issueDate}<br>
                    ${requestScope.order.dueDate}<br>
                    ${param.loanType}<br>
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
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}" method="post">
        <c:if test="${not empty requestScope.orderDto}">
            <fmt:message key="page.order.confirm.msg"/><br>
            ${requestScope.book.title}<br>
            ${requestScope.book.authors}<br>
            ${requestScope.book.genres}<br>
            ${requestScope.orderDto.issueDate}<br>
            ${requestScope.orderDto.dueDate}<br>
            ${requestScope.orderDto.loanType}<br>
            <input type="hidden" name="cfm" value="y"><br>
            <input type="hidden" name="bookId" value="${param.bookId}"><br>
            <input type="hidden" name="issueDate" value="${param.issueDate}"><br>
            <input type="hidden" name="dueDate" value="${param.dueDate}"><br>
            <input type="hidden" name="loanType" value="${param.loanType}"><br>
            <button type="submit">
                <fmt:message key="page.button.confirm"/>
            </button>
            <br>
        </c:if>
    </form>
</c:if>
</body>
</html>
