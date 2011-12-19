package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@SuppressWarnings("unused")
public class InsertTask 
{
	///////sql/////////
	private Statement psmt;
	private String queryString ;
	private ResultSet rset;
	private Connection connection ;
	
	///////task/////////
	private int taskID;
	
	
	///////this//////////
	
	private int thisID;
	private int thisType = 0;
	
	private Date date;
	
	private String EmailReceiver;
	private String EmailReceiverPassWord;
	

	private String thisweiboID;
	private String checkContend;
	private int duration; 
	
	
	////////that////////
	private int thatID;
	private int thatType = 0;
	
	
	private String thatweiboID;
	private String weiboPassWord;
	private String weiboContend;
	
	private String EmailID;
	private String EmailPassWord;
	private String EmailContend;
	private String EmailSubject;
	private String EmailCharset;
	private String EmailFrom;
	private String EmailTarget;
	
	
	public InsertTask(int taskID,int thisID,int thatID,String whatever) throws ClassNotFoundException, SQLException
	{
		this.taskID = taskID;
		this.thisID = thisID;
		this.thatID = thatID;
		initializeDB();
	}
	public InsertTask(int taskID,int thisID,int thatID) throws SQLException, ClassNotFoundException
	{
		
		this.taskID = taskID;
		this.thisID = thisID;
		this.thatID = thatID;
		initializeDB();
		insertTask();
	}
	
	public void initializeDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");
		
		connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
		
		System.out.println("Database connected");
	}
	
	public void insertTask()
	{
		try
		{
			queryString =	"insert "+
							"into TASK(taskid,thisid,thatid ,status) "+
							"values ("+this.taskID+","+this.thisID+","+this.thatID+",1)";
			
			
			psmt = connection.createStatement();
			psmt.executeUpdate(queryString);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//////thisType =1/////
	public void setThis(int year,int month,int day,int hour,int minute)
	{
		this.thisType = 1;
		this.date = new Date(year,month,day,hour,minute);
	}
	
	//////thisType =2/////
	public void setThis(String EmailReceiver,String EmailReceiverPassword)
	{
		this.thisType = 2;
		this.EmailReceiver=EmailReceiver;
		this.EmailReceiverPassWord = EmailReceiverPassword;
	}
	
	//////thisType =3/////
	public void setThis(String weiboID,String checkContend,String whatever)
	{
		this.thisType = 3;
		this.thisweiboID = weiboID;
		this.checkContend = checkContend;
	}
	
	//////thisType =4/////
	public void setThis(String weiboID,int duration)
	{
		this.thisType =4;
		this.thisweiboID = weiboID;
		this.duration = duration;
	}
	
	
	public void insertThis() throws SQLException
	{
		String query ="";
		if(this.thisType == 1)
		{
			
			query= "insert into "+
					"THIS(thisid,thistype,dateyear,datemonth,dateday,timehour,timeminute) "+
					"values("+this.thisID+","+this.thisType+","+this.date.year+","+
					this.date.month+","+this.date.day+","+this.date.hour+","+this.date.minute+")";
		}
		else if(this.thisType == 2)
		{
			query= "insert into "+
					"THIS(thisid,thistype,emailreceiver,emailreceiverpassword) "+
					"values("+this.thisID+","+this.thisType+",'"+this.EmailReceiver+"','"+this.EmailReceiverPassWord+"')";
		}
		else if(this.thisType == 3)
		{
			query= "insert into "+
					"THIS(thisid,thistype,weiboid,weibocontend) "+
					"values("+this.thisID+","+this.thisType+",'"+this.thisweiboID+"','"+this.checkContend+"')";
		}
		else if(this.thisType == 4)
		{
			query= "insert into "+
					"THIS(thisid,thistype,weiboid,duration) "+
					"values("+this.thisID+","+this.thisType+",'"+this.thisweiboID+"',"+this.duration+")";
		}
		psmt = connection.createStatement();
		psmt.executeUpdate(query);
		
	}
	//////thatType = 1/////
	public void setThat(String weiboID,String weiboPassWord,String weiboContend)
	{
		this.thatType = 1;
		this.thatweiboID = weiboID;
		this.weiboPassWord = weiboPassWord;
		this.weiboContend = weiboContend;
	}
	//////thatType = 2/////
	public void setThat(String EmailID,String EmailPassWord,String EmailSubject,String EmailContend,
			String EmailTarget)
	{
		this.thatType = 2;
		this.EmailID = EmailID;
		this.EmailPassWord= EmailPassWord;
		this.EmailSubject = EmailSubject;
		this.EmailContend = EmailContend;
		this.EmailTarget = EmailTarget;
		
		this.EmailFrom = EmailID;
		this.EmailCharset ="gbk";
	}
	
	
	public void insertThat() throws SQLException
	{
		String query = "";
		if(this.thatType == 1)
		{
			query= "insert into "+
					"THAT(thatid,thattype,weiboid,weibopassword,weibocontend) "+
					"values("+this.thatID+","+this.thatType+",'"+this.thatweiboID+"','"+this.weiboPassWord+"','"+this.weiboContend+"')";
		}
		else if(this.thatType == 2)
		{
			query= "insert into "+
					"THAT(thatid,thattype,emailid,emailpassword,emailsubject,emailcontend,emailtarget) "+
					"values("+this.thatID+","+this.thatType+",'"+this.EmailID+"','"+this.EmailPassWord+"','"+
					this.EmailSubject+"','"+this.EmailContend+"','"+this.EmailTarget+"')";

		}
		
		psmt = connection.createStatement();
		psmt.executeUpdate(query);
	}
	
	
	public static void main(String []args) throws SQLException, ClassNotFoundException
	{
		//InsertTask test1 = new InsertTask(1,1,1,"whatever");
		
		//test1.setThis(1990, 8, 12, 6, 30);
		//test1.insertThis();
		
		//test1.setThat("sd517@sina.com", "sd1990812", "testfromdatabase");
		//test1.insertThat();
		
		/*
		InsertTask test3 = new InsertTask(4,4,4,"whatever");
		test3.setThis("1042022145", 5);
		test3.setThat("creativeshang@gmail.com", "sd1990812", "test", "If I fail,I will fuck you too!", "sd517@sina.com");
		test3.insertThis();
		test3.insertThat();
		*/
		/*
		InsertTask test5 = new InsertTask(5,5,5);
		test5.setThis("1042022145", "testtest", "whatever");
		test5.insertThis();
		test5.setThat("285926445@qq.com", "sd1990812", "我爱王雪姣");
		test5.insertThat();
		*/
		/*
		InsertTask test6 = new InsertTask(3,3,3);
		InsertTask test7 = new InsertTask(4,4,4);
		*/
	}
	
}
