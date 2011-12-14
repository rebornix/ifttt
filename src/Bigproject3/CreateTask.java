package Bigproject3;
import java.sql.*;


public class CreateTask 
{
	private Statement psmt;
	private int taskID;
	private String queryString ;
	private ResultSet rset;
	private Connection connection ;
	
	public static void main(String []args) throws SQLException
	{
		CreateTask test = new CreateTask(3);
		test.start();
	}
	public void start()
	{
		ThreadCreate.thread[taskID].start();
	}
	public CreateTask(int taskID) throws SQLException
	{
		this.taskID = taskID;
		initializeDB();
		DoCreateTask();
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
			
			queryString =	"select THISID,THATID "+
							"from TASK "+
							"where TASKID = "+this.taskID;
			
			psmt = connection.createStatement();		
			//rset = psmt.executeQuery(queryString);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void DoCreateTask() throws SQLException
	{
		rset = psmt.executeQuery(queryString);
		int thisID = 0;
		int thatID = 0;
		if(rset.next())
		{
			thisID = Integer.parseInt(rset.getString(1));
			thatID = Integer.parseInt(rset.getString(2));
			System.out.println("thisID: "+thisID+" thatID: "+thatID);
			
			This newThis = getThisFromDatabase(thisID);
			That newThat = getThatFromDatabase(thatID);
			
			Task tempTask = new Task(this.taskID,newThis,newThat);
			ThreadCreate.thread[taskID] = new Thread(tempTask);
			
		}
		else
		{
			System.out.println("Not found");
		}

		
	}
	
	public This getThisFromDatabase(int thisID) throws SQLException
	{
		
		
		This newThis = null;
		String query =	
				"select THISTYPE "+
				"from THIS "+
				"where THISID ="+thisID;
		
		psmt = connection.createStatement();		
		rset = psmt.executeQuery(query);
		
		int thisType=0;
		if(rset.next())
		{
			thisType = Integer.parseInt(rset.getString(1));
		}
		else
		{
			System.out.println("not found");
		}
		
		if(thisType ==1)
		{
			query = "select DATEYEAR,DATEMONTH,DATEDAY,TIMEHOUR,TIMEMINUTE "+
					"from THIS "+
					"where THISID = "+thisID;
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(query);
			
			if(rset.next())
			{
				int year =Integer.parseInt(rset.getString(1));
				int month = Integer.parseInt(rset.getString(2));
				int day =Integer.parseInt(rset.getString(3));
				int hour =Integer.parseInt(rset.getString(4));
				int minute =Integer.parseInt(rset.getString(5));

				newThis= new This(thisID,year,month,day,hour,minute);
				return newThis;

			}
			else
			{
				System.out.println("not found");
			}
		}
		else if(thisType ==2)
		{
			query = "select EMAILRECEIVER,EMAILRECEIVERPASSWORD "+
					"from THIS "+
					"where THISID = "+thisID;
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(query);
			
			if(rset.next())
			{
				String EmailReceiver = rset.getString(1);
				String EmailReceiverPassword = rset.getString(2);
				newThis= new This(thisID,EmailReceiver, EmailReceiverPassword);
				return newThis;
			}
			else
			{
				System.out.println("not found");
			}
		}
		else if(thisType ==3)
		{
			query = "select WEIBOID,WEIBOCONTEND "+
					"from THIS "+
					"where THISID = "+thisID;
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(query);
			
			if(rset.next())
			{
				String weiboID = rset.getString(1);
				String checkContend = rset.getString(2);
			
				newThis= new This(thisID,weiboID,checkContend,"whatever");
				return newThis;
			}
		}
		else if(thisType == 4)
		{
			query = "select WEIBOID,DURATION "+
					"from THIS "+
					"where THISID = "+thisID;
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(query);
			if(rset.next())
			{
				String thisweiboID = rset.getString(1);
				int duration = Integer.parseInt(rset.getString(2));
				
				newThis = new This(thisID,thisweiboID,duration);
				return newThis;
			}
			else
			{
				System.out.println("not found");
			}
		}
		else
			return null;
		
		return null;
		
	}
	public That getThatFromDatabase(int thatID) throws SQLException
	{
		That newThat = null;
		String query = 
				"select THATTYPE "+
				"from THAT "+
				"where THATID = "+thatID;
		
		rset = psmt.executeQuery(query);
		int thatType = 0;
		if(rset.next())
		{
			thatType = Integer.parseInt(rset.getString(1));
		}
		
		if(thatType ==1)
		{
			query = "select WEIBOID,WEIBOPASSWORD,WEIBOCONTEND "+
					"from THAT "+
					"where THATID = "+thatID;
			rset = psmt.executeQuery(query);
			
			if(rset.next())
			{
				String weiboID = rset.getString(1);
				String weiboPassWord = rset.getString(2);
				String weiboContend = rset.getString(3);
			
				newThat = new That(thatID,weiboID,weiboPassWord,weiboContend);
				System.out.println("get that success");
				return newThat;
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
				String EmailID = rset.getString(1);
				String EmailPassWord = rset.getString(2);
				String EmailSubject = rset.getString(3);
				String EmailContend = rset.getString(4);
				String EmailTarget = rset.getString(5);
				newThat = new That(thatID,EmailID,EmailPassWord,EmailSubject,EmailContend,EmailTarget);
				System.out.println("get that success");
				return newThat;
			}
		}
		else
			return null;
		
		return null;
			
		
	}

}


