package Bigproject3;

import java.sql.*;
import java.util.Calendar;
public class InsertMoney 
{
	
	/////account money////
	private String userName;
	private int insertMoney;
	private int oldLevel;
	private int newLevel;
	
	///////sql//////
	private Statement psmt;
	private String queryString ;
	private ResultSet rset;
	private Connection connection ;
	
	@SuppressWarnings("unused")
	public static void main(String []args) throws ClassNotFoundException, SQLException
	{
		InsertMoney test = new InsertMoney("greatshang",300);
	}
	public void initializeDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		System.out.println("Database connected");
	
		
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
		
		System.out.println("Database connected");
	}
	
	public InsertMoney(String userName,int insertMoney) throws ClassNotFoundException, SQLException
	{
		initializeDB();
		this.userName = userName;
		if(insertMoney >0)
		{
			this.insertMoney = insertMoney;
		}
		else
		{
			this.insertMoney = 0;
		}
		this.oldLevel = this.getLevel();
		this.newLevel = this.newLevel();
		updateLevel();
		updateMoney();
		InsertRecord();
	}
	
	public int getLevel() throws NumberFormatException, SQLException
	{
		queryString =	
				"select level "+
				"from ACCOUNT "+
				"where accountid = '"+this.userName+"'";

		psmt = connection.createStatement();		
		rset = psmt.executeQuery(queryString);
		System.out.println("get  success");

		if(rset.next())
		{
			int templevel = 0;
			templevel= Integer.parseInt(rset.getString(1));

			System.out.println("level befor insert is"+templevel);
			return templevel;
		}
		else
		{
			System.out.println("Not found");
		}
		return 0;
	}
	public int newLevel()
	{
		int tempAdd = (int)(this.insertMoney/1000);
		if(this.oldLevel +tempAdd >5)
		{
			return 5;
		}
		else
		{
			return this.oldLevel+tempAdd;
		}
	}
	public void updateLevel() 
	{
		queryString =	
				"update ACCOUNT "+
				"SET level = "+this.newLevel+" "+
				"where accountid = '"+this.userName+"'";
		
		try {
			psmt = connection.createStatement();
			psmt.executeUpdate(queryString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("updaste level success");
	}
	public void updateMoney()
	{
		queryString =	
				"update ACCOUNT "+
				"set money = money +"+this.insertMoney+" "+
				"where accountid = '"+this.userName+"'";


		try {
			psmt = connection.createStatement();
			psmt.executeUpdate(queryString);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("updaste level success");
	}
	public int getInsertID() throws SQLException
	{
		String query =	
				"select recordid "+
				"from RECORD ";
		psmt = connection.createStatement();		
		rset = psmt.executeQuery(query);
		System.out.println("get RECORDID success");
		int tempID = 0;
		if(rset.next())
		{	
			int temp=Integer.parseInt((rset.getString(1)));
			while(rset.next())
			{
				temp=Integer.parseInt((rset.getString(1)));
			}
			tempID = temp;
		}
		else
		{
			System.out.println("Not foundccc");
		}
		System.out.println("tempID = "+tempID);
		System.out.println("nextID = "+(tempID+1));
		
		return tempID+1;
		
	}
	public void InsertRecord() throws SQLException
	{
		
		Calendar   c   =   Calendar.getInstance();
		int	year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int	hour = c.get(Calendar.HOUR_OF_DAY);  
		int	minute = c.get(Calendar.MINUTE);  
		String time = (new Date(year,month,day,hour,minute)).toString();
		
		int insertid = this.getInsertID();
		
		queryString =	
				"insert "+
				"into RECORD (RECORDID,ACCOUNTID,TIME,CONSUMPTION) "+
				"values ("+insertid+",'"+this.userName+"','"+time+"',"+this.insertMoney+")";

		psmt = connection.createStatement();
		psmt.executeUpdate(queryString);
		System.out.println("insert record success");
	}
}
