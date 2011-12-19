package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;

public class getNewTaskID 
{
	//////sql information////
	public Statement psmt;
	public String queryString ;
	public ResultSet rset;
	public Connection connection ;
	
	//////
	private int numOfTasks = 0;
	private int taskIDs[];
	private int newID= 0;
	
	
	public static void main(String []args)
	{
		getNewTaskID test1 = new getNewTaskID();
		System.out.println(test1.getNew());
		
	}
	
	public getNewTaskID()
	{
		initializeDB();
		saveTaskIDs();
		setNewID();
		
	}
	public void initializeDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			
			connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
			
			System.out.println("Database connected");
			
			queryString =	"select count(taskid) "+
							"from TASK ";
			
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
			queryString =	
					"select taskid "+
					"from TASK ";			
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
	
	public void setNewID()
	{
		boolean temp = false;
		this.newID = 1;
		for(int i = 1;i<this.numOfTasks+10;i++)
		{
			temp = false;
			for(int j = 0;j<numOfTasks;j++)
			{
				if(i==this.taskIDs[j])
					temp = true;
			}
			if(temp == false)
			{
				this.newID = i;
				break;
			}
		}
		
	}
	
	public int getNew()
	{
		return this.newID;
	}
	
}
	
	

