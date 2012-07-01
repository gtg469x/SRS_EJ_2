<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="java.util.*,java.text.*, java.io.*, edu.jhu.JavaEE.caldwell.kristin.login.StudentInfoBean" %>
<jsp:useBean id="studentInfoBean" class="edu.jhu.JavaEE.caldwell.kristin.login.StudentInfoBean" scope="session"/>
<title>Registration</title>
</head>
<body>
<%	//StudentInfoBean sib = new StudentInfoBean();
		session.setAttribute("weblogicURL", config.getInitParameter("weblogicURL"));
		System.out.println((String)session.getAttribute("weblogicURL"));
		session.setAttribute("jhuDataSource", config.getInitParameter("jhuDataSource"));
		System.out.println((String)session.getAttribute("jhuDataSource"));
	if(session.getAttribute("registrationFormAttribute").equals("redirectRegister")){
		studentInfoBean.setWeblogicUrl(config.getInitParameter("weblogicURL"));
		studentInfoBean.setDataSource(config.getInitParameter("jhuDataSource"));
		out.println("<html>");
		out.println("<head>");
		out.println("<title>RegistrationA</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Registration Form A</h1>");
		out.println("<form action=\"RegistrationController\" method=\"POST\" accept-charset=\"utf-8\"> " +
				"<table>" +
					"<tr>" +
						"<td>UserID:</td>" +
						"<td><input name=\"uidInput\" type=\"text\" size=\"12\" /></td>" +
					"<tr>" +
						"<td>Password: </td>" +
						"<td><input name=\"passInput\" type=\"password\" size=\"12\"/></td>" +
					"<tr>" +
						"<td>Password (Repeat): </td>" +
						"<td><input name=\"passInputR\" type=\"password\" size=\"12\"/></td>" +
					"<tr>" +
						"<td>First Name:</td> " +
						"<td><input name=\"fName\" type=\"text\" size=\"12\" /></td> " +
					"<tr>" + 
						"<td>Last Name:</td>" +
						"<td><input name=\"lName\" type=\"text\" size=\"12\" /></td>" +
					"<tr>" +
						"<td>Phone:</td>" +
						"<td><input name=\"phone\" type=\"text\" size=\"12\" /></td>" +
				"</table>" +
				"<table>" +
				"<tr>" +
						"<td>Social Security Number:</td>" +
						"<td><input name=\"Ssn1\" type=\"text\" size=\"3\" /></td>" +
						"<td><input name=\"Ssn2\" type=\"text\" size=\"2\" /></td>" +
						"<td><input name=\"Ssn3\" type=\"text\" size=\"4\" /></td>" +
				"</table>" +
				"<table>" +
					"<tr>" +
						"<td>Email:</td>" +
						"<td><input name=\"email\" type=\"text\" size=\"20\" /></td>" +
					"<tr>" +
						"<td><input name=\"submitA\" type=\"SUBMIT\" value=\"Continue\" ></td>" +
						"<input type=\"hidden\" name=\"formType\" value=\"registrationA\" />" +
				"</table>" +
			"</form>" +
		"</body>"+
		"</html>");
	}else if(session.getAttribute("registrationFormAttribute").equals("registrationA")){
	%>
		<jsp:setProperty name="studentInfoBean" property="uname" param="uidInput"/>
		<jsp:setProperty name="studentInfoBean" property="pass" param="passInput"/>
		<jsp:setProperty name="studentInfoBean" property="firstName" param="fName"/>
		<jsp:setProperty name="studentInfoBean" property="lastName" param="lName"/>
		<jsp:setProperty name="studentInfoBean" property="ssn1" param="Ssn1"/>
		<jsp:setProperty name="studentInfoBean" property="ssn2" param="Ssn2"/>
		<jsp:setProperty name="studentInfoBean" property="ssn3" param="Ssn3"/>
		<jsp:setProperty name="studentInfoBean" property="email" param="email"/>
		
	<%
		System.out.println(studentInfoBean.getUname());
		studentInfoBean.concatSSN();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>RegistrationB</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Registration Form B</h1>");
		out.println("" +
"	<form action=\"RegistrationController\" method=\"POST\" accept-charset=\"utf-8\"> " +
"		<table> " +
"			<tr> " +
"				<td>Address:</td> " +
"				<td><input name=\"address\" type=\"text\" size=\"40\" /></td> " +
"		</table> " +
"		<table> " +
"			<tr> " +
"				<td>City: </td> " +
"				<td><input name=\"city\" type=\"text\" size=\"30\"/></td> " +
"			<tr> " +
"				<td>State: </td> " +
"				<td><select name=\"state\"> " +
"					<option value=\"\" selected=\"selected\">Select a State</option> " +
"					<option value=\"AL\">Alabama</option> " +
"					<option value=\"AK\">Alaska</option> " +
"					<option value=\"AZ\">Arizona</option> " +
"					<option value=\"AR\">Arkansas</option> " +
"					<option value=\"CA\">California</option> " +
"					<option value=\"CO\">Colorado</option> " +
"					<option value=\"CT\">Connecticut</option> " +
"					<option value=\"DE\">Delaware</option> " +
"					<option value=\"DC\">District Of Columbia</option> " +
"					<option value=\"FL\">Florida</option> " +
"					<option value=\"GA\">Georgia</option> " +
"					<option value=\"HI\">Hawaii</option> " +
"					<option value=\"ID\">Idaho</option> " +
"					<option value=\"IL\">Illinois</option> " +
"					<option value=\"IN\">Indiana</option> " +
"					<option value=\"IA\">Iowa</option> " +
"					<option value=\"KS\">Kansas</option> " +
"					<option value=\"KY\">Kentucky</option> " +
"					<option value=\"LA\">Louisiana</option> " +
"					<option value=\"ME\">Maine</option> " +
"					<option value=\"MD\">Maryland</option> " +
"					<option value=\"MA\">Massachusetts</option> " + 
"					<option value=\"MI\">Michigan</option> " +
"					<option value=\"MN\">Minnesota</option> " +
"					<option value=\"MS\">Mississippi</option> " +
"					<option value=\"MO\">Missouri</option> " +
"					<option value=\"MT\">Montana</option> " +
"					<option value=\"NE\">Nebraska</option> " +
"					<option value=\"NV\">Nevada</option> " +
"					<option value=\"NH\">New Hampshire</option> " + 
"					<option value=\"NJ\">New Jersey</option> " +
"					<option value=\"NM\">New Mexico</option> " +
"					<option value=\"NY\">New York</option> " +
"					<option value=\"NC\">North Carolina</option> " + 
"					<option value=\"ND\">North Dakota</option> " +
"					<option value=\"OH\">Ohio</option> " +
"					<option value=\"OK\">Oklahoma</option> " + 
"					<option value=\"OR\">Oregon</option> " +
"					<option value=\"PA\">Pennsylvania</option> " + 
"					<option value=\"RI\">Rhode Island</option> " +
"					<option value=\"SC\">South Carolina</option> " +
"					<option value=\"SD\">South Dakota</option> " +
"					<option value=\"TN\">Tennessee</option> " +
"					<option value=\"TX\">Texas</option> " +
"					<option value=\"UT\">Utah</option> " +
"					<option value=\"VT\">Vermont</option> " +
"					<option value=\"VA\">Virginia</option> " +
"					<option value=\"WA\">Washington</option> " +
"					<option value=\"WV\">West Virginia</option> " +
"					<option value=\"WI\">Wisconsin</option> " +
"					<option value=\"WY\">Wyoming</option> " +
"					</select> " +
"				</td> " +
"			<tr> " +
"				<td>Zip Code:</td> " +
"				<td><input name=\"zip\" type=\"text\" size=\"9\" /></td> " +
"		</table> " +
"		<table> " +
"				<td><input name=\"submit1\" type=\"SUBMIT\" value=\"submit\"></td> " +
"				<td><input name=\"reset\" type=\"RESET\" value=\"reset\"> " +
"		</table> " +
"		<input type=\"hidden\" name=\"formType\" value=\"registrationB\" /> " +
"		<td> " +		
"		<jsp:getProperty name=\"loginSuccessful\" property=\"firstName\"/> " +
"	</form> " +
"</body> " +
"</html> " +
 "");
 
	}else if(session.getAttribute("registrationFormAttribute").equals("registrationB")){
	%>	
		<jsp:setProperty name="studentInfoBean" property="address" param="address"/>
		<jsp:setProperty name="studentInfoBean" property="city" param="city"/>
		<jsp:setProperty name="studentInfoBean" property="state" param="state"/>
		<jsp:setProperty name="studentInfoBean" property="zip" param="zip"/>
	<%  
		studentInfoBean.processRegistration();
		if(studentInfoBean.validateRegistration()){	
			session.setAttribute("storedFirstName", studentInfoBean.getFirstName());
			session.setAttribute("storedLastName", studentInfoBean.getLastName());
			RequestDispatcher dispatcher= request.getRequestDispatcher("/welcome2.jsp");
			dispatcher.forward(request,response);
		}else{
		out.println("<html>");
		out.println("<head>");
		out.println("<title>RegistrationFailed</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Registration Fatal Error</h1>");
		out.println("</body>");
		out.println("</html>");
		}
			
	}else{
		out.println("<html>");
		out.println("<head>");
		out.println("<title>DebugginPage</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>DebuggingPage</h1>");
		out.println("</body>");
		out.println("</html>");
	}
 %>


</body>
</html>