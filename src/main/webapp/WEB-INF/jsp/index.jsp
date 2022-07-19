<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<html>
<head>
    <title>Home</title>
</head>
<body>
<%@ include file="header.jsp"%>
<button type="button">
    <a href="${pageContext.request.contextPath}/home?cmd=find_all_books">
        find all books
    </a>
</button>
<br><br>

<button type="button">
    <a href="${pageContext.request.contextPath}/home?cmd=find_all_book_metas">
        find all book-metas
    </a>
</button>
<br><br>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="find_book_by_id">
    Book id:
    <input type="number" name="bookId">
    <input type="submit" value="press">
</form>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="find_all_book_metas_by_filter">
    <input type="hidden" name="page" value="1">
    Filter:
    <input type="text" name="title" placeholder="title"><br>
    <input type="text" name="authors" placeholder="authors"><br>
    <input type="text" name="genres" placeholder="genres"><br>
    <input type="text" name="series" placeholder="series"><br>
    <input type="submit" value="find by filter">
</form>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_author">
    <input type="submit" value="add author">
</form>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_book">
    <input type="submit" value="add book">
</form>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_book_meta">
    <input type="submit" value="add book-meta">
</form>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_genre">
    <input type="submit" value="add genre">
</form>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="add_publisher">
    <input type="submit" value="add publisher">
</form>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="delete_book">
    <input type="submit" value="delete book">
</form>

<form action="${pageContext.request.contextPath}/home" method="get">
    <input type="hidden" name="cmd" value="find_all_orders">
    <input type="submit" value="find orders">
</form>


</body>
</html>