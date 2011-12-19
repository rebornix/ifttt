package adminservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bigproject3.InsertMessage;
import Bigproject3.InsertNotice;
import Bigproject3.getAccountIDs;

public class SendMessage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SendMessage() {
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
		
		 String textField= request.getParameter("textfield");
		 String receiver = request.getParameter("dropdown");
		 
		 if(receiver.equals("option2")){
			 //send to everyone! 
		 }
		    
		RequestDispatcher rd = request.getRequestDispatcher("/ifttt/admin/db.jsp");
		rd.forward(request, response);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		 String textField= request.getParameter("textfield");
		 String receiver = request.getParameter("dropdown");
		 
		 if(receiver.equals("option2")){
			 //send to everyone! 
			 getAccountIDs idlist = new getAccountIDs();
			 int amount = idlist.getNumOfAccouts();
			 String[] receivers = new String[amount];
			 receivers = idlist.getAllAccounts();
			 InsertNotice notice = new InsertNotice("penn", "all", "Welcome", textField);
			 for(int i = 0; i < amount; i++){
				 InsertMessage test = new InsertMessage("penn", receivers[i], "Welcome!", textField);
			 }
		 }
		 else if(receiver.equals("option4")){
			 InsertMessage test1 = new InsertMessage("penn","greatshang","Hello Buddy",textField);
		 }
		    
		RequestDispatcher rd = request.getRequestDispatcher("/admin/db.jsp");
		rd.forward(request, response);
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
