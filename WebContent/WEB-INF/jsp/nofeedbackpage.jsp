<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question Bank</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/questionbank.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/externalresources/timer/countdowntimer.js"></script>
</head>
<body onload="initializeExamTimer('${TIME}');">
	<center>
		<h1 class="bg-primary">MATHEMATICS TEST</h1>
	</center>
	<center>
		<h4>Hi! ${USERNAME}</h4>
	</center>

	<nav class="navbar navbar-default navbar-static-top">
	<ul class="nav navbar-nav">
		<li class="active"><a href="#">Test</a></li>
		<li><a href="javascript:formSubmit()">Logout</a></li>
	</ul>
	</nav>
	<br>
	<div>
		<input type="text" id="time" readonly="true">
	</div>
	<br>
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
	
	<a class="btn btn-info" href="nextQuestion">Go to Next Question</a>
	
</body>
</html>
