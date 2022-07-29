<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.find.all.metas.by.filter"/>
    </title>
</head>
<body>
<h1>
    <fmt:message key="page.book.metas.books"/>:
</h1>
<ul>
    <c:forEach var="bookMeta" items="${requestScope.bookMetas}">
        <li>
            <a href="${pageContext.request.contextPath}/home?cmd=find_any_book&bookMetaId=${bookMeta.id}">
                    ${bookMeta.title}<br>
            </a>
            <fmt:message key="page.book.metas.author"/>: ${bookMeta.authors}<br>
            <fmt:message key="page.book.metas.genre"/>: ${bookMeta.genres}<br>
            <c:if test="${not empty bookMeta.series}">
                <fmt:message key="page.book.metas.series"/>:
                ${bookMeta.series}<br>
            </c:if>
        </li>
    </c:forEach>
</ul>
<c:if test="${not empty requestScope.bookMetas}">
    <div>
        [${param.page}]
    </div>
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page + 1}"
          method="post">
        <div style="display: none">
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
                   name="series"
                   value="${param.series}"><br>
        </div>
        <button type="submit">
            <fmt:message key="page.button.next"/>
        </button>
    </form>
</c:if>
<c:if test="${param.page > 1}">
    <form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&page=${param.page - 1}"
          method="post">
        <div style="display: none">
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
                   name="series"
                   value="${param.series}"><br>
        </div>
        <button type="submit">
            <fmt:message key="page.button.previous"/>
        </button>
    </form>
</c:if>
</body>
</html>
