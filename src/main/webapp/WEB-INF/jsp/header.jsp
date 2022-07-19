<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<a href="${pageContext.request.contextPath}/home">
    На главную
</a><br>
<c:if test="${empty sessionScope.user}">
    <form action="${pageContext.request.contextPath}/home" method="get">
        <input type="hidden" name="cmd" value="login">
        <input type="submit" value="login">
    </form>
</c:if>
<c:if test="${not empty sessionScope.user}">
    ${sessionScope.user.login}
    <div id="logout">
        <form action="${pageContext.request.contextPath}/home?cmd=logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</c:if>
<%--<div id="locale">--%>
<%--    <form action="${pageContext.request.contextPath}/locale" method="post">--%>
<%--        <button type="submit" name="lang" value="ru_RU">RU</button>--%>
<%--        <button type="submit" name="lang" value="en_US">EN</button>--%>
<%--    </form>--%>
<%--</div>--%>
</body>
</html>
