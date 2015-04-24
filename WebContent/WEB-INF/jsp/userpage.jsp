<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<noscript>
	<meta HTTP-EQUIV="Refresh" CONTENT="0;URL=/ExamModule/secure/jserror">
</noscript>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
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
</head>
<body>
	<center>
		<h1 class="bg-primary">MATHEMATICS TEST</h1>
	</center>
	<center>
		<h4>Hi, ${name}</h4>
	</center>

	<nav class="navbar navbar-default navbar-static-top">
	<ul class="nav navbar-nav">
		<li class="active"><a href="#">Home</a></li>
		<!-- <li><a href="question">Solve Questions</a></li>-->
		<li><a href="javascript:formSubmit()">Logout</a></li>
	</ul>
	</nav>
	<br>
	<c:choose>
		<c:when test="${ACCOUNT_BLOCKED}">
			<a class="btn btn-info" href="question">Continue Test</a>
		</c:when>
		<c:otherwise>
			<h2>Please read the below instructions before you start the
				test:</h2>
			<h3>
				&nbsp;&nbsp;- Once started, the test could not be restarted. So
				please make sure you complete the test once you start.<br>
				&nbsp;&nbsp;- The test duration is 2 hours, you will be able to see
				the remaining time.<br> &nbsp;&nbsp;- During the test you will
				face questions with five options.<br> &nbsp;&nbsp;- Please
				select the most suitable option from the drop-down list as answer
				and hit submit.<br> &nbsp;&nbsp;- You can not go back to a
				previous question once you have submitted the answer for that
				question. <br> &nbsp;&nbsp;- You will be logged out if you stay
				inactive for more than 5 minutes.<br> &nbsp;&nbsp;- Please
				logout once you complete the test.<br> &nbsp;&nbsp;- You will
				not be allowed to login again once you complete the test.<br>
				&nbsp;&nbsp;- If you face any trouble, please inform the test
				administrator.<br>
			</h3>
			<br>
	&nbsp;&nbsp;<a class="btn btn-info" href="test">Begin Test</a>

		</c:otherwise>
	</c:choose>

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
</body>
</html>