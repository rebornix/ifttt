package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertAccount 
{
	///////sql///////
	private Statement psmt;
	private String queryString ;
	//private ResultSet rset;
	private Connection connection ;
	
	//////account//////
	private String ID;
	private String PassWord;	
	private int level;
	private int money;
	
	
	public void initializeDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
		
		System.out.println("Database connected");
	}
	
	public InsertAccount(String ID,String PassWord,int level,int money)
	{
		this.ID  = ID;
		this.PassWord = PassWord;
		this.level = level;
		this.money = money;

		try
		{
			initializeDB();
			queryString =	"insert "+
							"into ACCOUNT(accountid,password,money,level) "+
							"values ('"+this.ID+"','"+this.PassWord+"',"+this.money+","+this.level+")";
			
			
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
		InsertAccount test1 = new InsertAccount("kidreborn","fuckmyself",3,1000);
		InsertAccount test2 = new InsertAccount("greatshang","fuckyourself",3,1000);
	}
	
	
	
}
