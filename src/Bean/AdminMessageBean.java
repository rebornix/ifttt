package Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Bigproject3.getMessagesFromSender;

public class AdminMessageBean 
{
	private int amount = 0;
	
	//////sql information////
	private Statement psmt;
	private String queryString ;
	private ResultSet rset;
	private Connection connection ;
	
	//////message information///
	private int [] messageIDs;
	private String [] receivers;
	private String [] senders;
	private String [] subjects;
	private String [] contends;
	private String [] sendtimes;
	private int [] neworolds;
	
	public static void main(String []args)
	
	{
		AdminMessageBean test = new AdminMessageBean();
	}
	public AdminMessageBean()
	{
		initializeDB();
		setMessages();
	}
	
	public void initializeDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			
			connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
			
			System.out.println("Database connected");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void setMessages()
	{
		try
		{	
			queryString =	"select count(messageid) "+
			"from NOTICE ";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get num success");
			
			if(rset.next())
			{
			this.amount = Integer.parseInt(rset.getString(1));
			
			this.messageIDs = new int [this.amount];
			this.receivers 	= new String [this.amount];
			this.senders 	= new String [this.amount];
			this.subjects	= new String [this.amount];
			this.contends	= new String [this.amount];
			this.sendtimes	= new String [this.amount];
			this.neworolds	= new int [this.amount];
			}
			else
			{
			System.out.println("not found");
			}
			
			System.out.println(this.amount);
			
			queryString =	"select * "+
							"from NOTICE ";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get messages from db success");
			
			int i =0;
			while(rset.next())
			{
				this.messageIDs[i] = Integer.parseInt((rset.getString(1)));
				this.senders[i] = rset.getString(2);
				this.receivers[i]= rset.getString(3);
				this.subjects [i] = rset.getString(4);
				this.contends [i] = rset.getString(5);
				this.sendtimes [i]= rset.getString(6);
				this.neworolds [i] = Integer.parseInt((rset.getString(7)));
				System.out.println(messageIDs[i]+" "+senders[i]+" "+receivers[i]+" "+subjects[i]+" "+contends[i]+" "+sendtimes[i]+" "+neworolds[i]);
				i++;
			}
			System.out.println("get messages success");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public int [] getMessageIDs()
	{
		return this.messageIDs;
	}
	public int [] getNeworolds()
	{
		return this.neworolds;
	}
	public String [] getReceivers()
	{
		return this.receivers;
	}
	public String [] getSenders()
	{
		return this.senders;
	}
	public String [] getSubjects()
	{
		return this.subjects;
	}
	public String [] getSendtimes()
	{
		return this.sendtimes;
	}
	public String [] getContend()
	{
		return this.contends;
	}
	public int getAmount(){
		return this.amount;
	}
}