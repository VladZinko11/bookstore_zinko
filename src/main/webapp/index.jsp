<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2024
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jsp/navBar.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="users" methods="get" content="${pageContext.request.contextPath}">users</a><br/>
    <a href="books" methods="get" content="${pageContext.request.contextPath}">books</a><br/>
</body>
</html>
