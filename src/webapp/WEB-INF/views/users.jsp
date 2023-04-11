<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
    <meta charset="UTF-8">
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<h2>All Users</h2>

<table>
    <tr bgcolor="#949494">
        <th>First Name</th>
        <th>Last name</th>
        <th>Citizenship</th>
        <th>Operations</th>
    </tr>
    <tbody>
    <c:forEach var="user" items="${users}">

        <c:url var="updateButton" value="/updateUser">
            <c:param name="userId" value="${user.id}"/>
        </c:url>
        <c:url var="deleteButton" value="/deleteUser">
            <c:param name="userId" value="${user.id}"/>
        </c:url>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.citizenship}</td>
            <td>
                <input type="button" value="Update" onclick="window.location.href='${updateButton}'"/>
                <input type="button" value="Delete" onclick="window.location.href='${deleteButton}'"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<input type="button" value="Add" onclick="window.location.href = 'addUser'"/>
</body>
</html>