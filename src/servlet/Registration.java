package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Registration extends HttpServlet {

	 private PreparedStatement pstmt;
	 private static String msg  = "init ", msg2 = "init";
	/**
	 * Constructor of the object.
	 */
	public Registration() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse
		      response) throws ServletException, IOException {
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    //out.println(msg + " " + msg2);
		    // Obtain parameters from the client
		    String userName = request.getParameter("username");
		    String password = request.getParameter("password");
		    int money = 100;
		    int level = 0;
		    try {
		      if (userName.length() == 0 || password.length() == 0) {
		    	System.out.println("error");
		        out.println("UserName and password are required");
		        return; // End the method
		      }

		      storeStudent(userName, password, money, level);   
		      out.println(userName + " is now registered in the database");
		    }
		    catch(Exception ex) {
		    
		      out.println("Error: " + ex.getMessage());
		      //((ServletRequest) response).setAttribute("info", "�û�ע��ɹ�");
		    }
		    finally {
		      //out.close(); 
		      response.setContentType("text/html; charset=gb2312");
		      response.sendRedirect("/login.jsp");
		    }
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
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	 public void init() throws ServletException {
		  initializeJdbc();
	  }
	 
	 private void initializeJdbc() {
		    try {
		      // Declare driver and connection string
		      Class.forName("com.mysql.jdbc.Driver");
		      msg = "driver loaded";
		      Connection connection = (Connection) DriverManager.getConnection
		      ("jdbc:mysql://localhost/ifttt", "root", "092305");
		      msg2 = "database connected";
		      // Create a Statement
		      pstmt = (PreparedStatement) connection.prepareStatement("insert into ACCOUNT " +
		        "(ACCOUNTID, PASSWORD , MONEY, LEVEL) values (?, ?, ?, ?)");
		    }
		    catch (Exception ex) {
		      System.out.println(ex);
		    }
		  }

		  /** Store a student record to the database */
		  private void storeStudent(String userName, String password, int money, int level)
		  		throws SQLException, ClassNotFoundException {
		    pstmt.setString(1, userName);
		    pstmt.setString(2, password);
		    pstmt.setInt(3, money);
		    pstmt.setInt(4, level);
		    pstmt.executeUpdate();
		  }

}
