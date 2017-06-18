<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>login</title>
</head>
<body>

<form:form method = "POST" action = "/verify">
    <table>
        <tr>
            <td><form:label path = "userName">user name</form:label></td>
            <td><form:input path = "userName" /></td>
        </tr>
        <tr>
            <td><form:label path = "password">password</form:label></td>
            <td><form:password path = "password" /></td>
        </tr>

        <tr>
            <td colspan = "2">
                <input type = "submit" value = "login"/>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
<%@page isELIgnored="false" %>
