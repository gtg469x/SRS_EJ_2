<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="java.util.*,java.sql.*,javax.sql.*,org.apache.derby.jdbc.*,javax.naming.*,java.text.*" %>
<% String dataSource=request.getParameter("dataSource");
	System.out.println("datasource is: "+ (String)dataSource);  %>
<% String weblogicUrl=request.getParameter("weblogicURL");
   System.out.println("datasource is: "+ (String)dataSource); %>
<% int numStudents = 0; %>
<% String results="success"; %>
<%String courseListinValue = ""; %>

<title>RegistrarCourse</title>
</head>
<body>
	<% 	
		System.out.println(config.getInitParameter("CourseCapacity"));
		int courseCapacity = Integer.parseInt(config.getInitParameter("CourseCapacity")); 
		courseListinValue=request.getParameter("courseList");
		System.out.println(courseListinValue);
		%>
		<input type="hidden" name="courseListingVal" value=<%=request.getParameter("courseList") %> />
		<%
		//session.setAttribute("courseListing", request.getParameter("courseListing"));
		String[] courseListArray = courseListinValue.split(" ");
		String courseID = courseListArray[0];
		int courseIDlookup = Integer.parseInt(courseID);
		
		Context cxt = null;
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, weblogicUrl.toString());

		try{
			cxt = new InitialContext(env);
			javax.sql.DataSource ds = (javax.sql.DataSource)cxt.lookup(dataSource.toString());
	        Connection conn = ds.getConnection();
	        Statement getCourses = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        PreparedStatement pstmt = conn.prepareStatement("Select NUMBER_STUDENTS_REGISTERED from REGISTRAR where COURSEID=?");
	        pstmt.setInt(1,courseIDlookup);
	        System.out.println("pstmt: " + pstmt.toString());
	        
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        while(rs.next()){
	        	numStudents = rs.getInt(1);
	        	System.out.println(numStudents);
	        }
	        
	        if(numStudents<=courseCapacity){
	        	numStudents++;
	        	PreparedStatement pstmt2 = conn.prepareStatement("Update REGISTRAR set NUMBER_STUDENTS_REGISTERED=? where COURSEID=?");
	        	pstmt2.setInt(1,numStudents);
	        	pstmt2.setInt(2,courseIDlookup);
	        	
	        	System.out.println("pstmt: " + pstmt.toString());
	        	
	        	pstmt2.executeUpdate();
	        	
	        	results = "success";
	        %>
	        	<jsp:forward page="/RegistrarSuccess">
	        		<jsp:param value="<%=courseListinValue%>" name="courseListingVal2"/>
	        	</jsp:forward>
	        <%	        
	        }else{
	        
	        	results="Sorry, the registration to thise course has been closed based on availability";
	        %>	<jsp:forward page="/RegistrarFail">
	        		<jsp:param value= "<%=results %>" name="regFail"/>
	        	</jsp:forward>
	        		
	        <%	
	        }
		%> <input type="hidden" name="registarResults" value=<%= results %>/>
		<%
		}catch(SQLException e){
		System.err.println(e);
		}
 		%>


</body>
</html>