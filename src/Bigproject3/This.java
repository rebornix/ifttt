package Bigproject3;

import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;

import weibo4j.Paging;
import weibo4j.Status;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;



public class This 
{
	private int thisID;
	private int thisType = 0;
	
	private Date date;
	
	private String EmailReceiver;
	private String EmailReceiverPassWord;
	
	private String weiboID;
	private String checkContend;
	
	private int duration; 
	
	
	
	public This(int thisID,int year,int month,int day,int hour,int minute)
	{
		this.thisID = thisID;
		this.thisType = 1;
		
		this.date = new Date(year,month,day,hour,minute);
		
	}
	public This(int thisID,String EmailReceiver,String EmailReceiverPassword)
	{
		this.thisID = thisID;
		this.thisType = 2;
		this.EmailReceiver=EmailReceiver;
		this.EmailReceiverPassWord = EmailReceiverPassword;
	}
	public This(int thisID,String weiboID,String checkContend,String whatever)
	{
		this.thisID = thisID;
		this.thisType = 3;
		this.weiboID = weiboID;
		this.checkContend = checkContend;
	}
	public This(int thisID,String weiboID,int duration)
	{
		this.thisID = thisID;
		this.thisType =4;
		this.weiboID = weiboID;
		this.duration = duration;
	}
	
	public int getThisType()
	{
		return thisType;
	}
	public int getThatID()
	{
		return thisID;
	}
	
	
	
	public int doThis() throws InterruptedException
	{
		if(thisType == 1)
		{
			return timeReady();
		}
		else if(thisType ==2)
		{
			return isNew();
		}
		else if(thisType == 3)
		{
			return checkWeibo();
		}
		else if(thisType == 4)
		{
			long id = getFirstID();
			return waitingNewWeibo(id);
		}
		else
			return 0;

	}
	
	public  int timeReady()
	{
		Calendar now=Calendar.getInstance();
		if(	(now.get(Calendar.YEAR) == this.date.getYear())
				&&(now.get(Calendar.MONTH)+1==this.date.getMonth())
				&&(now.get(Calendar.DAY_OF_MONTH)==this.date.getDay())
				&&(now.get(Calendar.HOUR_OF_DAY)==this.date.getHour())
				&&(now.get(Calendar.MINUTE)==this.date.getMinute())
				)
		{
			System.out.println("time ready");
			return 1;
		}
		else 
			return 0;
		
	}
	//if(IsNew("imap.126.com","creativeshang@126.com","sd1990812"))
	
	public int isNew()
	{
		String Server = "imap.126.com";
		String User = EmailReceiver;
		String Password = EmailReceiverPassWord;
		
		Properties prop = System.getProperties();
		prop.put("mail.imap.host",Server);
		prop.put("mail.imap.auth.plain.disable","true");
		Session session = Session.getInstance(prop,null);
		session.setDebug(false);
		IMAPFolder folder = null;
		IMAPStore store = null;
		try{
			store = (IMAPStore)session.getStore("imap");
			store.connect(Server, User, Password);
			folder = (IMAPFolder)store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			if(folder.getNewMessageCount()> 0)
			{
				System.out.println("there is new message in INBOX.");
				return 1;
			}
		}catch(MessagingException ex){
			System.err.println("message exception");
			ex.printStackTrace();
		}
		finally{
			try{
				if(folder != null)
					folder.close(true);
				if(store != null)
					store.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int checkWeibo() 
	{
		AccessToken accessToken = null;
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		Weibo weibo = new Weibo();	
		try 
		{
			RequestToken requestToken = weibo.getOAuthRequestToken();
        	String pin = weibo.getPin(requestToken.getAuthorizationURL(), requestToken
                    .getToken());
        	accessToken = requestToken.getAccessToken(pin);
        	weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());
        	
        	Paging pag = new Paging();
			pag.setSinceId(3343021761165196l);
			pag.setCount(10);
			
        	List<Status> statuses = weibo.getUserTimeline(this.weiboID,pag);
        	
        	String contend = null;
            
			for (Status status : statuses) 
			{
	            contend = status.getText();
	            if(contend.equals(this.checkContend))
	        	{
					System.out.println("there is that checkContend existed.");
	        		return 1;
	        	}
	        	else
	        	{
	        		System.out.println("thistime there is no checkContend existed.");
	        		return 0;
	        	}
	        }

        	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean waitingNewWeibo()
	{
		
		return true;
	}
	
	public long getFirstID()
	{
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		AccessToken accessToken = null;
		Weibo weibo = new Weibo();
		long tempID = 0;
		try 
		{
			RequestToken requestToken = weibo.getOAuthRequestToken();
        	String pin = weibo.getPin(requestToken.getAuthorizationURL(), requestToken
                    .getToken());
        	accessToken = requestToken.getAccessToken(pin);
        	weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());
        	Paging pag = new Paging();
			pag.setSinceId(3343021761165196l);
			pag.setCount(2);
			List<Status> statuses = weibo.getUserTimeline(this.weiboID,pag);
			for (Status status : statuses) 
			{
	            System.out.println(status.getUser().getName() + ":" +status.getId()+":"+
	                             status.getText() + status.getOriginal_pic());
	            tempID = status.getId();
	            break;
	        }
        	
		}
		catch (Exception e) 
		{
				e.printStackTrace();
		}
		return tempID;
	}
	public int waitingNewWeibo(long firstID ) throws InterruptedException
	{
		int duration = this.duration;
		int  last = duration *60000;
		Thread.sleep(last);
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		AccessToken accessToken = null;
		Weibo weibo = new Weibo();
		try {
			RequestToken requestToken = weibo.getOAuthRequestToken();
        	String pin = weibo.getPin(requestToken.getAuthorizationURL(), requestToken
                    .getToken());
        	accessToken = requestToken.getAccessToken(pin);
        	weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());
        	Paging pag = new Paging();
			pag.setSinceId(3343021761165196l);
			pag.setCount(2);
			
        	List<Status> statuses = weibo.getUserTimeline(this.weiboID,pag);
        	long tempID = 0;
   
			for (Status status : statuses) {
	            System.out.println(status.getUser().getName() + ":" +status.getId()+":"+
	                             status.getText() + status.getOriginal_pic());
	            tempID = status.getId();
	            break;
	        }
			if(tempID > firstID)
			{
				System.out.println("success,there is new weibo");
				return 1;
			}
			else
			{
				System.out.println("This time,there is no new weibo");
				return 3;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//Thread.sleep(30000);
		return 3;
	}
}
