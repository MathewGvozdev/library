<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'ru_RU'}"/>
<fmt:setBundle basename="translations"/>
<a href="${pageContext.request.contextPath}/home">
    <fmt:message key="page.header.to.home"/>
</a><br>
<c:if test="${empty sessionScope.user}">
    <div id="login">
        <form action="${pageContext.request.contextPath}/home?cmd=login"
              method="post">
            <input type="submit"
                   value=" <fmt:message key="page.header.login"/>">
        </form>
    </div>
    <div id="registration">
        <form action="${pageContext.request.contextPath}/home?cmd=registration"
              method="post">
            <input type="submit"
                   value=" <fmt:message key="page.header.registration"/>">
        </form>
    </div>
</c:if>
<c:if test="${not empty sessionScope.user}">
    <div id="cabinet">
        <form action="${pageContext.request.contextPath}/home?cmd=find_user_info&userId=${sessionScope.user.id}"
              method="post">
                ${sessionScope.user.login}
            <button type="submit">
                Личный кабинет
            </button>
        </form>
    </div>
    <div id="logout">
        <form action="${pageContext.request.contextPath}/home?cmd=logout" method="post">
            <button type="submit">
                <fmt:message key="page.header.logout"/>
            </button>
        </form>
    </div>
</c:if>
<div id="locale">
    <form action="${pageContext.request.contextPath}/home?cmd=change_locale"
          method="post">
        <button type="submit"
                name="lang"
                value="ru_RU">
            RU
        </button>
        <button type="submit"
                name="lang"
                value="en_US">
            EN
        </button>
    </form>
</div>
