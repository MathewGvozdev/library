<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/home?cmd=find_all_orders" method="get">
    <h1>
        Заявки:
    </h1>
    <c:forEach var="order" items="${requestScope.orders}">
        <ul>
            <li>
                №${order.id}<br>
                Клиент: ${order.client}<br>
                # книги: ${order.bookId}<br>
                Название: ${order.bookTitle}<br>
                Дата выдачи: ${order.issueDate}<br>
                Дата сдачи: ${order.dueDate}<br>
                Фактическая дата: ${order.factDate}<br>
                Тип: ${order.loanType}<br>
                Статус: ${order.status}<br>
            </li>
        </ul>
    </c:forEach>
</form>
</body>
</html>
