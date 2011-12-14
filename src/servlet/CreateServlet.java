package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import Bean.Account;
import Bigproject3.*;
import Bigproject3.InsertTask;

import com.mysql.jdbc.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
public class CreateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CreateServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse
		      response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String thisType = request.getParameter("this_type");
	    String thisValue = request.getParameter("this_value");
	    String thatType = request.getParameter("that_type");
	    String thatValue = request.getParameter("that_value");
	    HttpSession session = request.getSession(true);
	    
	    Account accountBean = (Account)session.getAttribute("account");
	    int taskamount = accountBean.getTaskamount();
	    accountBean.setTaskamount(++taskamount);
	    int taskenabledamount = accountBean.getTaskenabledamount();
	    accountBean.setTaskenabledamount(++taskenabledamount);
		
		try {
			int id = 15;
			InsertTask test1 = new InsertTask(id,id,id);
			//this 
			 if(thisType.equals("thisServiceClock")){
			    	int hour = 0, minute = 0, second = 0;
			    	ClockMessage clockMessage = new ClockMessage(thisValue);
			    	hour = clockMessage.getHour();
			    	minute = clockMessage.getMinute();
			    	second = clockMessage.getSecond(); 	
			    	test1.setThis(2011, 12, 14, hour, minute);
					test1.insertThis();
			    }
			 else if(thisType.equals("thisServiceMail")){
			    	String account = "";
			    	String password = "";
			    	ThisMailMessage thisMailMessage = new ThisMailMessage(thisValue);
			    	account = thisMailMessage.getAccount();
			    	password = thisMailMessage.getPassword();
			    	
			    }
			
			//that
			 if(thatType.equals("thatServiceMail")){
					String account = "";
			    	String password = "";
			    	String subject = "";
			    	String content = "";
			    	ThatMailMessage thatMailMessage = new ThatMailMessage(thatValue);
			    	account = thatMailMessage.getAccount();
			    	password = thatMailMessage.getPassword();
			    	subject = thatMailMessage.getSubject();
			    	content = thatMailMessage.getContent();
			    	test1.setThat("creativeshang@gmail.com", "sd1990812", "Test hei", 
			    			"Rt", account);
			    	test1.insertThat();
				}
				else if(thatType.equals("thatServiceWeibo")){
					String account = "";
			    	String password = "";
			    	String content = "";
			    	ThatWeiboMessage thatWeiboMessage = new ThatWeiboMessage(thatValue);
			    	account = thatWeiboMessage.getAccount();
			    	password = thatWeiboMessage.getPassword();
			    	content = thatWeiboMessage.getContent();
			    	test1.setThat(account, password, content);
					test1.insertThat();
				}
			 
			CreateTask thread = new CreateTask(id);
			thread.start();
			 
			RequestDispatcher rd = request.getRequestDispatcher("/Dashboard.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}




	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

class ClockMessage{
	private String message = null;
	private int hour;
	private int minute;
	private int second;
	public ClockMessage(){}
	public ClockMessage(String message){
		this.message = message;
		derive(message);
	}
	private void derive(String message){
		int dotPos;
		String tempString;
		int length = message.length();
		dotPos = message.indexOf("&");
		hour = Integer.parseInt(message.substring(0, dotPos));
		
		tempString = message.substring(dotPos+1, length);
		length = tempString.length();
		dotPos = tempString.indexOf("&");
		minute = Integer.parseInt(tempString.substring(0, dotPos));
		
		tempString = tempString.substring(dotPos+1, length);
		//dotPos = tempString.indexOf("&");
		second = Integer.parseInt(tempString);
	}
	public int getHour(){
		return this.hour;
	}
	public int getMinute(){
		return this.minute;
	}
	public int getSecond() {
		return this.second;
	}
}
class ThisMailMessage{
	private String message;
	private String account;
	private String password;
	public ThisMailMessage(){}
	public ThisMailMessage(String message){
		this.message = message;
		derive(message);
	}
	private void derive(String message) {
		int dotPos;
		String tempString;
		int length = message.length();
		dotPos = message.indexOf("&");
		this.account = message.substring(0, dotPos);
		
		tempString = message.substring(dotPos+1, length);
		this.password = tempString;
	}
	public String getAccount() {
		return this.account;
	}
	public String getPassword() {
		return this.password;
	}
}
class ThatMailMessage{
	private String message;
	private String account;
	private String password;
	private String subject;
	private String content;
	public ThatMailMessage(){}
	public ThatMailMessage(String message){
		this.message = message;
		derive(message);
	}
	private void derive(String message) {
		int dotPos;
		String tempString;
		int length = message.length();
		dotPos = message.indexOf("&");
		this.account = message.substring(0, dotPos);
		
		tempString = message.substring(dotPos+1, length);
		length = tempString.length();
		dotPos = tempString.indexOf("&");
		this.password = tempString.substring(0, dotPos);
		
		tempString = tempString.substring(dotPos+1, length);
		length = tempString.length();
		dotPos = tempString.indexOf("&");
		this.subject = tempString.substring(0, dotPos);
		
		tempString = tempString.substring(dotPos + 1, length);
		content = tempString;
	}
	public String getAccount() {
		return this.account;
	}
	public String getPassword() {
		return this.password;
	}
	public String getSubject() {
		return this.subject;
	}
	public String getContent() {
		return this.content;
	}
	
}
class ThatWeiboMessage{
	private String message;
	private String account;
	private String password;
	private String content;
	public ThatWeiboMessage(){}
	public ThatWeiboMessage(String message){
		this.message = message;
		derive(message);
	}
	private void derive(String message) {
		int dotPos;
		String tempString;
		int length = message.length();
		dotPos = message.indexOf("&");
		this.account = message.substring(0, dotPos);
		
		tempString = message.substring(dotPos+1, length);
		length = tempString.length();
		dotPos = tempString.indexOf("&");
		this.password = tempString.substring(0, dotPos);
		
		tempString = tempString.substring(dotPos + 1, length);
		content = tempString;
	}
	public String getAccount() {
		return this.account;
	}
	public String getPassword() {
		return this.password;
	}
	public String getContent() {
		return this.content;
	}
	
}
