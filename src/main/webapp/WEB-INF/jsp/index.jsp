<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.index"/>
    </title>
</head>
<body>

<form action="${pageContext.request.contextPath}/home"
      method="get">
    <div style="display: none">
        <input type="hidden"
               name="cmd"
               value="find_all_book_metas">
        <input type="hidden"
               name="page"
               value="1">
    </div>
    <button type="submit">
        <fmt:message key="page.index.show.books"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home"
      method="get">
    <fmt:message key="page.index.filter"/>:<br>
    <div style="display: none">
        <input type="hidden"
               name="cmd"
               value="find_all_book_metas_by_filter">
        <input type="hidden"
               name="page"
               value="1">
    </div>
    <label>
        <input type="text"
               name="title"
               placeholder="<fmt:message key="page.index.book.title"/>">
    </label><br>
    <label>
        <input type="text"
               name="authors"
               placeholder="<fmt:message key="page.index.book.authors"/>">
    </label><br>
    <label>
        <input type="text"
               name="genres"
               placeholder="<fmt:message key="page.index.book.genres"/>">
    </label><br>
    <label>
        <input type="text"
               name="series"
               placeholder="<fmt:message key="page.index.book.series"/>">
    </label><br>
    <button type="submit">
        <fmt:message key="page.index.find"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home"
      method="get">
    <div style="display: none">
        <input type="hidden"
               name="cmd"
               value="make_order">
    </div>
    <button type="submit">
        <fmt:message key="page.index.make.order"/>
    </button>
</form>

<c:if test="${sessionScope.role == 'Админ' || sessionScope.role == 'Библиотекарь'}">
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
</c:if>
</body>
</html>