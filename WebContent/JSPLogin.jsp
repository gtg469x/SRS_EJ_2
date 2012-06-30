<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ page import="java.util.*,java.text.*, java.io.*, edu.jhu.JavaEE.caldwell.kristin.login.StudentInfoBean" %>
<%int loginAttempt = 0; %>
<title>Login</title>
</head>

<form action="RegistrationController" method="POST" accept-charset="utf-8">
<body bgcolor="#ADD8E6">
	<h1>Welcome to the Student Registration Site</h1>
	<h3>If you already have an account, please log in</h3>
	<table>
			<tr>
				<td>UserID:</td>
				<td><input name="uidInput" type="text" size="8" /><td>
			<tr>
				<td>Password: </td>
				<td><input name="passInput" type="password" size="8"/><td>
			<tr>
				<td><input type="SUBMIT" value="submit"></td>
				<td><input type="RESET" value="cancel"></td>
			<tr>
		</table>
		<p>
		<p>
		
		<input type="hidden" name="formType" value="login" />
</form> 
	<!--  <form METHOD="GET" ACTION="RegistrationA.html">
		<td><input id="register" type="SUBMIT" value="register"></td>
	</form>-->
<jsp:useBean id="studentInfoBean" class="edu.jhu.JavaEE.caldwell.kristin.login.StudentInfoBean" scope="session"/>	
		<%System.out.println("I have made it to before the uidSetProperty"); %>
		<jsp:setProperty name="studentInfoBean" property="userId" param="uidInput" /> 
		<%System.out.println("I have made it after the uidSetProperty"); %>
		<jsp:setProperty name="studentInfoBean" property="password" param="passInput" />
		<!-- <%=studentInfoBean.executeLoginProcedure()%>  -->
<%  
		session.setAttribute("initialLoginAttempts",config.getInitParameter("loginAttempts"));
		if(session.getAttribute("loginAttempt")==null){
			session.setAttribute("loginAttempt", loginAttempt);
		}else{
			loginAttempt=(Integer)session.getAttribute("loginAtttempt");
			session.setAttribute("loginAttempt",loginAttempt++);
		}
		if((Integer)session.getAttribute("initialLoginAttempts") < (Integer)session.getAttribute("loginAttempt") ) {
			out.println("<html>");
			out.println("<head>");
			out.println("<title> Login Error </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>You have exceeded the maximum number of login attempts</h1>");
			out.println("</body>");
			out.println("</html>");
	}else{
		out.println("<html>");
		out.println("<head>");
		out.println("<title> </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>"); %> I have made it here <% 
		out.println("</h1>");
		out.println("</body>");
		out.println("</html>");
	}
%>

	<!-- 
	
	<h3>For new users, please register first</h3>
	<input type="button" name="redirect" value="register" onclick="window.location = 'registrationA.html'" />
	-->
	 
	 
</body>
</html>