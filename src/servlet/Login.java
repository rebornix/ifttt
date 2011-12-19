package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Login() {
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
		HttpSession session = request.getSession(true);
        
        String userName = (String) session.getAttribute("username");
        if(userName != null){
        	RequestDispatcher rd = request.getRequestDispatcher("/Dashboard.jsp");
			rd.forward(request, response);
        }
        else{
        	RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
        }
       
	}

	/**("/login.jsp");
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
		HttpSession session = request.getSession(true);
        
        String userName = (String) session.getAttribute("username");
        if(userName != null){
        	RequestDispatcher rd = request.getRequestDispatcher("/Dashboard.jsp");
			rd.forward(request, response);
        }
        else{
        	RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
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
