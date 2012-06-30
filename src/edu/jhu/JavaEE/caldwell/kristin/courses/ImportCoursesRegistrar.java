package edu.jhu.JavaEE.caldwell.kristin.courses;


import java.io.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import javax.naming.*;
import java.text.*;
import org.apache.derby.jdbc.ClientDriver;

public class ImportCoursesRegistrar {
	
	private String insertValues;
	private StringTokenizer st;
	private String insertQuery;
	
	private int courseIDCourses;
	private String courseNameCourses;
	
	private int couseIDReigstrar;
	private int numStudentsRegistrar;
	
	public void insertCourses(){
		String dbPath = "jdbc:derby:/Users/kristincaldwell/Derby/JHU";

		
		String path = "/Users/kristincaldwell/Documents/JHU-MSCS/605.784_EnterpriseJava/Mod04/";
		try{
	        Connection conn = DriverManager.getConnection(dbPath);
			BufferedReader in = new BufferedReader(new FileReader(path+"courses.txt"));
			while ((insertValues=in.readLine())!=null){
				//System.out.println(insertValues);
				st = new StringTokenizer(insertValues, "|");
				while (st.hasMoreTokens()){
					
					
					courseIDCourses = Integer.parseInt(st.nextToken());
					courseNameCourses = st.nextToken();

					

					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO COURSES VALUES(?, ?)");
					pstmt.setInt(1, courseIDCourses);
					pstmt.setString(2, courseNameCourses);

					
					pstmt.executeUpdate();
				}
			}
					
					conn.close();
			}catch(IOException ioe){
					System.err.println(ioe);
			}
			catch(SQLException sqle){
					System.err.println(sqle);
			}
	}
	
	
	public void insertRegistrar(){
		String dbPath = "jdbc:derby:/Users/kristincaldwell/Derby/JHU";

		
		String path = "/Users/kristincaldwell/Documents/JHU-MSCS/605.784_EnterpriseJava/Mod04/";
		try{
	        Connection conn = DriverManager.getConnection(dbPath);
			BufferedReader in = new BufferedReader(new FileReader(path+"registrar.txt"));
			while ((insertValues=in.readLine())!=null){
				//System.out.println(insertValues);
				st = new StringTokenizer(insertValues, "|");
				while (st.hasMoreTokens()){
					
					
					couseIDReigstrar = Integer.parseInt(st.nextToken());
					numStudentsRegistrar = Integer.parseInt(st.nextToken());

					

					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO REGISTRAR VALUES(?, ?)");
					pstmt.setInt(1, couseIDReigstrar);
					pstmt.setInt(2, numStudentsRegistrar);

					
					pstmt.executeUpdate();
				}
			}
					
					conn.close();
			}catch(IOException ioe){
					System.err.println(ioe);
			}
			catch(SQLException sqle){
					System.err.println(sqle);
			}
	}


}

					