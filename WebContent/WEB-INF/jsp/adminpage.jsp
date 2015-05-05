<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<noscript>
  <meta HTTP-EQUIV="Refresh" CONTENT="0;URL=/ExamModule/secure/jserror">
</noscript>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/externalresources/questionbank.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<center><h1 class="bg-primary"> MATHEMATICS TEST </h1> </center>
<center><h4>Hi, ${name}</h4></center>

<nav class="navbar navbar-default navbar-static-top">
<ul class="nav navbar-nav">
<li class="active"><a href="#">Home</a></li>
<li><a href="studentsRecord">Students Record</a></li>
<li><a href="resetAccount">Reset Student Account</a></li>
<li><a href="addUser">Add User</a></li>
<li><a href="javascript:formSubmit()">Logout</a> </li>
</ul>
</nav>
<br>
	<h2> Please read the below instructions:</h2>
	<h3>
	&nbsp;&nbsp;- You can click on the "Students Record" tab above to see details of students' performance on the test.<br>
	&nbsp;&nbsp;- You can click on the "Logout" tab to logout.<br>
	&nbsp;&nbsp;- You can click on the "Reset Student Account" tab to reset the test of a student. <br>
	</h3>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<!-- csrf for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
</body>
</html>
