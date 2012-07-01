package edu.jhu.JavaEE.caldwell.kristin.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;

public class StudentInfoBean {
	private String userId;
	private String password;
	private String loginSyntaxInfo="";
	
	private String dataSource;
	private int loginAttempts;
	private String weblogicUrl;
	
	private boolean registrationSuccessful=false;
	
	private String loginName;
	private String loginPass;
	private String storedFirstName;
	private String storedLastName;
	
	private String uname;
	private String pass;
	private String ssn;
	private String ssn1;
	private String ssn2;
	private String ssn3;
	private String firstName;
	private String lastName;
	private String email;
	
	private String address;
	private String city;
	private String state;
	private String zip;
	
	private boolean validPass;
	private boolean validUname;
	private boolean userFound=false;
	
	public void setUserId(String userid){
		this.userId=userid;
	}
	
	public void setWeblogicUrl(String wlurl){
		weblogicUrl = wlurl;
	}
	
	public void setDataSource(String ds){
		dataSource = ds;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public String getPassword(){
		return password;
	}
	
	public boolean getValidPass(){
		return validPass;
	}
	
	public void validPassword(String pass){
		if((pass.indexOf(" ")==-1)&&pass.length()==8){
			validPass = true;
		}else{
			validPass = false;
		}
	}
	
	
	public boolean getValidUname(){
		return validUname;
	}
	
	public void validUserName(String userName){
		if((userName.indexOf(" ")==-1)&&userName.length()==8){
			validUname = true;
		}else{
			validUname = false;
		}
	}
	
	
	public boolean getValidSyntax(){
		validUserName(userId);
		validPassword(password);
		if(getValidUname()&&getValidPass()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String getStoredFirstName(){
		return storedFirstName;
	}
	
	public String getStoredLastName(){
		return storedLastName;
	}
	
	public String getSyntaxValidationString(){
		validUserName(userId);
		validPassword(password);
		if(getValidUname()){
			if(getValidPass()){
				loginSyntaxInfo = "Authenticated";
			}else{
				loginSyntaxInfo = "Your password cannot contain spaces and must be exactly 8 characters";
			}	
		}else{
			loginSyntaxInfo = "Your username cannot contain spaces and must be exactly 8 characters";
		}
		return loginSyntaxInfo;
	}
	
	public void findUser(){

		Context cxt = null;
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, weblogicUrl);
		try{
			cxt = new InitialContext(env);
			System.out.println(dataSource.toString());
			javax.sql.DataSource ds = (javax.sql.DataSource)cxt.lookup(dataSource.toString());
			System.out.println("i have made it past the datasource lookup");
			Connection conn = ds.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("select FIRST_NAME, LAST_NAME from STUDENT where USERID=? and PASSWORD=?");
			stmt.setString(1,userId);
			stmt.setString(2,password);
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
			//return userFound;
		//	return true;
		}
	}
	
	public boolean getUserFound(){
		return userFound;
	}
	

	public String executeLoginProcedure(){
		if(getValidSyntax()){
			if(getUserFound()){
				 String s = "Welcome to the site "+storedFirstName + storedLastName;
				 return s;
			}else{
				String s = "Please Register to use the Student Registration System";
				return s;
			}
		}else{
			return getSyntaxValidationString();
		}		
	}
	
	
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	/*
	public String getSsn() {
		return ssn;
	}
	*/
	
	public void concatSSN(){
		ssn = ssn1+ssn2+ssn3;
	}
	
	public void setSsn1(String ssn1) {
		this.ssn1 = ssn1;
	}
	
	public void setSsn2(String ssn2) {
		this.ssn2 = ssn2;
	}
	
	public void setSsn3(String ssn3) {
		this.ssn3 = ssn3;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public boolean validateRegistration(){
		return registrationSuccessful;
	}
	
	public void processRegistration(){
		try{ 
			Context cxt = null;
		    Hashtable env = new Hashtable();
		    env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		    env.put(Context.PROVIDER_URL, weblogicUrl.toString());
			cxt = new InitialContext(env);
			javax.sql.DataSource ds = (javax.sql.DataSource)cxt.lookup(dataSource.toString());
			Connection conn = ds.getConnection();
			

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO STUDENT VALUES(?, ?, ?, ?, ?, ?, ?)");
			System.out.println(this.firstName);
			pstmt.setString(1, this.firstName);
			pstmt.setString(2, this.lastName);
			pstmt.setString(3, this.ssn);
			pstmt.setString(4, this.email);
			pstmt.setString(5, this.address+" "+this.city+" "+this.state+" "+this.zip);
			pstmt.setString(6, this.uname);
			pstmt.setString(7, this.pass);
			
			
			pstmt.executeUpdate();
			
			
			
			conn.close();
			registrationSuccessful=true;
		}catch(SQLException sqle){
			System.err.println(sqle);
		}catch(NamingException ne){
			System.err.println(ne);
		}finally{
        	//lsBean.setFirstName("Welcome to the site, "+this.firstName);
        	//lsBean.setLastName(rb.getLastName());
            //request.setAttribute("loginSuccessful", lsBean);
			//session.setAttribute("storedFirstName", sib.getStoredFirstName());
			//session.setAttribute("storedLastName", sib.getStoredLastName());
			//RequestDispatcher dispatcher= request.getRequestDispatcher("/welcome2.jsp");
			//dispatcher.forward(request,response);
        	//RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/welcome2.jsp");
        	//dispatcher.forward(request, response);
		}
		
	}
	
	
}
