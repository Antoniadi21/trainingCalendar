<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><c:out value = "Тренировки пользователя ${sessionScope.get('login')}"/>: <c:forEach var="training" items="${trainings}"><br><c:out value="${training}"/>
</c:forEach></p>
</body>
</html>
