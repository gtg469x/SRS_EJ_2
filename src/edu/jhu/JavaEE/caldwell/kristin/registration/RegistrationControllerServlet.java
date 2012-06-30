package edu.jhu.JavaEE.caldwell.kristin.registration;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationControllerServlet extends HttpServlet{
	
	private int loginAttempt;
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        ServletContext servletContext = getServletContext();
        ServletConfig servletConfig = getServletConfig();
        
    	
		Enumeration e = servletConfig.getInitParameterNames();
	//	while(e.hasMoreElements()){
	//		System.out.println(e.nextElement());
	//	}

       	session.setAttribute("loginAttempts", servletConfig.getInitParameter("loginAttempts"));
  //     	session.setAttribute("loginAttempts", servletConfig.getInitParameter("loginAttempts"));
       	session.setAttribute("jhuDataSource", servletConfig.getInitParameter("jhuDataSource"));
       	/*       	while(servletConfig.getInitParameterNames().hasMoreElements()){
       		System.out.println(servletConfig.getInitParameterNames().nextElement());
       	}*/
       	session.setAttribute("weblogicURL", servletConfig.getInitParameter("weblogicURL"));
        
        if(request.getParameter("formType").equals("login")){
        	loginAttempt++;
        	//session.setAttribute("initialLoginAttempts", Integer.parseInt(servletConfig.getInitParameter("loginAttempts")));
        	//session.setAttribute("loginAttempt", loginAttempt);
        	RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/JSPLogin.jsp");
            dispatcher.forward(request, response);

        	/*
     //   	session.setAttribute("loginAttempts", servletContext.getInitParameter("servletContext"));
      //  	session.setAttribute("jhuDataSource", servletContext.getInitParameter("jhuDataSource"));
        	while(servletContext.getInitParameterNames().hasMoreElements()){
        		System.out.println(servletContext.getInitParameterNames().nextElement());
        	}
        	session.setAttribute("weblogicURL", servletContext.getInitParameter("weblogicURL"));
        	*/
        }else if(request.getParameter("formType").equals("welcome2")){
        	if(request.getParameter("nextSteps").toString().equals("register")){
        
        	RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/Courses.jsp");
        	dispatcher.forward(request, response);
        	
        	}else{
        	//	session.invalidate();
        	}	
        	
        	
        }//else if(request.getParameter("formType").equals("courses")){
        //	RequestDispatcher dispatcher = request.getRequestDispatcher("/Courses.jsp");
        //	dispatcher.forward(request, response);
        //}
        else{
        		
               	
               	RequestDispatcher dispatcher = request.getRequestDispatcher("RegistrationServlet");
               	System.out.println(session.getAttribute("fName"));
               //	dispatcher.forward(session, response);
               //	String requestParameter = (String)request.getParameter("fName");
               //	String requestFname = (String)request.getAttribute("fName");
               	
            	dispatcher.forward(request, response);
        }
        
        
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		processRequest(request,response);
		
	}
	

	
	
	
}