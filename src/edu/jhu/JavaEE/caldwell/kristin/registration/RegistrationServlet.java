package edu.jhu.JavaEE.caldwell.kristin.registration;

import edu.jhu.JavaEE.caldwell.kristin.login.*;

import java.sql.*;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

public class RegistrationServlet extends HttpServlet{
	
	private String dataSource;
	private String loginAttempts;
	private String weblogicUrl;
	
	private LoginSuccessful lsBean = new LoginSuccessful();
	private RegistrationBean rb = new RegistrationBean();
	

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		
		response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        ServletConfig servletConfig = getServletConfig();
		
		System.out.println("i have made it to RegistrationServlet");
		
        dataSource=session.getAttribute("jhuDataSource").toString(); 
        loginAttempts=session.getAttribute("loginAttempts").toString();
        System.out.println(session.getAttribute("loginAttempts").toString());
        weblogicUrl = session.getAttribute("weblogicURL").toString();
        
		
		if(request.getParameter("formType").equals("registrationA")){
			session.setAttribute("uidInput", request.getParameter("uidInput"));
			session.setAttribute("passInput", request.getParameter("passInput"));
			session.setAttribute("passInputR", request.getParameter("passInputR"));
			session.setAttribute("fName", request.getParameter("fName"));
			session.setAttribute("lName", request.getParameter("lName"));
			session.setAttribute("phone", request.getParameter("phone"));
			session.setAttribute("ssn1", request.getParameter("ssn1"));
			session.setAttribute("ssn2", request.getParameter("ssn2"));
			session.setAttribute("ssn3", request.getParameter("ssn3"));
			session.setAttribute("email", request.getParameter("email"));
			
			rb.setUname((String)request.getParameter("uidInput"));
			rb.setEmail((String)request.getParameter("email"));
			rb.setFirstName((String)request.getParameter("fName"));
			rb.setLastName((String)request.getParameter("lName"));
			rb.setPass((String)request.getParameter("passInput"));
			rb.setSsn((String)request.getParameter("Ssn1")+(String)request.getParameter("Ssn2")+(String)request.getParameter("Ssn3"));
			System.out.println("ssn1");
			System.out.println(rb.getSsn());
			
        	request.setAttribute("regA", rb);

        	
			
			
	       	RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/registrationB.html");
        	dispatcher.forward(request, response);
			
		}else{
		//	System.out.println(session.getAttribute("fName"));
			session.setAttribute("address", request.getParameter("address"));
			session.setAttribute("city", request.getParameter("city"));
			session.setAttribute("state", request.getParameter("state"));
			session.setAttribute("zip", request.getParameter("zip"));
			

			try{ 
				Context cxt = null;
			    Hashtable env = new Hashtable();
			    env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			    env.put(Context.PROVIDER_URL, weblogicUrl.toString());
				cxt = new InitialContext(env);
				javax.sql.DataSource ds = (javax.sql.DataSource)cxt.lookup(dataSource.toString());
				Connection conn = ds.getConnection();
				

				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO STUDENT VALUES(?, ?, ?, ?, ?, ?, ?)");
				System.out.println(rb.getFirstName());
				pstmt.setString(1, rb.getFirstName());
				pstmt.setString(2, rb.getLastName());
				pstmt.setString(3, rb.getSsn());
				pstmt.setString(4, rb.getEmail());
				pstmt.setString(5, (String)request.getParameter("address")+" "+(String)request.getParameter("city")+" "+(String)request.getParameter("state")+" "+(String)request.getParameter("zip"));
				pstmt.setString(6, rb.getUname());
				pstmt.setString(7, rb.getPass());
				
				
				pstmt.executeUpdate();
				
				
				
				conn.close();
			}catch(SQLException sqle){
				System.err.println(sqle);
			}catch(NamingException ne){
				System.err.println(ne);
			}finally{
				System.out.println(rb.getFirstName());
	        	lsBean.setFirstName("Welcome to the site, "+rb.getFirstName());
	        	lsBean.setLastName(rb.getLastName());
	            request.setAttribute("loginSuccessful", lsBean);
	        	RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/welcome2.jsp");
	        	dispatcher.forward(request, response);
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		processRequest(request,response);
		
	}

}
