package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertExecution 
{
	private Statement psmt;
	private String queryString ;
	//private ResultSet rset;
	private Connection connection ;
	
	//////execution//////
	private String ID;
	private int  taskID;	
	private int cost;
	private int status = 0;
	
	public void initializeDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
		
		System.out.println("Database connected");
	}
	public InsertExecution(String ID,int taskID,int cost,int status)
	{
		this.ID  = ID;
		this.taskID = taskID;
		this.cost = cost;
		this.status = status;

		try
		{
			initializeDB();
			queryString =	"insert "+
							"into EXECUTION(accountid,taskid,cost,status) "+
							"values ('"+this.ID+"',"+this.taskID+","+this.cost+","+this.status+")";
			
			
			psmt = connection.createStatement();
			psmt.executeUpdate(queryString);
			System.out.println("insert success");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String []args)
	{
		InsertExecution test1 = new InsertExecution("greatshang",2,10,0);
		InsertExecution test2 = new InsertExecution("greatshang",3,10,0);
	}
}
