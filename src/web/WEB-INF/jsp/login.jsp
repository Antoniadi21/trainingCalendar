<%--
  Created by IntelliJ IDEA.
  User: nikit
  Date: 03.05.2023
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method = "post" action = "http://localhost:8080/trainingCalendar/login">
    <label>
        <input type = "text" name = "login" value= "Введите логин">
    </label>
    <label>
        <input type = "password" name = "password" value="password">
    </label>
    <button type = "submit">Вход</button>
</form>

</body>
</html>
