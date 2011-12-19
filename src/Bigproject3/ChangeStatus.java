package Bigproject3;

import java.sql.*;

public class ChangeStatus 
{
	private int status = 0;
	private int taskID = 0;
	
	///////sql///////
	private Statement psmt;
	private String queryString ;
	private ResultSet rset;
	private Connection connection ;
	
	public static void main()
	{
		
	}
	public ChangeStatus(int taskID) throws ClassNotFoundException, SQLException
	{
		this.taskID = taskID;
		initializeDB();
		setStatus();
		if(this.status == 0)
		{
			setDBStatus0();
			if(ThreadCreate.thread[this.taskID] == null)
			{
				CreateTask test = new CreateTask(this.taskID);
				test.start();
			}
			else{
				if(!ThreadCreate.thread[this.taskID].isAlive())
					ThreadCreate.thread[this.taskID].start();
			}
		}
		else if(this.status == 1)
		{
			setDBStatus1();
			if(ThreadCreate.thread[this.taskID] != null)
			{
				ThreadCreate.thread[this.taskID].interrupt();
			}
		}
	}
	
	public void initializeDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
		
		System.out.println("Database connected");
	}
	public void setStatus() throws SQLException
	{
		queryString =	
				"select status "+
				"from TASK "+
				"where taskid = "+this.taskID;

		psmt = connection.createStatement();		
		rset = psmt.executeQuery(queryString);
		System.out.println("get status success");
		if(rset.next())
		{
			this.status =rset.getInt(1);
		}
	}
	public void setDBStatus0() throws SQLException
	{
		queryString =	
				"update TASK "+
				"set status = 1 "+
				"where taskid = "+this.taskID;


		psmt = connection.createStatement();
		psmt.executeUpdate(queryString);
		System.out.println("set status = 1 success");
	}
	public void setDBStatus1() throws SQLException
	{
		queryString =	
				"update TASK "+
				"set status = 0 "+
				"where taskid = "+this.taskID;


		psmt = connection.createStatement();
		psmt.executeUpdate(queryString);
		System.out.println("set status = 0 success");
	}
}
