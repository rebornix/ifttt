package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class getRecordIDs 
{
	private String userName;
	private int numOfRecords = 0;
	private int recordIDs[];
	
	//////sql information////
	public Statement psmt;
	public String queryString ;
	public ResultSet rset;
	public Connection connection ;
	
	
	@SuppressWarnings("unused")
	public static void main(String []args)
	{
		getRecordIDs test = new getRecordIDs("greatshang");
	}
	public getRecordIDs(String userName)
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
			
			queryString =	"select count(recordid) "+
							"from RECORD "+
							"where accountid = '"+this.userName+"'";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get num success");
			
			if(rset.next())
			{
				this.numOfRecords = Integer.parseInt(rset.getString(1));
				this.recordIDs = new int[this.numOfRecords];
			}
			System.out.println(this.numOfRecords);
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
			queryString =	"select recordID "+
							"from RECORD "+
							"where accountid = '"+this.userName+"'";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get IDs success");
			

			if(rset.next())
			{	
				int j = 0;
				recordIDs[j]=Integer.parseInt((rset.getString(1)));
				while(rset.next())
				{
					j++;
					recordIDs[j]=Integer.parseInt((rset.getString(1)));
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
	
	public int getNumOfRecords()
	{
		return this.numOfRecords;
	}
	public int getTheRecord(int i )
	{
		if(i<this.numOfRecords)
			return recordIDs[i];
		else
			return -1;
	}
	public String toString()
	{
		String output = this.userName+ " ";
		for(int i = 0;i<this.numOfRecords;i++)
		{
			output += " "+recordIDs[i];
		}
		return output;
	}
}
