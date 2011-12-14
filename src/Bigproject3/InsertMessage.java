package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class InsertMessage 
{
	///////sql///////
	private Statement psmt;
	private String queryString ;
	private ResultSet rset;
	private Connection connection ;
	
	//////message//////
	private int messageID;
	private String sender;
	private String receiver;
	private String subject;
	private String contend;
	private String sendtime;
	//private int neworold = 1;
	

	public void initializeDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
		
		System.out.println("Database connected");
	}
	public int getInsertID() throws SQLException
	{
		String query =	
				"select messageid "+
				"from MESSAGE ";
		psmt = connection.createStatement();		
		rset = psmt.executeQuery(query);
		System.out.println("get messageID success");
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
			System.out.println("Not found");
		}
		System.out.println("tempID = "+tempID);
		System.out.println("nextID = "+(tempID+1));
		
		return tempID+1;
		
	}
	public InsertMessage(String sender,String receiver,String subject,String contend)
	{
		
		this.sender =sender;
		this.receiver = receiver;
		this.subject = subject;
		this.contend = contend;
		
		Calendar   c   =   Calendar.getInstance();
		int	year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int	hour = c.get(Calendar.HOUR_OF_DAY);  
		int	minute = c.get(Calendar.MINUTE);  
		this.sendtime = (new Date(year,month,day,hour,minute)).toString();
		try
		{
			initializeDB();
			this.messageID  = getInsertID();
			
			queryString =	"insert "+
							"into MESSAGE(messageid,sender,receiver,subject,emailcontend,sendtime,new) "+
							"values ("+this.messageID+",'"+this.sender+"','"+
							this.receiver+"','"+this.subject+"','"+this.contend+"','"+this.sendtime+"',1)";
			
			
			psmt = connection.createStatement();
			psmt.executeUpdate(queryString);
			System.out.println("insert message "+this.messageID+" success");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String []args)
	{
		InsertMessage test1 = new InsertMessage("greatshang","kidreborn","test1","fuck you bitch");
		InsertMessage test2 = new InsertMessage("greatshang","kidreborn","test2","fuck myself");
	}
	
	
	
}
