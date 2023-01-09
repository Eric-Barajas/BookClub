<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach JSP tags etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tacos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div>
			<h1> <c:out value="${oneBook.title}"/> </h1>
		<c:choose>
			<c:when test="${user_id == oneBook.creator.id }"> 
				<p>You read <c:out value="${oneBook.title}"/> by <c:out value="${oneBook.author}"/></p>
				<p> Here are your thoughts</p>
			</c:when>
			<c:otherwise>
				<p> <c:out value="${oneBook.creator.firstName}"/> read <c:out value="${oneBook.title}"/> by <c:out value="${oneBook.author}"/></p>
				<p> Here are <c:out value="${oneBook.creator.firstName}"/>'s thoughts</p>
			</c:otherwise>
		</c:choose>
		</div>
		<hr />
		<div>
			<p>
				<c:out value="${oneBook.description}"/>
			</p>
		</div>
		<hr />
		<div>
		<c:if test="${user_id == oneBook.creator.id }"> 
			<a class="btn btn-dark" href="/books/${id}/edit"> edit </a> 
			<a class="btn btn-dark" href="/book/${oneBook.id}/delete"> DELETE </a> 
		</c:if> 
		</div>
	</div>
<!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>