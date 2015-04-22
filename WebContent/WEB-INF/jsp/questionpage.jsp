<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions</title>
<!-- Latest compiled and minified CSS -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/bootstrap/css/bootstrap-theme.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/externalresources/questionbank.css">

<!-- Latest compiled and minified JavaScript -->
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/externalresources/bootstrap/js/bootstrap.min.js"></script>
<!-- Latex to image -->
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/externalresources/fMath/fonts/fmathFormulaFonts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/externalresources/fMath/js/fmathFormulaC.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/externalresources/timer/countdowntimer.js"></script>
<script>
	FMATH.ApplicationConfiguration.setFolderUrlForFonts("fonts");
	FMATH.ApplicationConfiguration.setFolderUrlForGlyphs("glyphs");

	function convertFromLatexToMathML() {
		var latexInput = document.getElementById("latex");
		var latexCanvas = document.getElementById("latexCanvas");
		var formula = new FMATH.MathMLFormula();
		var mathml = formula.convertLatexToMathML(latexInput.value);
		formula.drawImage(latexCanvas, mathml);
	}
</script>

</head>
<body
	onload="initializeExamTimer('${TIME}');convertFromLatexToMathML();">
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
	<form:form method="POST" modelattribute="command">
		<table>
			<tr>
				<td><form:hidden path="questionOrder"
						value="${command.questionOrder}" /></td>
				<td><form:hidden path="questionId"
						value="${command.questionId}" /></td>
				<td><form:hidden path="questionType"
						value="${command.questionType}" /></td>
				<td><form:hidden path="startTime" value="${command.startTime}" /></td>
			</tr>
			<tr>
				<td><input type="hidden" id="latex"
					value="${command.wholeQuestion}" style="width: 500px; height: 20px"></input>
					<canvas id="latexCanvas" width="0" height="0"
						style="border:0px solid #000000;"></canvas></td>
				<td><c:choose>
						<c:when test="${not empty command.questionImage}">
							<img alt="question image"
								src="${pageContext.request.contextPath}/externalresources/images/${command.questionImage}">
						</c:when>
					</c:choose></td>
			</tr>
			<tr>
				<td><form:select element="li" path="selectedOption">
						<c:forEach items="${optionList}" var="option">
							<form:option value="${option.value}" label="${option.key}" />
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Submit"></td>
			</tr>
		</table>
	</form:form>

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
