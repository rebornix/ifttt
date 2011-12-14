package Bigproject3;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
public class test 
{
	public static void main(String []args)
	{
		try{
	        SimpleEmail email = new SimpleEmail(); 
	        email.setHostName("smtp.gmail.com"); 
	        email.setSSL(true);  
	        email.setSmtpPort(465); 
	        email.setTLS(true);
	        email.setAuthenticator(new DefaultAuthenticator("creativeshang@gmail.com","sd1990812")); 
	        email.setFrom("creativeshang@gmail.com");  
	        email.setSubject("sdtsda");  
	        email.setCharset("gbk");  
	        email.setMsg("tjeisofgoijsdantjosapi");  
	        
	        email.addTo("sd517@sina.com");
	        email.send();
	        
	        System.out.println("send success!");
			}catch (Exception e){
				System.out.println(e.toString());
			}
	}
	public static void main1(String []args)
	{
		try{
	        SimpleEmail email = new SimpleEmail(); 
	        email.setHostName("smtp.gmail.com"); 
	        email.setSSL(true);  
	        email.setSmtpPort(465); 
	        email.setTLS(true);//gmail  
	        email.setAuthenticator(new DefaultAuthenticator("creativeshang@gmail.com","sd1990812")); 
	        email.setFrom("creativeshang@gmail.com");  
	        email.setSubject("sdatjiaosjt");  
	        email.setCharset("gbk");  
	        email.setMsg("fffffffffffffffffffffffff");  
	        
	        email.addTo("sd517@sina.com");
	        email.send();
	        
	        System.out.println("send success!");
			}catch (Exception e){
				System.out.println(e.toString());
			}
	}
}
