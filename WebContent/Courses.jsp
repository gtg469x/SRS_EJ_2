<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="java.util.*,java.sql.*,javax.sql.*,org.apache.derby.jdbc.*,javax.naming.*,java.text.*" %>
<% List<String> courses; %>
<% String dataSource=(String)session.getAttribute("jhuDataSource");  
	System.out.println(dataSource);
%>
<% String weblogicUrl=(String)session.getAttribute("weblogicURL"); 
	System.out.println(weblogicUrl);
%>
<title>CourseListing</title>
</head>
<body bgcolor="#ADD8E6">
	
	<form action="RegistrarCourse" method="POST" accept-charset="utf-8">
	<input type="hidden" name="dataSource" value=<%=dataSource %> />
	<input type="hidden" name="weblogicURL" value=<%=weblogicUrl %> />
	<% courses = new ArrayList<String>();
		Context cxt = null;
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, weblogicUrl.toString());

		try{
			cxt = new InitialContext(env);
			javax.sql.DataSource ds = (javax.sql.DataSource)cxt.lookup(dataSource.toString());
	        Connection conn = ds.getConnection();
	        Statement getCourses = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs = getCourses.executeQuery("select * from COURSES");
	        %><select name="courseList"> 
	        <%
	        while (rs.next()){
	        	courses.add(Integer.toString(rs.getInt(1))+" "+rs.getString(2));
	        	//s = Integer.toString(rs.getInt(1))+" "+rs.getString(2);
	        %>
	        <option name="courseListing" value=<%=Integer.toString(rs.getInt(1))+" "+rs.getString(2) %>><%= Integer.toString(rs.getInt(1))+" "+rs.getString(2) %></option>
	        <%	
	        }
	         %>
	        </select>
			<%	conn.close();				
			}catch(SQLException sqle){
					System.err.println(sqle);
			}	
		for(int i = 0; i<courses.size(); i++){
			System.out.println(courses.get(i).toString());
		}
	 %>
	 	<td><input type="hidden" name="formType" value="courses" />
		<td><input type="SUBMIT" value="submit"></td>
		
		<!--  <input type="button" name="redirect" value="register" onclick="window.location = 'RegistrarCourse.jsp'" />-->
	 </form>

</body>
</html>