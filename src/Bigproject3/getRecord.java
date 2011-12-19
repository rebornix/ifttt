package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getRecord 
{
	///////sql/////////
	public Statement psmt;
	public String queryString ;
	public ResultSet rset;
	public Connection connection ;
	
	///////record////////
	public int recordID;
	public String username;
	public String time;
	public int taskID = -1;
	public int consumption;
	
	public getRecord(int recordID) throws SQLException
	{
		this.recordID = recordID;
		initializeDB();
		connection.close();
	}
	public void initializeDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			
			connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
			
			System.out.println("Database connected");
			
			queryString =	"select ACCOUNTID ,TIME ,CONSUMPTION,TASKID   "+
							"from RECORD "+
							"where recordID ="+this.recordID;
			
			psmt = connection.prepareStatement(queryString);
			rset = psmt.executeQuery(queryString);
			System.out.println("get task success");
			if(rset.next())
			{
				this.username = rset.getString(1);
				this.time = rset.getString(2);
				this.consumption = rset.getInt(3);
				this.taskID = rset.getInt(4);
				System.out.println("get record success.");
			}
			else
			{
				System.out.println("not found");
			}
			
			
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String toString()
	{
		return this.recordID+":"+this.username+","+this.time+","+this.consumption+","+this.taskID;
	}
}