<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:useBean id="loginFail" class="edu.jhu.JavaEE.caldwell.kristin.login.LoginFail" scope="request"/>
<title>Login Fail</title>
</head>
<body>
	<h1>
		<jsp:getProperty name="loginFail" property="loginFailMessage"/>
	</h1>

</body>
</html>