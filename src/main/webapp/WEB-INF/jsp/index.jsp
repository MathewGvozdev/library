<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%@ include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/home?cmd=find_all_books" method="post">
    <button type="submit">
        <fmt:message key="page.index.show.book.examples"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=find_all_book_metas" method="post">
    <button type="submit">
        <fmt:message key="page.index.show.books"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=find_book_by_id" method="post">
    <fmt:message key="page.index.book.id"/>
    <input type="number" name="bookId">
    <button type="submit">
        <fmt:message key="page.index.find"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=find_all_book_metas_by_filter&page=1" method="post">
    <fmt:message key="page.index.filter"/>:<br>
    <input type="text" name="title" placeholder="<fmt:message key="page.index.book.title"/>"><br>
    <input type="text" name="authors" placeholder="<fmt:message key="page.index.book.authors"/>"><br>
    <input type="text" name="genres" placeholder="<fmt:message key="page.index.book.genres"/>"><br>
    <input type="text" name="series" placeholder="<fmt:message key="page.index.book.series"/>"><br>
    <button type="submit">
        <fmt:message key="page.index.find"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=add_author" method="post">
    <button type="submit">
        <fmt:message key="page.index.add.author"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=add_book" method="post">
    <button type="submit">
        <fmt:message key="page.index.add.book.example"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=add_book_meta" method="post">
    <button type="submit">
        <fmt:message key="page.index.add.book"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=add_genre" method="post">
    <button type="submit">
        <fmt:message key="page.index.add.genre"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=add_publisher" method="post">
    <button type="submit">
        <fmt:message key="page.index.add.publisher"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=delete_book" method="post">
    <button type="submit">
        <fmt:message key="page.index.delete.book.example"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=find_all_orders" method="post">
    <button type="submit">
        <fmt:message key="page.index.find.orders"/>
    </button>
</form>

<form action="${pageContext.request.contextPath}/home?cmd=make_order" method="post">
    <button type="submit">
        <fmt:message key="page.index.make.order"/>
    </button>
</form>

</body>
</html>