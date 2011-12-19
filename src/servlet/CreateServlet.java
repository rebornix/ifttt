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
import Bean.TaskListBean;
import Bigproject3.*;

import com.mysql.jdbc.*;
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
	    String userName = (String) session.getAttribute("username");
	    
	    System.out.println(userName);
		
		try {
			int id = (new getNewTaskID()).getNew();
			InsertTask test1 = new InsertTask(id,id,id);
			InsertExecution insertexecution = new InsertExecution(userName, id, 100, 1);
			
			//this 
			 if(thisType.equals("thisServiceClock")){
			    	int year = 0, month = 0, date = 0, hour = 0, minute = 0;
			    	ClockMessage clockMessage = new ClockMessage(thisValue);
			    	year = clockMessage.getYear();
			    	month = clockMessage.getMonth();
			    	date = clockMessage.getDate();
			    	hour = clockMessage.getHour();
			    	minute = clockMessage.getMinute();
			    	
			    	test1.setThis(year, month, date, hour, minute);
					
			    }
			 else if(thisType.equals("thisServiceMail")){
			    	String account = "";
			    	String password = "";
			    	ThisMailMessage thisMailMessage = new ThisMailMessage(thisValue);
			    	account = thisMailMessage.getAccount();
			    	password = thisMailMessage.getPassword();
			    	test1.setThis(account, password);
			    }
			 else if(thisType.equals("thisServiceWeiboWord")){
				   String account = "";
				   String word = "";
				   ThisWeiboWordMessage thisWeiboWordMessage = new ThisWeiboWordMessage(thisValue);
				   account = thisWeiboWordMessage.getAccount();
				   word = thisWeiboWordMessage.getWord();
				   test1.setThis(account, word,"ll");
			 }
			 else if(thisType.equals("thisServiceWeiboDuration")){
				   String account = "";
				   int duration = 0;
				   ThisWeiboDurationMessage thisWeiboDurationMessage = new ThisWeiboDurationMessage(thisValue);
				   account = thisWeiboDurationMessage.getAccount();
				   duration = thisWeiboDurationMessage.getDuration();
				   test1.setThis(account, duration);
			 }
			 test1.insertThis();
			//that
			 if(thatType.equals("thatServiceMail")){
					String account = "";
			    	String password = "";
			    	String target = "";
			    	String subject = "";
			    	String content = "";
			    	ThatMailMessage thatMailMessage = new ThatMailMessage(thatValue);
			    	account = thatMailMessage.getAccount();
			    	password = thatMailMessage.getPassword();
			    	subject = thatMailMessage.getSubject();
			    	content = thatMailMessage.getContent();
			    	target = thatMailMessage.getTarget();
			    	test1.setThat("creativeshang@gmail.com", "sd1990812", "Test! ", 
			    			"Rt", target);
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
			
			//This way, Dashboard.jsp can not be refreshed
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/Dashboard.jsp");
			rd.forward(request, response);
			*/
			response.sendRedirect("/ifttt/Dashboard.jsp");
			
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
	private int year;
	private int month;
	private int date;
	private int hour;
	private int minute;
	
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
		year = Integer.parseInt(message.substring(0, dotPos));
		
		tempString = message.substring(dotPos+1, length);
		length = tempString.length();
		dotPos = tempString.indexOf("&");
		month = Integer.parseInt(tempString.substring(0, dotPos));
		
		tempString = tempString.substring(dotPos+1, length);
		length = tempString.length();
		dotPos = tempString.indexOf("&");
		date = Integer.parseInt(tempString.substring(0, dotPos));
		
		tempString = tempString.substring(dotPos+1, length);
		length = tempString.length();
		dotPos = tempString.indexOf("&");
		hour = Integer.parseInt(tempString.substring(0, dotPos));
		
		tempString = tempString.substring(dotPos+1, length);
		//dotPos = tempString.indexOf("&");
		minute = Integer.parseInt(tempString);
	}
	public int getYear(){
		return this.year;
	}
	public int getMonth(){
		return this.month;
	}
	public int getDate(){
		return this.date;
	}
	public int getHour(){
		return this.hour;
	}
	public int getMinute(){
		return this.minute;
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

class ThisWeiboWordMessage{
	private String message;
	private String account;
	private String word;
	public ThisWeiboWordMessage(){}
	public ThisWeiboWordMessage(String message){
		this.message = message;
		derive(message);
	}
	private void derive(String message) {
		int dotPos;
		String tempString;
		int length = message.length();
		dotPos = message.indexOf("&");
		this.account = message.substring(0, dotPos);
		
		word = message.substring(dotPos+1, length);
	}
	public String getAccount() {
		return this.account;
	}
	public String getWord() {
		return this.word;
	}
	
}
class ThisWeiboDurationMessage{
	private String message;
	private String account;
	private int duration;
	public ThisWeiboDurationMessage(){}
	public ThisWeiboDurationMessage(String message){
		this.message = message;
		derive(message);
	}
	private void derive(String message) {
		int dotPos;
		String tempString;
		int length = message.length();
		dotPos = message.indexOf("&");
		this.account = message.substring(0, dotPos);
		
		this.duration = Integer.parseInt(message.substring(dotPos+1, length));
	}
	public String getAccount() {
		return this.account;
	}
	public int getDuration() {
		return this.duration;
	}
	
}

class ThatMailMessage{
	private String message;
	private String account;
	private String password;
	private String target;
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
		this.target = tempString.substring(0, dotPos);
		
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
	public String getTarget(){
		return this.target;
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
