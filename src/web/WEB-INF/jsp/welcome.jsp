<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><c:out value = "login: ${sessionScope.get('login')}"/><br>
<c:out value = "email: ${sessionScope.get('email')}"/><br>
<c:out value = "sex: ${sessionScope.get('sex')}"/><br>
<c:out value = "age: ${sessionScope.get('age')}"/></p>
<form method="post" action="http://localhost:8080/trainingCalendar/training">
    <button>Тренировки</button>
</form>
</body>
</html>