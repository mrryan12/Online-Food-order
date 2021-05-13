package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	
	private final static Logger LOGGER = Logger.getLogger(AdminLoginServlet.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("uname");
		String pwd = request.getParameter("password");
		
		if(username.equals("rehan") && pwd.equals("rehan@123"))
		{
			LOGGER.info("Admin login successfully");
    		HttpSession session=request.getSession();
    		session.setAttribute("username", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.html");
			dispatcher.include(request, response);
	
		}else
		{
			LOGGER.info("Oops ! admin credential doesn't match");
			out.println("<center><h2>username and password does not match</h2></center>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
			dispatcher.include(request, response);
		}
	
	}

}
