<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<jsp:useBean id="studentInfoBean" class="edu.jhu.JavaEE.caldwell.kristin.login.StudentInfoBean" scope="session"/>
		<!-- <jsp:useBean id="loginSuccessful" class="edu.jhu.JavaEE.caldwell.kristin.login.LoginSuccessful" scope="request"/> -->
<title>Welcome</title>
</head>
	<body bgcolor="#ADD8E6">
	<h1> Welcome, 
		<%=session.getAttribute("storedFirstName") %>
		<%=session.getAttribute("storedLastName") %>
	</h1>

	<p>
	<form action="RegistrationController" method="POST" accept-charset="utf-8">
		Select your  next action:
		<table>
		<td>
			<input type="radio" name="nextSteps" value="register"/> Register for the course
			<td>
			<input type="radio" name="nextSteps" value="logout"/> Logout
		</table>
			<p>
			<p>
		<td><input type="SUBMIT" value="submit"></td>
		<td><input type="hidden" name="formType" value="welcome2" />
	</form>
	
</body>
</html>