package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import customer.Customer;
import customerService.CustomerServiceImpl;
import utility.IdGenration;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String address = request.getParameter("Address");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		CustomerServiceImpl customerService = new CustomerServiceImpl();
		Customer customer = new Customer();
		
		
		customer.setCustomerName(name);
		customer.setCustomerAddress(address);
		customer.setCustomerEmail(email);
		customer.setCustomerPassword(password);
		
		
		try {
			customerService.insertItem(customer);
			LOGGER.info("Successfully Register");
			out.println("Successfully Register");
			request.getRequestDispatcher("UserLogin.html").include(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
