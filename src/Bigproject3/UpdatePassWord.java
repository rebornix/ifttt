package Bigproject3;

import java.sql.*;

public class UpdatePassWord 
{
	private String userName;
	private String newPassword;
	
	///////sql//////
	private Statement psmt;
	private String queryString ;
	//private ResultSet rset;
	private Connection connection ;
	
	
	public static void main(String []args) throws ClassNotFoundException, SQLException
	{
		UpdatePassWord test = new UpdatePassWord("whoami","ddd");
	}
	public UpdatePassWord(String userName,String newPassword) throws ClassNotFoundException, SQLException
	{
		this.userName = userName;
		this.newPassword = newPassword;
		initializeDB();
	}
	public void initializeDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
		
		System.out.println("Database connected");
		
		queryString =	
				"update ACCOUNT "+
				"SET PASSWORD = '"+this.newPassword+"' "+
				"where accountid = '"+this.userName+"'";
		
		
		psmt = connection.createStatement();
		psmt.executeUpdate(queryString);

		System.out.println(this.userName+"updaste newpassword success: "+this.newPassword);
	}
}