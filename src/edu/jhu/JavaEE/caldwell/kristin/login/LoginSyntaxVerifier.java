package edu.jhu.JavaEE.caldwell.kristin.login;




public class LoginSyntaxVerifier {
	
	private boolean validU;
	private boolean validP;
	
	

	
	/*protected String getUidErr(){
		return "Username must be exactly 8 chars with no spaces";
	}
	
	protected void setUidErr(){
		
	}*/
	/*
	protected String getPassErr(){
			return "Password must be exactly 8 chars with no spaces";
	}*/
	
	public boolean getValidPassword(){
		return validP;
	}
	
	public boolean getValidUsername(){
		return validU;
	}
	
	public void setValidUserName(boolean bool){
		validU = bool;
	}
	
	public void setValidPass(boolean bool){
		validP = bool;
	}
	
}
