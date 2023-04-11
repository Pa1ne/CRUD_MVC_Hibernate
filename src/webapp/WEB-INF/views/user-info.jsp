<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Info</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Create or edit user</h2>
<br>
    <form:form action="saveUser" modelAttribute="user" onsubmit="formatIt(${saveUser});">
        <form:hidden path="id"/>
        First Name: <form:input path="firstName" id="firstName"/>
        <br><br>
        Last Name: <form:input path="lastName" id="lastName"/>
        <br><br>
        Citizenship: <form:input path="citizenship" id="citizenship"/>
        <br><br>
        <input class='main-button' type="submit" value="OK">
    </form:form>
<script>
    formatIt = function() {
        let fn = document.getElementById("firstName");
        let ln = document.getElementById("lastName");
        let cp = document.getElementById("citizenship");
        if (fn.value === '') {
            alert ("Введите First Name");
        } else if (ln.value === '') {
            alert ("Введите Last Name");
        } else if (cp.value === '') {
            alert ("Введите Citizenship");
        }
    }
</script>
</body>
</html>
