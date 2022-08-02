<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        Title
    </title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home"
      method="get">
    <div style="display: none">
        <input type="hidden"
               name="cmd"
               value="find_all_books">
        <input type="hidden"
               name="page"
               value="1">
    </div>
    <button type="submit">
        <fmt:message key="page.index.show.book.examples"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home"
      method="get">
    <fmt:message key="page.index.book.id"/>
    <div style="display: none">
        <input type="hidden"
               name="cmd"
               value="find_book_by_id">
    </div>
    <label for="bookId">
        <input type="number"
               name="bookId"
               id="bookId">
    </label>
    <button type="submit">
        <fmt:message key="page.index.find"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home"
      method="get">
    <div style="display: none">
        <input type="hidden"
               name="cmd"
               value="add_book">
    </div>
    <button type="submit">
        <fmt:message key="page.index.add.book.example"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home"
      method="get">
    <div style="display: none">
        <input type="hidden"
               name="cmd"
               value="delete_book">
    </div>
    <button type="submit">
        <fmt:message key="page.index.delete.book.example"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home"
      method="get">
    <div style="display: none">
        <input type="hidden"
               name="cmd"
               value="find_all_orders">
    </div>
    <button type="submit">
        <fmt:message key="page.index.find.orders"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home"
      method="get">
    <div style="display: none">
        <input type="hidden"
               name="cmd"
               value="find_all_users">
        <input type="hidden"
               name="page"
               value="1">
    </div>
    <button type="submit">
        <fmt:message key="page.button.find"/>
    </button>
</form>
</body>
</html>
