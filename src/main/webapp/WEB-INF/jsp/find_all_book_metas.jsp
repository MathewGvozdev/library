<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.find.all.metas"/>
    </title>
</head>
<body>
<h1>
    <fmt:message key="page.book.metas.books"/>:
</h1>
<c:forEach var="bookMeta" items="${requestScope.bookMetas}">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/home?cmd=find_any_book&bookMetaId=${bookMeta.id}">
                    ${bookMeta.title}<br>
            </a>
            <fmt:message key="page.book.metas.author"/>:<br> ${bookMeta.authors}<br>
            <fmt:message key="page.book.metas.genre"/>:<br> ${bookMeta.genres}<br>
            <c:if test="${not empty bookMeta.series}">
                <fmt:message key="page.book.metas.series"/>:<br> ${bookMeta.series}<br>
            </c:if>
        </li>
    </ul>
</c:forEach>
<c:if test="${not empty requestScope.bookMetas}">
    <div>
        [${param.page}]
    </div>
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page + 1}"
          method="post">
        <button type="submit">
            <fmt:message key="page.button.next"/>
        </button>
    </form>
</c:if>
<c:if test="${param.page > 1}">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page - 1}"
          method="post">
        <button type="submit">
            <fmt:message key="page.button.previous"/>
        </button>
    </form>
</c:if>
</body>
</html>
