package adminservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.TaskListBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class AdminLogin extends HttpServlet {
	private Connection connection;
	/**
	 * Constructor of the object.
	 */
	public AdminLogin() {
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String userName = request.getParameter("username");
	    String passWord = request.getParameter("password");
	    String page="";
	  
	    
	    try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery("select ACCOUNTID , password from ACCOUNT where ACCOUNTID  = '"+ userName+"'");
			response.setContentType("text/html; charset=gb2312");
			if(resultSet.next() == false){
				page = "/admin/index.jsp";
			    request.setAttribute("userError", "Name not existed");
			}
			else {
				if(!resultSet.getString("password").equals(passWord)){
					page = "/admin/index.jsp";
					request.setAttribute("passwordError", "password not correct");
				}
				else if( !userName.equals("penn") && !userName.equals("greatshang")){
					page = "/admin/index.jsp";
				    request.setAttribute("userError", "Name not allowed");
				}
				else{
					HttpSession session = request.getSession(true);
					session.setAttribute("username", userName);
					out.println("log in successfully!");
					page = "/admin/db.jsp";
				    //response.sendRedirect("/iftttDemo/Dashboard.html");
					
					TaskListBean tasklistbean = new TaskListBean();
					session.setAttribute("tasklistbean", tasklistbean);
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

	private void initializeJdbc() {
	    try {
	      // Declare driver and connection string
	      Class.forName("com.mysql.jdbc.Driver");
	      connection = (Connection) DriverManager.getConnection
	      ("jdbc:mysql://localhost/ifttt", "root", "092305");
	    }
	    catch (Exception ex) {
	      System.out.println(ex);
	    }
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		initializeJdbc();
	}

}
