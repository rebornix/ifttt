package Bigproject3;


import java.sql.*;

public class getMessagesFromReceiver 
{
	//////sql information////
	private Statement psmt;
	private String queryString ;
	private ResultSet rset;
	private Connection connection ;
	
	//////message information///
	private String receiverName;
	
	private int numOfMessages= 0;
	private int [] messageIDs;
	private String [] senders;
	private String [] subjects;
	private String [] contends;
	private String [] sendtimes;
	private int [] neworolds;
	
	
	public static void main(String []args)
	{
		getMessagesFromReceiver  test = new getMessagesFromReceiver ("kidreborn");
		test.printMessage();
	}
	public void printMessage()
	{
		System.out.println("messages to"+this.receiverName);
		for(int i = 0; i<this.numOfMessages;i++)
		{
			System.out.println(messageIDs[i]+" from "+senders[i]+" "+subjects[i]+" "+contends[i]+" "+sendtimes[i]+" "+neworolds[i]);
		}
	}
	public getMessagesFromReceiver(String receiverName)
	{
		this.receiverName = receiverName;
		initializeDB();
		getMessages();
	}
	public void initializeDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
			
			connection  = DriverManager.getConnection("jdbc:mysql://localhost/ifttt","root","092305");
			
			System.out.println("Database connected");
			
			queryString =	"select count(receiver) "+
							"from MESSAGE "+
							"where receiver = '"+this.receiverName+"'";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get num success");
			
			if(rset.next())
			{
				this.numOfMessages = Integer.parseInt(rset.getString(1));
				
				this.messageIDs 	= new int [this.numOfMessages];
				this.senders 	= new String [this.numOfMessages];
				this.subjects	= new String [this.numOfMessages];
				this.contends	= new String [this.numOfMessages];
				this.sendtimes	= new String [this.numOfMessages];
				this.neworolds	= new int [this.numOfMessages];
			}
			else
			{
				System.out.println("not found");
			}

			System.out.println(this.numOfMessages);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void getMessages()
	{
		try
		{	
			queryString =	"select * "+
							"from MESSAGE "+
							"where receiver = '"+this.receiverName+"'";
			
			psmt = connection.createStatement();		
			rset = psmt.executeQuery(queryString);
			System.out.println("get messages from db success");
			
			int i =0;
			while(rset.next())
			{
				this.messageIDs[i] = Integer.parseInt((rset.getString(1)));
				this.senders[i] = rset.getString(2);
				this.subjects [i] = rset.getString(4);
				this.contends [i] = rset.getString(5);
				this.sendtimes [i]= rset.getString(6);
				this.neworolds [i] = Integer.parseInt((rset.getString(7)));
				//System.out.println(messageIDs[i]+" "+receivers[i]+" "+subjects[i]+" "+contends[i]+" "+sendtimes[i]+" "+neworolds[i]);
				i++;
			}
			System.out.println("get messages success");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public int getNumOfMessages()
	{
		return this.numOfMessages;
	}
	
}
