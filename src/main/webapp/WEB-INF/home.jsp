<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
</head>
<body>
    <table>
        <tr>
            <td>${message}</td>
        </tr>
        <tr>
            <td>userName</td>
            <td>${userName}</td>
        </tr>
        <tr>
            <td>address</td>
            <td>${address}</td>
        </tr>
        <tr>
            <td>Subscribed to Newsletter </td>
            <td>${receivePaper}</td>
        </tr>
    </table>
</body>
</html>
<%@page isELIgnored="false" %>
