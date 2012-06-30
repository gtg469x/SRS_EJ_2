package edu.jhu.JavaEE.caldwell.kristin.courses;

import java.io.*;
import java.sql.*;

import javax.sql.*;
import org.apache.derby.jdbc.ClientDriver;
import org.apache.derby.jdbc.*;
import java.util.*;

import javax.naming.*;

import java.text.*;


import javax.servlet.*;
import javax.servlet.http.*;

public class CoursesServlet extends HttpServlet{
	
	private String dataSource;
	private String loginAttempts;
	private String weblogicUrl;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        ServletConfig servletConfig = getServletConfig();
		
        dataSource=session.getAttribute("jhuDataSource").toString(); 
        loginAttempts=session.getAttribute("loginAttempts").toString();
 //       System.out.println(session.getAttribute("loginAttempts").toString());
        weblogicUrl = session.getAttribute("weblogicURL").toString();
        
		getCourses();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RegistrarCourse.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
	
	public void getCourses(){
		
		LinkedList<String> courses = new LinkedList();
		
		
		Context cxt = null;
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, weblogicUrl);

		try{
	        Connection conn = DriverManager.getConnection(dataSource);

	         
	        Statement getCourses = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs = getCourses.executeQuery("select * from COURSES");
	        int lliterator = 0;
	        while (rs.next()){
	        	courses.add(lliterator, Integer.toString(rs.getInt("COURSEID"))+" "+Integer.toString(rs.getInt("COURSE_NAME")));
	        	System.out.println(courses.get(lliterator));
	        	lliterator++;	
	        	}
	        		
				conn.close();
				
				
				
			}catch(SQLException sqle){
					System.err.println(sqle);
			}
	}

}
