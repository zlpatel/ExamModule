<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/questionbank.css">

<link
	href="${pageContext.request.contextPath}/externalresources/font-awesome-4.3.0/css/font-awesome.min.css"
	rel="stylesheet">

<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Latest compiled and minified JavaScript -->
<script
	src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<center>
		<h1 class="bg-primary">MATHEMATICS TEST</h1>
	</center>
	<center>
		<h4>Hi! ${USERNAME}</h4>
	</center>

	<nav class="navbar navbar-default navbar-static-top">
	<ul class="nav navbar-nav">
		<li><a href="home">Home</a></li>
		<li><a href="studentsRecord">Students Record</a></li>
		<li class="active"><a href="#">Reset Student Account</a></li>
		<li><a href="javascript:formSubmit()">Logout</a></li>
	</ul>
	</nav>
	<br>
	<center>
		<h4>${message}</h4>
	</center>
	<form id="resetform" action="resetFormSubmit" method="post"
		modelattribute="command">

		<div class="container">
			<div class="row">
				<div class="col-md-offset-5 col-md-3">
					<div class="form-login">
						<h4>Reset Student Account</h4>
						<input type="text" id="userName" name="userName"
							placeholder="username" value="${command.userName}" class="form-control input-sm chat-input" required
							autofocus /> </br> <input type="text" id="fullName" name="fullName"
							placeholder="full name" value="${command.fullName}" class="form-control input-sm chat-input" required /> </br>
						<div class="wrapper">
							<span class="group-btn">
								<center>
									<a href="javascript:resetFormSubmit()"
										class="btn btn-primary btn-md">submit</a>
								</center>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>

		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<!-- csrf for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<script>
		function resetFormSubmit() {
			document.getElementById("resetform").submit();
		}
	</script>
</body>
</html>