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
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" >
		<div class="row">
			
			<div class="col">
				<h1> Welcome, <c:out value="${oneUser.firstName}" /> <c:out value="${oneUser.lastName}" /></h1>
				<p> Books from everyone's shelves </p>
			</div>
			<div class="col">
				<a href="/logout">logout</a> <br>
				<a href="/books/new">Add a book to my shelf</a>
			</div>
		</div>
		<div>
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Title</th>
			      <th scope="col">Author name</th>
			      <th scope="col">Posted By</th>
			    </tr>
			  </thead>
			  <tbody>
			    <c:forEach var="book" items="${allBooks}">
			    	<tr>
			    		<th scope="row">${book.id}</th>
			    		<td> 
			      			<a href="/books/${book.id}"> <c:out value="${book.title}"/> </a> 
<%-- 			      			<c:if test="${user_id == book.creator.id}">
			      				<a href=""></a>
			      			</c:if> --%>
			      		</td>
			      		<td> <c:out value="${book.author}" /> </td>
			    		<td> <c:out value="${book.creator.firstName}" /> <c:out value="${book.creator.lastName}" /> </td>
			    	</tr>
			    </c:forEach>
			  </tbody>
			</table>
		</div>
	</div>
<!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</body>
</html>