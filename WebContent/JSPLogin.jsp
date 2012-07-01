<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ page import="java.util.*,java.text.*, java.io.*, edu.jhu.JavaEE.caldwell.kristin.login.StudentInfoBean" %>

<title>Login</title>
</head>
 <body>	
<%  try{
		StudentInfoBean sib = new StudentInfoBean();
		System.out.println("I am in the JSPLoginPage");
		System.out.println((Integer)session.getAttribute("loginAttempt"));
		sib.setWeblogicUrl(config.getInitParameter("weblogicURL"));
		session.setAttribute("weblogicURL", config.getInitParameter("weblogicURL"));

		sib.setDataSource(config.getInitParameter("jhuDataSource"));
		session.setAttribute("jhuDataSource", config.getInitParameter("jhuDataSource"));
		
		if(session.getAttribute("initialLoginAttempts")==null){
			out.println("<html>");
			out.println("<head>");
			out.println("<title> Student Registration System </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action=\"RegistrationController\" method=\"POST\" accept-charset=\"utf-8\">");
			out.println("<h1>Welcome to the Student Registration Site</h1>");
			out.println("<h3>If you already have an account, please log in</h3>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>UserID:</td>");
			out.println("<td><input name=\"uidInput\" type=\"text\" size=\"8\" /><td>");
			out.println("<tr>");
			out.println("<td>Password: </td>");
			out.println("<td><input name=\"passInput\" type=\"password\" size=\"8\"/><td>");
			out.println("<tr>");
			out.println("<td><input type=\"SUBMIT\" value=\"submit\"></td>");
			out.println("<td><input type=\"RESET\" value=\"cancel\"></td>");
			out.println("<tr>");
			out.println("</table>");
			out.println("<p><p>");
			out.println("<input type=\"hidden\" name=\"formType\" value=\"login\" />");
			out.println("</form>");
			out.println("<h3>For new users, please register first</h3>");
			out.println("<form action=\"RegistrationController\" method=\"POST\" accept-charset=\"utf-8\">");
			out.println("<input type=\"hidden\" name=\"formType\" value=\"redirectRegister\" />");
			out.println("<input type=\"SUBMIT\" value=\"register\"/>");
			out.println("</form>");
			session.setAttribute("initialLoginAttempts",config.getInitParameter("loginAttempts"));
			int loginAttempt = 0;
			session.setAttribute("loginAttempt", loginAttempt);
			System.out.println("LoginAttempt1: " + (Integer)session.getAttribute("loginAttempt"));
			System.out.println("InitialLoginAttempts: " + session.getAttribute("initialLoginAttempts"));
		}else if(Integer.parseInt((String)session.getAttribute("initialLoginAttempts"))< (Integer)session.getAttribute("loginAttempt")){
			out.println("<html>");
			out.println("<head>");
			out.println("<title> Login Error </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>You have exceeded the maximum number of login attempts</h1>");
			out.println("</body>");
			out.println("</html>");
		}else{
			//loginAttempt=(Integer)session.getAttribute("loginAtttempt");
			int loginAttempt2=(Integer)session.getAttribute("loginAttempt");
			session.removeAttribute("loginAttempt");
			loginAttempt2++;
			session.setAttribute("loginAttempt",loginAttempt2);
			System.out.println("LoginAttempt2: " + session.getAttribute("loginAttempt"));
			
			sib.setUserId(request.getParameter("uidInput"));
			//System.out.println(request.getParameter("uidInput"));
			//System.out.println(sib.getUserId());
			sib.setPassword(request.getParameter("passInput"));
			//System.out.println(request.getParameter("passInput"));
			//System.out.println(sib.getPassword());
			
			if(sib.getValidSyntax()){
				sib.findUser();
				if(sib.getUserFound()){
					session.setAttribute("storedFirstName", sib.getStoredFirstName());
					session.setAttribute("storedLastName", sib.getStoredLastName());
					RequestDispatcher dispatcher= request.getRequestDispatcher("/welcome2.jsp");
					dispatcher.forward(request,response);
				}else{
					System.out.println(sib.getStoredFirstName());
					System.out.println(sib.getStoredLastName());
					out.println("<html>");
					out.println("<head>");
					out.println("<title> Login Error </title>");
					out.println("</head>");
					out.println("<body>");
					out.println("<h1>User Login Failed, Please Register to use the Student Registration System</h1>");
					out.println("<h3>Login Number " + session.getAttribute("loginAttempt") + " attempts</h3>");
					out.println("</body>");
					out.println("</html>");
				}
			}else{	
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title> Login Error </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1> Password and Username Must be exactly 8 characters.  Please Try Again</h1>");
			out.println("<h3>Login Number " + session.getAttribute("loginAttempt") + " attempts</h3>");
			out.println("</body>");
			out.println("</html>");
			
			}
			
		}
		

//		}else{
//				out.println("<html>");
//				out.println("<head>");
//				out.println("<title> </title>");
//				out.println("</head>");
//				out.println("<body>");
//				out.println("<h1>");
//				out.println("</h1>");
//				out.println("</body>");
//				out.println("</html>");
			
//		}
	}catch(NullPointerException e){
		System.err.println(e+"Cause: "+ e.getCause()+ "Message: " + e.getMessage() + e.getStackTrace());
	}catch(NumberFormatException nfe){
		System.err.println(nfe);
	}
%>

	<!-- 
	
	<h3>For new users, please register first</h3>
	<input type="button" name="redirect" value="register" onclick="window.location = 'registrationA.html'" />
	-->
	 
	 
</body>
</html>