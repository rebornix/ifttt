package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class getTaskIDs 
{
	
	//////task imformation////
	private String userName;
	private int numOfTasks = 0;
	private int taskIDs[];
	
	
	//////sql information////
	public Statement psmt;
	public String queryString ;
	public ResultSet rset;
	public Connection connection ;
	
	@SuppressWarnings("unused")
	public static void main(String []args)
	{
		getTaskIDs test1 = new getTaskIDs("greatshang");
		getTaskIDs test2 = new getTaskIDs("kidreborn");
	}
	
	public getTaskIDs(String userName)
	{
		this.userName = userName;
		initializeDB();
		saveTaskIDs();
		System.out.println(this.toString());
	}
	

	public void initializeDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			
			connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
			
			System.out.println("Database connected");
			
			queryString =	"select count(accountid) "+
							"from EXECUTION "+
							"where accountid = '"+this.userName+"'";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get num success");
			
			if(rset.next())
			{
				this.numOfTasks = Integer.parseInt(rset.getString(1));
				this.taskIDs = new int[numOfTasks];
			}
			System.out.println(this.numOfTasks);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveTaskIDs()
	{
		try
		{	
			queryString =	"select taskid "+
							"from EXECUTION "+
							"where accountid = '"+this.userName+"'";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get IDs success");
			

			if(rset.next())
			{	
				int j = 0;
				taskIDs[j]=Integer.parseInt((rset.getString(1)));
				while(rset.next())
				{
					j++;
					taskIDs[j]=Integer.parseInt((rset.getString(1)));
				}
			}
			else
			{
				System.out.println("Not found");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getUserName()
	{
		return this.userName;
	}
	public int getNumOfTasks()
	{
		return this.numOfTasks;
	}
	public int[] getAllTasks()
	{
		return taskIDs;
	}
	public int getTheTask(int i )
	{
		if(i<this.numOfTasks)
			return taskIDs[i];
		else
			return -1;
	}
	public String toString()
	{
		String output = this.userName+ " ";
		for(int i = 0;i<this.numOfTasks;i++)
		{
			output += " "+taskIDs[i];
		}
		return output;
	}
}