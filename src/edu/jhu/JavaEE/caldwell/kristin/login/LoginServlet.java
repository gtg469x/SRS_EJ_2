package edu.jhu.JavaEE.caldwell.kristin.login;

import java.io.*;
import java.sql.*;
import javax.sql.*;
import org.apache.derby.jdbc.ClientDriver;
import java.util.*;
import javax.naming.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet{
	
//	private int loginAttemptLimit=4;
	private String loginName;
	private String loginPass;
	private String storedFirstName;
	private String storedLastName;
	
	private LoginSuccessful lsBean = new LoginSuccessful();
	private LoginFail lfBean = new LoginFail();
	
	private String dataSource;
	private int loginAttempts;
	private String weblogicUrl;
	
//	private LoginSyntaxVerifier lSv = new LoginSyntaxVerifier();
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        ServletConfig servletConfig = getServletConfig();

        dataSource=session.getAttribute("jhuDataSource").toString(); 
        loginAttempts=Integer.parseInt(session.getAttribute("loginAttempts").toString());
 //       System.out.println(session.getAttribute("loginAttempts").toString());
        weblogicUrl = session.getAttribute("weblogicURL").toString();
        
        //I'm not sure why I'm doing this: check later
        //lSv = (LoginSyntaxVerifier) session.getAttribute("loginSyntaxVerifier");
        
        loginName = request.getParameter("uidInput");
        System.out.println(loginName);
        loginPass = request.getParameter("passInput");
        System.out.println(loginPass);
        
        if(!(validUserName(loginName)&&validPassword(loginPass))){
        /*
            lSv.setValidUserName(validUserName(loginName));
        	lSv.setValidPass(validPassword(loginPass));
        	session.setAttribute("loginSyntaxVerifier", lSv);
        	RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/login.html");
        	dispatcher.forward(request, response);
        */
        	lfBean.setLoginFailMessage("Username and Password must be 8 chars in length with no spaces. Try logging in Again");
        	request.setAttribute("loginFail", lfBean);
        	RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/loginFail.jsp");
        	dispatcher.forward(request, response);
        	
        }else{
	        if(findUser()){
	        //if(kristinAdmin()){
	        	//lsBean.setFirstName("Welcome to the site, "+"kristin");
	        	//lsBean.setLastName("caldwell");
        		lsBean.setFirstName("Welcome to the site, "+storedFirstName);
	        	lsBean.setLastName(storedLastName);
	            request.setAttribute("loginSuccessful", lsBean);
	            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/welcome2.jsp");
	            dispatcher.forward(request, response);
	        }else{
	        	String logfail = "username and password combination invalid, please register";
	        	lfBean.setLoginFailMessage(logfail);
	        	request.setAttribute("loginFail", lfBean);
	        	RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/loginFail.jsp");
	        	dispatcher.forward(request, response);
	        }
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		processRequest(request,response);
	}
	
	protected boolean findUser(){
		boolean userFound=false;
		Context cxt = null;
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, weblogicUrl);
		try{
			cxt = new InitialContext(env);
			javax.sql.DataSource ds = (javax.sql.DataSource)cxt.lookup(dataSource.toString());
			System.out.println("i have made it past the datasource lookup");
			Connection conn = ds.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("select FIRST_NAME, LAST_NAME from STUDENT where USERID=? and PASSWORD=?");
			stmt.setString(1,loginName);
			stmt.setString(2,loginPass);
			/*	Statement stmt = conn.createStatement();
			String selectStmt = "select FIRST_NAME, LAST_NAME from STUDENT where USERID="+loginName+" and PASSWORD="+loginPass;
			System.out.println(selectStmt);
			*/
			ResultSet rs = stmt.executeQuery();
			
			
			if(rs.next()){
				userFound = true;
				storedFirstName = rs.getString(1);
				System.out.println(storedFirstName);
				storedLastName = rs.getString(2);
				System.out.println(storedLastName);
			}else{
				userFound = false;
			}
			conn.close();
			//return userFound;
		}catch(SQLException sqle){
			System.err.println(sqle);
		}catch(NamingException ne){
			System.err.println(ne);
		}finally{
			return userFound;
		//	return true;
		}
	}
	
	protected boolean kristinAdmin(){
		return true;
	}
	
	
	
	protected boolean validPassword(String pass){
		if((pass.indexOf(" ")==-1)&&pass.length()==8){
			return true;
		}else{
			return false;
		}
	}
	
	
	protected boolean validUserName(String userName){
		if((userName.indexOf(" ")==-1)&&userName.length()==8){
			return true;
		}else{
			return false;
		}
	}
}