package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class deleteTask 
{
	private int taskID = 0;
	private int thisID = 0;
	private int thatID = 0;
	
	private String userName = null;
	
	private Statement psmt;
	private String queryString ;
	private ResultSet rset;
	private Connection connection ;
	
	
	public static void main(String []args)
	{
		deleteTask test1 = new deleteTask(2);
	}
	
	public void initializeDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
		
		System.out.println("Database connected");
	}
	
	public deleteTask(int taskID)
	{
		this.taskID  = taskID;

		try
		{
			initializeDB();
			queryString =	"select thisID,thatID "+
							"from TASK "+
							"where taskID = "+this.taskID;
			
			
			psmt = connection.createStatement();
			rset = psmt.executeQuery(queryString);
			System.out.println("get task success");
			
			if(rset.next())
			{
				this.thisID = Integer.parseInt(rset.getString(1));
				this.thatID = Integer.parseInt(rset.getString(2));
				System.out.println(thisID +" "+thatID+" ");
			}
			else
			{
				System.out.println("not found");
			}
			
			queryString =	
					"select accountid "+
					"from EXECUTION "+
					"where taskID = "+this.taskID;
	
	
			psmt = connection.createStatement();
			rset = psmt.executeQuery(queryString);
			System.out.println("get task success");
	
			if(rset.next())
			{
				this.userName = rset.getString(1);
				System.out.println(userName);
			}
			else
			{
				System.out.println("not found");
			}
			
			queryString =	
					"delete  "+
					"from TASK "+
					"where taskID = "+this.taskID;
	
	
			psmt = connection.createStatement();
			psmt.executeUpdate(queryString);
			
			System.out.println(this.taskID+"deleted from task.");
			
			deleteThis();
			deleteThat();
			
			queryString =	
					"delete  "+
					"from EXECUTION "+
					"where taskID = "+this.taskID;
	
	
			psmt = connection.createStatement();
			psmt.executeUpdate(queryString);
			
			System.out.println(this.taskID+" (taskID) deleted from execution.");
			
			connection.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void deleteThis() throws SQLException
	{
		queryString =	
				"delete  "+
				"from THIS "+
				"where thisID = "+this.thisID;
		
		psmt = connection.createStatement();
		psmt.executeUpdate(queryString);
		System.out.println(this.thisID+"deleted from this.");
		
	}
	public void deleteThat() throws SQLException
	{
		queryString =	
				"delete  "+
				"from THAT "+
				"where thatID = "+this.thatID;

		psmt = connection.createStatement();
		psmt.executeUpdate(queryString);
		System.out.println(this.thatID+"deleted from that.");
		
	}

	public String getUserName()
	{
		return this.userName;
	}
	public int getThisID()
	{
		return this.thisID;
	}
	public int getThatID()
	{
		return this.thatID;
	}
}
