package Bigproject3;

import java.io.IOException;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import weibo4j.Status;
import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;
@SuppressWarnings("unused")
public class That 
{
	private int thatID;
	private int thatType = 0;
	
	
	private String weiboID;
	private String weiboPassWord;
	private String weiboContend;
	
	private String EmailID;
	private String EmailPassWord;
	private String EmailContend;
	private String EmailSubject;
	private String EmailCharset;

	private String EmailFrom;
	private String EmailTarget;
	
	public That(int thatID,String weiboID,String weiboPassWord,String weiboContend)
	{
		this.thatID = thatID;
		this.thatType = 1;
		this.weiboID = weiboID;
		this.weiboPassWord = weiboPassWord;
		this.weiboContend = weiboContend;
	}
	public That(int thatID,String EmailID,String EmailPassWord,String EmailSubject,String EmailContend,
			String EmailTarget)
	{
		this.thatID = thatID;
		this.thatType = 2;
		this.EmailID = EmailID;
		this.EmailPassWord= EmailPassWord;
		this.EmailSubject = EmailSubject;
		this.EmailContend = EmailContend;
		this.EmailTarget = EmailTarget;
		
		this.EmailFrom = EmailID;
		this.EmailCharset ="gbk";
	}
	
	
	public int getThatType()
	{
		return thatType;
	}
	public int getThatID()
	{
		return thatID;
	}
	
	
	public void dothat()
	{
		if(this.thatType==1)
		{
			updateWeiboWithoutPin();
		}
		else if(this.thatType == 2)
		{
			sendGmail();
		}
		else
		{
			System.out.println("error,impossible!!");
		}
	}
	public  void updateWeiboWithoutPin()
	{
		Weibo.setUSER_NAME(this.weiboID);
		Weibo.setPSW(this.weiboPassWord);
		try {
	         System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
	         System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
	         
	         
	            Weibo weibo = new Weibo();
	            // set callback url, desktop app please set to null
	            // http://callback_url?oauth_token=xxx&oauth_verifier=xxx
	            RequestToken requestToken = weibo.getOAuthRequestToken();
	           
	            System.out.println("Got request token.");
	            System.out.println("Request token: "+ requestToken.getToken());
	            System.out.println("Request token secret: "+ requestToken.getTokenSecret());
	            AccessToken accessToken = null;
	           
	            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	            while (null == accessToken) {
	                System.out.println("Open the following URL and grant access to your account:");
	                System.out.println(requestToken.getAuthorizationURL());
	                //BareBonesBrowserLaunch.openURL(requestToken.getAuthorizationURL());
	                System.out.print("Hit enter when it's done.[Enter]:");
	               
	                String pin = weibo.getPin(requestToken.getAuthorizationURL(), requestToken
	                    .getToken());
	                try{
	                    accessToken = requestToken.getAccessToken(pin);
	                } catch (WeiboException te) {
	                    if(401 == te.getStatusCode()){
	                        System.out.println("Unable to get the access token.");
	                    }else{
	                        te.printStackTrace();
	                    }
	                }
	            }
	            System.out.println("Got access token.");
	            System.out.println("Access token: "+ accessToken.getToken());
	            System.out.println("Access token secret: "+ accessToken.getTokenSecret());
	          
	            weibo.setToken(accessToken.getToken(), accessToken.getTokenSecret());
	            
	            Status status = weibo.updateStatus(this.weiboContend);
				System.out.println("Successfully updated the status to ["
						+ status.getText() + "].");
	            
	            //System.exit(0);
	        } catch (WeiboException te) {
	            System.out.println("Failed to get timeline: " + te.getMessage());
	            System.exit( -1);
	        } catch (IOException e) {
	            System.out.println(e.toString());
	            System.exit(-1);
	        }
	}
	public void sendGmail()
	{
		try{
	        SimpleEmail email = new SimpleEmail(); 
	        email.setHostName("smtp.gmail.com"); 
	        email.setSSL(true);  
	        email.setSmtpPort(465); 
	        email.setTLS(true);
	        email.setAuthenticator(new DefaultAuthenticator(this.EmailID,this.EmailPassWord)); 
	        email.setFrom(this.EmailID);  
	        email.setSubject(this.EmailSubject);  
	        email.setCharset("gbk");  
	        email.setMsg(this.EmailContend);  
	        
	        email.addTo(this.EmailTarget);
	        email.send();
	        
	        System.out.println("send success!");
			}catch (Exception e){
				System.out.println(e.toString());
			}
	}
}
