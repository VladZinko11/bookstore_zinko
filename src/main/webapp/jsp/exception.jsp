<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="navBar.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="css/stylesheet.css">
    <title>Title</title>
</head>
<body>
<h1 class="error">
    <div class="error_status"><c:out value="${pageContext.response.status}"></c:out></div>
    <c:out value="${message.toString()}"></c:out> <br/>
</h1>
</body>
</html>
