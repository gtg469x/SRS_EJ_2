<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<!--<jsp:root xmlns:jsp="http://java.sun.com/JSP/page" version="1.2">-->
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Welcome</title>
		<meta name="generator" content="TextMate http://macromates.com/">
		<meta name="author" content="Kristin Caldwell">
		<jsp:useBean id="loginSuccessful" class="edu.jhu.JavaEE.caldwell.kristin.login.LoginSuccessful" scope="request"/>
		<jsp:useBean id="loginFail" class="edu.jhu.JavaEE.caldwell.kristin.login.LoginFail" scope="request"/>
		<!--<jsp:usebean id="loginSuccessful" class="edu.jhu.JavaEE.caldwell.kristin.login.LoginSuccessful" scope="session"/> -->
	<!-- Date: 2012-06-20 -->
	</head>
	<body bgcolor="#ADD8E6">
		<h1>
		<jsp:getProperty name="firstName" property="loginSuccessful"/>
		<jsp:getProperty name="lastName" property="loginSuccessful"/>
		</h1>
		<h1>
		<jsp:getProperty name="loginFailMessage" property="loginFail"/>
		</h1>
	</body>
</html>
<!-- </jsp:root>  -->
