<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'ru_RU'}"/>
<fmt:setBundle basename="translations"/>
<style>
    .header {
        width: 100%;
        height: 80px;
        display: block;
        background-color: #2b6068;
    }
    .inner_header {
        width: 1200px;
        height: 100%;
        display: block;
        margin: 0 auto;
    }
    .logo_container {
        height: 100%;
        display: table;
        float: left;
    }
    .logo_container h1 {
        color: white;
        height: 100%;
        display: table-cell;
        vertical-align: middle;
        font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
        font-size: 32px;
    }
    .lang_container {
        width: 100px;
        height: 100%;
        display: table;
        float: right;
    }
    .lang_container a {
        color: white;
        height: 100%;
        display: table-cell;
        vertical-align: middle;
        font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
        font-size: 16px;
        text-decoration: none;
    }
    .lang_container a:hover {
         text-decoration: underline;
     }
    .navigation {
        float: right;
        height: 100%;
        margin: 0;
    }
    .navigation li {
        height: 100%;
        display: table;
        float: left;
        padding: 0 20px;
    }
    .navigation li a {
        display: table-cell;
        vertical-align: middle;
        height: 100%;
        color: white;
        font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
        font-size: 24px;
        text-decoration: none;
    }
    .navigation li a:hover {
        text-decoration: underline;
    }
</style>
<div class="header">
    <div class="lang_container">
        <a href="${pageContext.request.contextPath}/home?cmd=change_locale&lang=ru_RU">
            RU
        </a>
        <a href="${pageContext.request.contextPath}/home?cmd=change_locale&lang=en_US">
            EN
        </a>
    </div>
    <div class="inner_header">
        <div class="logo_container">
            <h1>LIBRARY</h1>
        </div>
        <ul class="navigation">
            <li>
                <a href="${pageContext.request.contextPath}/home">
                    <fmt:message key="page.header.to.home"/>
                </a>
            </li>
            <c:if test="${empty sessionScope.user}">
                <li>
                    <a href="${pageContext.request.contextPath}/home?cmd=login">
                        <fmt:message key="page.header.login"/>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/home?cmd=registration">
                        <fmt:message key="page.header.registration"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <li>
                    <a href="${pageContext.request.contextPath}/home?cmd=find_user_info&userId=${sessionScope.user.id}">
                            ${sessionScope.user.login}
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/home?cmd=logout">
                        <fmt:message key="page.header.logout"/>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</div>