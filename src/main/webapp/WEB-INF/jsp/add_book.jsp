<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.add.book"/>
    </title>
</head>
<body>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
      method="post">
    <fmt:message key="page.book.msg"/>:<br>
    <label>
        <input type="text"
               name="bookMetaId"
               placeholder="<fmt:message key="page.book.meta.id"/>"
               value="${param.bookMetaId}">
    </label><br>
    <label>
        <input type="text"
               name="publisherId"
               placeholder="<fmt:message key="page.book.publisher.id"/>"
               value="${param.publisherId}">
    </label><br>
    <label>
        <input type="text"
               name="pages"
               placeholder="<fmt:message key="page.book.pages"/>"
               value="${param.pages}">
    </label><br>
    <label>
        <input type="text"
               name="publicationYear"
               placeholder="<fmt:message key="page.book.publication.year"/>"
               value="${param.publicationYear}">
    </label><br>
    <button type="submit">
        <fmt:message key="page.button.add"/>
    </button>
    <br>
    <c:if test="${not empty requestScope.result}">
        <c:if test="${requestScope.result == 'success'}">
            <span>
                <fmt:message key="page.book.result.success.msg"/><br>
                ID: ${requestScope.book.id}<br>
                <fmt:message key="page.book.metas.title"/>: ${requestScope.book.bookMeta.title}<br>
                <fmt:message key="page.book.metas.author"/>:<br>
                    <c:forEach var="author" items="${requestScope.book.bookMeta.authors}">
                        ${author.firstName} ${author.surname}<br>
                    </c:forEach>
                <fmt:message key="page.book.metas.genre"/>:<br>
                    <c:forEach var="genre" items="${requestScope.book.bookMeta.genres}">
                        ${genre.title}<br>
                    </c:forEach>
                <fmt:message key="page.book.publisher"/>: ${requestScope.book.publisher.title}<br>
                <fmt:message key="page.book.pages"/>:${requestScope.book.pages}<br>
                <fmt:message key="page.book.publication.year"/>:${requestScope.book.publicationYear}<br>
            </span>
        </c:if>
        <c:if test="${requestScope.result == 'failure'}">
            <span>
                <fmt:message key="page.book.result.failure.msg"/>
            </span>
        </c:if>
    </c:if>
    <c:if test="${not empty requestScope.error}">
        <span>${requestScope.error}</span>
    </c:if>
</form>
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}"
      method="post">
    <c:if test="${not empty requestScope.bookDto}">
        <fmt:message key="page.book.confirm.msg"/><br>
        <fmt:message key="page.book.meta.id"/>:${requestScope.bookDto.bookMetaId}<br>
        <fmt:message key="page.book.publisher.id"/>: ${requestScope.bookDto.publisherId}<br>
        <fmt:message key="page.book.pages"/>: ${requestScope.bookDto.pages}<br>
        <fmt:message key="page.book.publication.year"/>: ${requestScope.bookDto.publicationYear}<br>
        <div style="display: none">
            <input type="hidden" name="cfm" value="y"><br>
            <input type="hidden" name="bookMetaId" value="${param.bookMetaId}"><br>
            <input type="hidden" name="publisherId" value="${param.publisherId}"><br>
            <input type="hidden" name="pages" value="${param.pages}"><br>
            <input type="hidden" name="publicationYear" value="${param.publicationYear}"><br>
        </div>
        <button type="submit">
            <fmt:message key="page.button.confirm"/>
        </button>
    </c:if>
</form>
</body>
</html>
