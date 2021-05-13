package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LogoutServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
         PrintWriter out=response.getWriter();  

		 HttpSession session=request.getSession();  
         session.invalidate();
         
         request.getRequestDispatcher("index.html").include(request, response); 
         LOGGER.info("You are successfully Logged out!");
         out.print("You are successfully logged out!"); 

	}

}