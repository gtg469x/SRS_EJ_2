package edu.jhu.JavaEE.caldwell.kristin.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class StudentInfoBean {
	private String userId;
	private String password;
	private String loginSyntaxInfo="";
	
	private String dataSource;
	private int loginAttempts;
	private String weblogicUrl;
	
	private String loginName;
	private String loginPass;
	private String storedFirstName;
	private String storedLastName;
	
	private String uname;
	private String pass;
	private String ssn;
	private String firstName;
	private String lastName;
	private String email;
	
	private boolean validPass;
	private boolean validUname;
	private boolean userFound=false;
	
	public void setUserId(String userid){
		this.userId=userid;
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
	
	protected void findUser(){

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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
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
	
	
}
