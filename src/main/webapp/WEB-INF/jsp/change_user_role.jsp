<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="header.jsp" %>
    <title>
        Change user role
    </title>
    <style>
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            background: white;
            width: 100%;
            border-collapse: collapse;
            text-align: center;
            margin: 0 auto 20px;
        }

        tbody {
            display: table-row-group;
            vertical-align: middle;
            border-color: inherit;
        }

        tr {
            display: table-row;
            vertical-align: inherit;
            border-color: inherit;
        }

        th {
            font-size: 14px;
            font-weight: normal;
            color: black;
            border-bottom: 2px solid #158ea2;
            padding: 10px 8px;
        }

        td {
            color: black;
            padding: 9px 8px;
        }

        caption {
            font-size: 30px;
            line-height: 36px;
        }

        button.aqua {
            border-radius: 4px;
            color: #fff;
            display: block;
            width: 140px;
            height: 50px;
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
            font-size: 16px;
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
<form action="${pageContext.request.contextPath}/home?cmd=${param.cmd}&cfm"
      method="post">
    <table>
        <caption>
            USER
        </caption>
        <tbody>
        <tr>
            <th>ID</th>
            <th>LOGIN</th>
            <th>NAME</th>
            <th>SURNAME</th>
            <th>ROLE</th>
        </tr>
        <tr>
            <td>${requestScope.user.id}</td>
            <td>${requestScope.user.login}</td>
            <td>${requestScope.user.firstName}</td>
            <td>${requestScope.user.surname}</td>
            <td>
                <label for="role">
                    <select name="role"
                            id="role">
                        <c:forEach var="role" items="${requestScope.roles}">
                            <option value="${role}">
                                    ${role}
                            </option>
                        </c:forEach>
                    </select>
                </label>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
    <div style="display: none">
        <input type="hidden" name="id" value="${requestScope.user.id}">
        <input type="hidden" name="role" value="${requestScope.user.role}">
    </div>
    <button type="submit" class="aqua">
        <fmt:message key="page.button.confirm"/>
    </button>
    <br>
</form>
</body>
</html>
