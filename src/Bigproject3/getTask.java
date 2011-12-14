package Bigproject3;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getTask 
{
	
	///////sql/////////
	public Statement psmt;
	public String queryString ;
	public ResultSet rset;
	public Connection connection ;
	
	///////task/////////
	public int taskID;
	
	
	///////this//////////
	
	public int thisID;
	public int thisType = 0;
	
	public Date date;
	
	public String EmailReceiver;
	public String EmailReceiverPassWord;
	

	public String thisweiboID;
	public String checkContend;
	public int duration; 
	
	
	////////that////////
	public int thatID;
	public int thatType = 0;
	
	
	public String thatweiboID;
	public String weiboPassWord;
	public String weiboContend;
	
	public String EmailID;
	public String EmailPassWord;
	public String EmailContend;
	public String EmailSubject;
	public String EmailCharset;
	public String EmailFrom;
	public String EmailTarget;
	
	
	public static void main(String []args) throws SQLException
	{
		getTask test1 = new getTask(1);
		System.out.println(test1.toString());
		getTask test2 = new getTask(2);
		System.out.println(test2.toString());
		getTask test3 = new getTask(3);
		System.out.println(test3.toString());
		getTask test4 = new getTask(4);
		System.out.println(test4.toString());
		getTask test5 = new getTask(5);
		System.out.println(test5.toString());
	}
	public getTask(int taskID) throws SQLException
	{
		this.taskID = taskID;
		initializeDB();
		DoGetTask();
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
			
			queryString =	"select thisID,thatID "+
							"from TASK "+
							"where Task.taskID ="+this.taskID;
			
			psmt = connection.prepareStatement(queryString);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void DoGetTask() throws SQLException
	{
		rset = psmt.executeQuery(queryString);
		System.out.println("get task success");
		if(rset.next())
		{
			this.thisID = Integer.parseInt(rset.getString(1));
			this.thatID = Integer.parseInt(rset.getString(2));
			System.out.println("thisID: "+thisID+" thatID: "+thatID);
			
			getThisFromDatabase();
			getThatFromDatabase();
			
			
		}
		else
		{
			System.out.println("Not found");
		}

		
	}
	public void getThisFromDatabase() throws SQLException
	{

		String query =	
				"select thisType "+
				"from THIS "+
				"where thisID ="+thisID;
		
		psmt = connection.createStatement();		
		rset = psmt.executeQuery(query);
		
		if(rset.next())
		{
			this.thisType = Integer.parseInt(rset.getString(1));
			System.out.println("thistype ="+ this.thisType);
		}
		else
		{
			System.out.println("not found");
		}
		
		
		if(this.thisType ==1)
		{
			query = "select dateyear,datemonth,dateday,timehour,timeminute "+
					"from THIS "+
					"where thisID = "+this.thisID;
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(query);
			
			if(rset.next())
			{
				int year =Integer.parseInt(rset.getString(1));
				int month = Integer.parseInt(rset.getString(2));
				int day =Integer.parseInt(rset.getString(3));
				int hour =Integer.parseInt(rset.getString(4));
				int minute =Integer.parseInt(rset.getString(5));
				this.date= new Date(year,month,day,hour,minute);
				//System.out.println(date.toString());

			}
			else
			{
				System.out.println("not found");
			}
		}
		else if(this.thisType ==2)
		{
			//System.out.println("aa");
			query = "select emailreceiver,emailreceiverpassword "+
					"from THIS "+
					"where thisID = "+thisID;
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(query);
			
			if(rset.next())
			{
				this.EmailReceiver = rset.getString(1);
				this.EmailReceiverPassWord = rset.getString(2);
			}
			else
			{
				System.out.println("not found");
			}
		}
		else if(this.thisType ==3)
		{
			//System.out.println("aa");
			query = "select weiboid,weibocontend "+
					"from THIS "+
					"where thisID = "+thisID;
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(query);
			
			if(rset.next())
			{
				this.thisweiboID = rset.getString(1);
				this.checkContend = rset.getString(2);
			}
			else
			{
				System.out.println("not found");
			}
		}
		else if(this.thisType == 4)
		{
			query = "select weiboid,duration "+
					"from THIS "+
					"where thisID = "+thisID;
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(query);
			if(rset.next())
			{
				this.thisweiboID = rset.getString(1);
				this.duration = Integer.parseInt(rset.getString(2));
			}
			else
			{
				System.out.println("not found");
			}
		}
		
		System.out.println("get this success");

	}
	public void getThatFromDatabase() throws SQLException
	{

		String query = 
				"select thatType "+
				"from THAT "+
				"where thatID = "+thatID;
		
		rset = psmt.executeQuery(query);
		if(rset.next())
		{
			this.thatType = Integer.parseInt(rset.getString(1));
			
			System.out.println("thatType = "+this.thatType);
		}
		
		if(thatType ==1)
		{
			query = "select weiboid,weibopassword,weibocontend "+
					"from THAT "+
					"where thatID = "+thatID;
			rset = psmt.executeQuery(query);
			
			if(rset.next())
			{
				this.thatweiboID = rset.getString(1);
				this.weiboPassWord = rset.getString(2);
				this.weiboContend = rset.getString(3);	
			}
		}
		else if(thatType ==2)
		{
			query = "select emailid,emailpassword,emailsubject,emailcontend,emailtarget "+
					"from THAT "+
					"where thatID = "+thatID;
			rset = psmt.executeQuery(query);
			
			if(rset.next())
			{
				this.EmailID = rset.getString(1);
				this.EmailPassWord = rset.getString(2);
				this.EmailSubject = rset.getString(3);
				this.EmailContend = rset.getString(4);
				this.EmailTarget = rset.getString(5);

			}
		}
		
		System.out.println("get that success");
	}
	
	public String toString()
	{
		String task = this.taskID+" "+this.thisID+" "+this.thatID+ " ";
		String thisString = "thisType: "+ this.thisType+ " ";
		if(this.thisType == 1)
		{
			thisString += this.date.toString()+ " ";
		}
		else if(this.thisType == 2)
		{				
			thisString += this.EmailReceiver+" "+this.EmailReceiverPassWord+ " ";
		}
		else if(this.thisType ==3 )
		{
			thisString += this.thisweiboID +" "+ this.checkContend +" ";
		}
		else if(this.thisType == 4)
		{
			thisString += this.thisweiboID +" "+ this.duration +" ";
		}
		
		String thatString = "thattype: "+this.thatType + " ";
		if(this.thatType == 1)
		{
			thatString += this.thatweiboID+" "+ this.weiboPassWord+" "+ this.weiboContend+" ";			
		}
		else if(this.thatType == 2)
		{
			thatString += this.EmailID +" "+this.EmailPassWord +" "+this.EmailSubject+" "+ this.EmailContend +" "+this.EmailTarget+" ";
		}
		
		return task + thisString + thatString;
		
	}
}


