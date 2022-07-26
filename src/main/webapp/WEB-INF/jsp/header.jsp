<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'ru_RU')}"/>
<fmt:setBundle basename="translations"/>
<a href="${pageContext.request.contextPath}/home">
    <fmt:message key="page.header.to.home"/>
</a><br>
<c:if test="${empty sessionScope.user}">
    <form action="${pageContext.request.contextPath}/home?cmd=login" method="post">
        <input type="submit" value=" <fmt:message key="page.header.login"/>">
    </form>
    <form action="${pageContext.request.contextPath}/home?cmd=register" method="post">
        <input type="submit" value=" <fmt:message key="page.header.registration"/>">
    </form>
</c:if>
<c:if test="${not empty sessionScope.user}">
    ${sessionScope.user.login}
    <div id="logout">
        <form action="${pageContext.request.contextPath}/home?cmd=logout" method="post">
            <button type="submit">
                <fmt:message key="page.header.logout"/>
            </button>
        </form>
    </div>
</c:if>
<div id="locale">
    <form action="${pageContext.request.contextPath}/home?cmd=change_locale" method="post">
        <button type="submit" name="lang" value="ru_RU">RU</button>
        <button type="submit" name="lang" value="en_US">EN</button>
    </form>
</div>
</body>
</html>
