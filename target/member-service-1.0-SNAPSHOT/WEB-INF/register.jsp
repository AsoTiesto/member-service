<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<p>Welcome to SpringMVC!</p>
<form:form method = "POST" action = "/process">
    <table>
        <tr>
            <td><form:label path = "userName">user name</form:label></td>
            <td><form:input path = "userName" /></td>
        </tr>
        <tr>
            <td><form:label path = "password">password</form:label></td>
            <td><form:input path = "password" /></td>
        </tr>
        <tr>
            <td colspan = "5">
                <input type = "submit" value = "Register"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
<%@page isELIgnored="false" %>
