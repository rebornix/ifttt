package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class CosumeMoney 
{
	private String userName;
	
	private getAccount getaccount;
	private int taskID = 0;
	private int oldMoney = 0;
	private int newMoney = 0;
	private int level = 0;
	private int insertMoney = 0;
	private int insertID = 0;
	
	///////sql//////
	private Statement psmt;
	private String queryString ;
	private ResultSet rset;
	private Connection connection ;
	
	public static void main(String [] args) throws SQLException, ClassNotFoundException
	{
		CosumeMoney test = new CosumeMoney("greatshang",2);
	}
	
	public CosumeMoney(String userName ,int taskID) throws SQLException, ClassNotFoundException
	{
		this.userName = userName;
		this.taskID = taskID;
		initializeDB();
		
		getaccount = new getAccount(this.userName);
		this.oldMoney = getaccount.getMoney();
		this.level = getaccount.getLevel();
		this.newMoney = this.oldMoney - this.level*100;
		
		this.insertMoney = -(this.level*100);
		updateMoney();
		InsertRecord();
	}
	public void initializeDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
		
		System.out.println("Database connected");
	}
	public void updateMoney()
	{
		queryString =	
				"update ACCOUNT "+
				"set money = "+this.newMoney+" "+
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
		
		this.insertID = this.getInsertID();
		
		queryString =	
				"insert "+
				"into RECORD (RECORDID,ACCOUNTID,TIME,CONSUMPTION,TASKID) "+
				"values ("+this.insertID+",'"+this.userName+"','"+time+"',"+this.insertMoney+","+this.taskID+")";

		psmt = connection.createStatement();
		psmt.executeUpdate(queryString);
		System.out.println("insert record success");
	}
}