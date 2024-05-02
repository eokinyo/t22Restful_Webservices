<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>All the robots</h1>
<%--
<ul>
<c:forEach var="robot" items="${requestScope.robots }">
	<li>${robot.id}: ${robot.name } / ${robot.speed }/ ${robot.iswhite }
	<a href='rest/t22RestfulProject/deleterobot/${robot.id}'>Delete</a>
	<a href='rest/t22RestfulProject/readforupdate/${robot.id}'>Update</a>
</c:forEach>
</ul>
--%>
<form action="/rest/t22RestfulProject/updaterobot" method="post">
ID:<input type="text" name="id" value="${requestScope.robot.id}"><br>
Name:<input type="text" name="name" value="${requestScope.robot.name}"><br>
Speed:<input type="text" name="weight" value="${requestScope.robot.speed}"><br>
Iswhite?<br>
<input type="radio" name="iswhite" value="0" ${resuestScope.robot.iswhite == 0? 'checked' : ''}>No<br>
<input type="radio" name="iswhite" value="1" ${resuestScope.robot.iswhite == 1? 'checked' : ''}>Yes<br>
<input type="submit" name="OK" value="Send"><br>
</form>
</body>
</html>