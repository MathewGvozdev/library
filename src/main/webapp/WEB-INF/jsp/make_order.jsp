<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.make.order"/>
    </title>
    <style>
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

        .centered-wrapper {
            position: relative;
            text-align: center;
        }

        .centered-content {
            display: inline-block;
            vertical-align: middle;
            margin: 40px;
        }

        input[type=text] {
            width: 300px;
            height: 50px;
            font-size: 16px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        input[type=date] {
            width: 300px;
            height: 50px;
            font-size: 16px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        select {
            width: 300px;
            height: 50px;
            font-size: 16px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        label {
            padding: 12px 12px 12px 0;
            display: inline-block;
        }

        h1 {
            color: black;
            height: 100%;
            display: table-cell;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 32px;
        }

        h5 {
            color: black;
            height: 100%;
            display: table-cell;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 16px;
        }

        span {
            color: black;
            height: 100%;
            display: table-cell;
            vertical-align: middle;
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 16px;
            padding: 30px 0 30px 650px;
            width: 1000px;
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
<c:if test="${param.cfm == null && not empty requestScope.book}">
    <div class="centered-wrapper">
        <div class="centered-content">
            <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
                  method="post">
                <h1>
                    <fmt:message key="page.order.make.msg"/>:<br>
                </h1>
                <div style="display: none">
                    <label>
                        <input type="hidden"
                               name="bookId"
                               value="${requestScope.book.id}">
                    </label><br>
                </div>
                <label>
                    <h5>
                        <fmt:message key="page.book.title"/>:<br>
                    </h5>
                    <input type="text"
                           name="title"
                           value="${requestScope.book.title}" readonly>
                </label><br>
                <label>
                    <h5>
                        <fmt:message key="page.book.author"/>:<br>
                    </h5>
                    <input type="text"
                           name="authors"
                           value="${requestScope.book.authors}" readonly>
                </label><br>
                <label>
                    <h5>
                        <fmt:message key="page.book.genre"/>:<br>
                    </h5>
                    <input type="text"
                           name="genres"
                           value="${requestScope.book.genres}" readonly>
                </label><br>
                <label>
                    <h5>
                        <fmt:message key="page.order.issue.date"/>:<br>
                    </h5>
                    <input type="date"
                           name="issueDate"
                           value="${param.issueDate}" required>
                </label><br>
                <label>
                    <h5>
                        <fmt:message key="page.order.due.date"/>:<br>
                    </h5>
                    <input type="date"
                           name="dueDate"
                           value="${param.dueDate}" required>
                </label><br>
                <label for="loanType">
                    <h5>
                        <fmt:message key="page.order.type"/>:<br>
                    </h5>
                    <select name="loanType"
                            id="loanType">
                        <c:forEach var="loanType" items="${requestScope.loanTypes}">
                            <option value="${loanType}">${loanType}</option>
                        </c:forEach>
                    </select><br>
                </label>
                <button type="submit" class="aqua">
                    <fmt:message key="page.button.send"/>
                </button>
                <br>
            </form>
        </div>
    </div>
</c:if>
<c:if test="${empty requestScope.book}">
    <div class="centered-wrapper">
        <div class="centered-content">
            <div class="validationError">
                <h1>
                    <fmt:message key="page.order.not.found"/><br>
                </h1>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${not empty requestScope.order}">
        <span>
            <fmt:message key="page.order.success.msg"/>:<br>
        </span>
    <table>
        <tbody>
        <tr>
            <th><fmt:message key="page.login.login"/></th>
            <th><fmt:message key="page.book.id"/></th>
            <th><fmt:message key="page.book.title"/></th>
            <th><fmt:message key="page.order.issue.date"/></th>
            <th><fmt:message key="page.order.due.date"/></th>
            <th><fmt:message key="page.order.type"/></th>
        </tr>
        <tr>
            <th>${requestScope.order.client}</th>
            <th>${requestScope.order.bookId}</th>
            <th>${requestScope.order.bookTitle}</th>
            <th>${requestScope.order.issueDate}</th>
            <th>${requestScope.order.dueDate}</th>
            <th>${requestScope.order.loanType}</th>
        </tr>
        </tbody>
    </table>
</c:if>
<div class="validationError">
    <c:if test="${not empty requestScope.validationError}">
    <span>
            ${requestScope.validationError}
    </span>
    </c:if>
</div>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
      method="post">
    <c:if test="${not empty requestScope.orderDto}">
        <span>
            <fmt:message key="page.order.confirm.msg"/><br>
        </span>
        <table>
            <tbody>
            <tr>
                <th><fmt:message key="page.book.title"/></th>
                <th><fmt:message key="page.book.author"/></th>
                <th><fmt:message key="page.book.genre"/></th>
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
            </tbody>
        </table>
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
                   name="authors"
                   value="${param.authors}"><br>
            <input type="hidden"
                   name="genres"
                   value="${param.genres}"><br>
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
        <button type="submit" class="aqua">
            <fmt:message key="page.button.confirm"/>
        </button>
        <br>
    </c:if>
</form>
</body>
</html>
