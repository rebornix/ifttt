package Bigproject3;

import java.sql.*;

public class getAccount 
{
	private String ID;
	private String PassWord;
	private int level;
	private int money;
	private int taskAmount;
	private int recordAmount;
	private getRecordIDs recordIDs ;
	
	
	public int getTaskAmount()
	{
		return this.taskAmount;
	}
	public int getRecordAmount()
	{
		return this.recordAmount;
	}
	public getAccount(String ID) throws SQLException
	{
		this.ID = ID;
		recordIDs = new getRecordIDs(this.ID);
		this.recordAmount = recordIDs.getNumOfRecords();
		initializeDB();
		
	}
	
	public String getID()
	{
		return ID;
	}
	public String getPassWord()
	{
		return PassWord;
	}
	public int getLevel()
	{
		return level;
	}
	public int getMoney()
	{
		return money;
	}
	public String toString()
	{
		return this.ID+" password:"+this.PassWord+",level: "+this.level+" ,money: "+this.money;
	}
	
	public void initializeDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			
			Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
			
			System.out.println("Database connected");
			
			String queryString =	
							"select accountid,password,money,level "+
							"from ACCOUNT "+
							"where accountid = '"+this.ID+"'";
			
			PreparedStatement psmt = connection.prepareStatement(queryString);
			
			
			ResultSet rset = psmt.executeQuery(queryString);
			
			if(rset.next())
			{
				ID = rset.getString(1);
				PassWord = rset.getString(2);
				money = Integer.parseInt(rset.getString(3));
				level = Integer.parseInt(rset.getString(4));
				System.out.println("ID: "+this.ID+" money: "+money+" level :"+level);
	
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
	public static void main(String []args)
	{

			getAccount test = null;
			try {
				test = new getAccount("kidreborn");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(test.toString());

	}
	
}