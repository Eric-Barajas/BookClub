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
		<div class="row">
			<div class="col">
				<h1>Add a Book to Your Shelf!</h1>
			</div>
			<div class="col">
				<a href="/books">back to the shelves</a> <br>
			</div>
		</div>	
		<div class="text-danger">
			<p> Author must not be blank</p>
		</div>
		<form:form modelAttribute="bookObj" action="/books/new" method="POST">
			<form:input type="hidden" path="creator" value="${user_id}" />
			<!-- modelAttrivute matches @modelAttribute in @Getmapping BookController -->
			<div class="form-group">
				<form:label path="title" class="form-label">Title:</form:label>
				<form:errors path="title" class="text-danger" />
				<form:input path="title" id="" class="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="author" class="form-label">Author:</form:label>
				<form:errors path="author" class="text-danger" />
				<form:input path="author" id="" class="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="description" class="form-label">My thoughts:</form:label>
				<form:errors path="description" class="text-danger" />
				<form:textarea path="description" id="" class="form-control"/>
			</div>
			<input type="submit" value="submit"/>
		<!-- submit button doesn't need form tag -->
		<!-- path names have to match member variables -->
		</form:form>
	</div>
<!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>