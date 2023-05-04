<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><c:out value = "login: ${sessionScope.get('login')}"/></p>
<p><c:out value = "email: ${sessionScope.get('email')}"/></p>
<p><c:out value = "sex: ${sessionScope.get('sex')}"/></p>
<p><c:out value = "age: ${sessionScope.get('age')}"/></p>
</body>
</html>