package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import customer.Customer;
import customerService.CustomerServiceImpl;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserLoginServlet.class);	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("uname");
		String pwd = request.getParameter("password");
		List<Customer> CustomerList = new ArrayList<>();
		
		
        CustomerServiceImpl customer = new CustomerServiceImpl();
        
        int count = 0;
        try {
			CustomerList = customer.readItem();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
      
        
        for(Customer customers : CustomerList)
        {
    	   
        	if(username.equals(customers.getCustomerName()) && pwd.equals(customers.getCustomerPassword()))
        	{
        		count++;
        		LOGGER.info("UserLogin Successfully");
        		HttpSession session=request.getSession();
        		session.setAttribute("username", username);
        		session.setAttribute("customerId", customers.getCustomerId());
        		RequestDispatcher dispatcher = request.getRequestDispatcher("UserDashboard.html");
    			dispatcher.forward(request, response);
        		
        	}
        	
        }
       
        if(count == 0)
        {
        	LOGGER.info("username and password not match");
        	out.println("<center><h2>username and password not match</h2></center>");
        	request.getRequestDispatcher("index.html").include(request, response);;

        }
        
	}

}
