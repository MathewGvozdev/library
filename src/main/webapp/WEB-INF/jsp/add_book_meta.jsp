<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book-meta</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form
        action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
        method="post">
    <fmt:message key="page.book.metas.msg"/>:<br>
    <input type="text" name="title" placeholder="<fmt:message key="page.book.metas.title"/>"><br>
    <input type="text" name="authors" placeholder="<fmt:message key="page.book.metas.author"/>"><br>
    <input type="text" name="genres" placeholder="<fmt:message key="page.book.metas.genre"/>"><br>
    <input type="text" name="series" placeholder="<fmt:message key="page.book.metas.series"/>"><br>
    <button type="submit">
        <fmt:message key="page.button.add"/>
    </button>
    <br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                <fmt:message key="page.book.metas.result.success.msg"/>:<br>
                ID: ${requestScope.bookMeta.id}<br>
                <fmt:message key="page.book.metas.title"/>: ${requestScope.bookMeta.title}<br>
                <fmt:message key="page.book.metas.author"/>:<br>
                    <c:forEach var="author" items="${requestScope.bookMeta.authors}">
                        ${author.firstName} ${author.surname}<br>
                    </c:forEach>
                <fmt:message key="page.book.metas.genre"/>:<br>
                    <c:forEach var="genre" items="${requestScope.bookMeta.genres}">
                        ${genre.title}<br>
                    </c:forEach>
                <c:if test="${not empty requestScope.bookMeta.series}">
                    <fmt:message key="page.book.metas.series"/>: ${requestScope.bookMeta.series}<br>
                </c:if>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>
                <fmt:message key="page.book.metas.result.failure.msg"/>
            </span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>
                ${requestScope.error}
        </span>
    </c:if>
</form>
<form
        action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
        method="post">
    <c:if test="${not empty requestScope.bookMetaDto}">
        <fmt:message key="page.book.metas.confirm.msg"/><br>
        <fmt:message key="page.book.metas.title"/>: ${requestScope.bookMetaDto.title}<br>
        <fmt:message key="page.book.metas.author"/>: ${requestScope.bookMetaDto.authors}<br>
        <fmt:message key="page.book.metas.genre"/>: ${requestScope.bookMetaDto.genres}<br>
        <c:if test="${not empty requestScope.bookMetaDto.series}">
            <fmt:message key="page.book.metas.series"/>: ${requestScope.bookMetaDto.series}<br>
        </c:if>
        <input type="hidden" name="cfm" value="y"><br>
        <input type="hidden" name="title" value="${param.title}"><br>
        <input type="hidden" name="authors" value="${param.authors}"><br>
        <input type="hidden" name="genres" value="${param.genres}"><br>
        <input type="hidden" name="series" value="${param.series}"><br>
        <button type="submit">
            <fmt:message key="page.button.confirm"/>
        </button>
        <br>
    </c:if>
</form>
</body>
</html>
