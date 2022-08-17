<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        <fmt:message key="head.admin.menu"/>
    </title>
    <style>
        .centered-wrapper {
            position: relative;
            text-align: center;
        }

        .centered-content {
            display: inline-block;
            margin: 40px;
        }
        button.aqua {
            border-radius: 4px;
            color: #fff;
            display: block;
            width: 240px;
            height: 80px;
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 24px;
            padding: 12px 20px;
            margin: 30px auto;
            text-decoration: none;
        }

        button.aqua {
            background-color: #2b6068;
            border: 1px solid #115868;
        }

        button.aqua:hover {
            background-color: #158ea2;
        }
    </style>
</head>
<body>
<div class="centered-wrapper">
    <div class="centered-content">
        <form action="${pageContext.request.contextPath}/home"
              method="get">
            <div style="display: none">
                <input type="hidden"
                       name="cmd"
                       value="add_book">
            </div>
            <button type="submit" class="aqua">
                <fmt:message key="page.admin.menu.add.book"/>
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/home"
              method="get">
            <div style="display: none">
                <input type="hidden"
                       name="cmd"
                       value="delete_book">
            </div>
            <button type="submit" class="aqua">
                <fmt:message key="page.admin.menu.delete.book"/>
            </button>
        </form>
    </div>
    <div class="centered-content">
        <form action="${pageContext.request.contextPath}/home"
              method="get">
            <div style="display: none">
                <input type="hidden"
                       name="cmd"
                       value="find_all_books">
                <input type="hidden"
                       name="page"
                       value="1">
            </div>
            <button type="submit" class="aqua">
                <fmt:message key="page.admin.menu.show.examples"/>
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/home"
              method="get">
            <div style="display: none">
                <input type="hidden"
                       name="cmd"
                       value="find_book_by_id">
            </div>
            <button type="submit" class="aqua">
                <fmt:message key="page.admin.menu.show.by.id"/>
            </button>
        </form>
    </div>
    <div class="centered-content">
        <form action="${pageContext.request.contextPath}/home"
              method="get">
            <div style="display: none">
                <input type="hidden"
                       name="cmd"
                       value="find_all_orders">
                <input type="hidden"
                       name="page"
                       value="1">
                <input type="hidden"
                       name="status"
                       value="all">
            </div>
            <button type="submit" class="aqua">
                <fmt:message key="page.admin.menu.find.orders"/>
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/home"
              method="get">
            <div style="display: none">
                <input type="hidden"
                       name="cmd"
                       value="find_all_users">
                <input type="hidden"
                       name="page"
                       value="1">
            </div>
            <button type="submit" class="aqua">
                <fmt:message key="page.admin.menu.find.users"/>
            </button>
        </form>
    </div>
</div>
</body>
</html>
