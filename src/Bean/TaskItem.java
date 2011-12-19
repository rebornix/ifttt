package Bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskItem implements Serializable
{
	public int taskID = 0;
	public int status = 0;
	
	///////this//////////
	public int thisID = 0;
	public int thisType = 0;
	
	///////this//////////
	public int thatID = 0;
	public int thatType = 0;
	
	//////sql////////
	public Statement psmt;
	public String queryString ;
	public ResultSet rset;
	public Connection connection ;
	
	public static void main(String []args) throws SQLException
	{
		TaskItem test1 = new TaskItem(3);
	}
	public TaskItem(int taskID) throws SQLException
	{
		this.taskID = taskID;
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
			
			queryString =	"select thisID,thatID,status "+
							"from TASK "+
							"where TASK.taskID ="+this.taskID;
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			
			if(rset.next())
			{
				this.thisID = rset.getInt(1);
				this.thatID = rset.getInt(2);
				this.status = rset.getInt(3);
				System.out.println("get thisID,thatID,status successful "+
				this.thisID+" "+this.thatID+" "+this.status);
			}
			else
			{
				System.out.println("not found");
			}
			
			queryString =	
					"select thistype "+
					"from THIS "+
					"where thisid ="+this.thisID;
	
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
	
			if(rset.next())
			{
				this.thisType = rset.getInt(1);

				System.out.println("get thisType successful:"+this.thisType);
			}
			else
			{
				System.out.println("not found");
			}
			
			queryString =	
					"select thattype "+
					"from THAT "+
					"where thatID ="+this.thatID;
	
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
	
			if(rset.next())
			{
				this.thatType = rset.getInt(1);

				System.out.println("get thatType successful : "+this.thatType);
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
	
	public int getTaskID()
	{
		return this.taskID;
	}
	public int getThisID()
	{
		return this.thisID;
	}
	public int getThatID()
	{
		return this.thatID;
	}
	public int getStatus()
	{
		return this.status;
	}

	public int getThisType()
	{
		return this.thisType;
	}
	public int getThatType()
	{
		return this.thatType;
	}




}