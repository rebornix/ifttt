package Bigproject3;

import java.sql.*;

public class getAccountIDs 
{
	private int numOfAccounts = 0;
	private String accountIDs[];
	
	
	//////sql information////
	public Statement psmt;
	public String queryString ;
	public ResultSet rset;
	public Connection connection ;
	
	
	public static void main(String[]args)
	{
		getAccountIDs test = new getAccountIDs();
		System.out.println(test.toString());
	}
	
	public getAccountIDs()
	{
		initializeDB();
		saveAccountIDs();
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
							"from ACCOUNT ";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get num success");
			
			if(rset.next())
			{
				this.numOfAccounts = rset.getInt(1);
				this.accountIDs = new String[numOfAccounts];
			}
			System.out.println(this.numOfAccounts);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveAccountIDs()
	{
		try
		{	
			queryString =	"select accountid "+
							"from ACCOUNT ";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get IDs success");
			

			if(rset.next())
			{	
				int j = 0;
				accountIDs[j]=rset.getString(1);
				while(rset.next())
				{
					j++;
					accountIDs[j]=rset.getString(1);
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
	
	public int getNumOfAccouts()
	{
		return this.numOfAccounts;
	}
	public String[] getAllAccounts()
	{
		return this.accountIDs;
	}
	public String getTheAccount(int i )
	{
		if(i<this.numOfAccounts)
			return accountIDs[i];
		else
			return "bitch stop it";
	}
	
	public String toString()
	{
		String output =  "";
		for(int i = 0;i<this.numOfAccounts;i++)
		{
			output += " "+accountIDs[i];
		}
		return output;
	}
}