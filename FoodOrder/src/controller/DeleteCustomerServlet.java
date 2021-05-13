package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import customer.Customer;
import customerService.CustomerServiceImpl;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(DeleteCustomerServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("customerId"));
		
		CustomerServiceImpl service = new CustomerServiceImpl();
		
		List<Customer> customers = null;
		
		int count = 0;
		try {
			customers = service.readItem();
			
			for(Customer customer : customers)
			{
				if(customer.getCustomerId() == id)
				{
					service.deleteItem(customer);
					count++;
					LOGGER.info("Customer Deleted successfully");
					out.print("<h2>customer deleted</h2>");
					request.getRequestDispatcher("AdminDashboard.html").include(request, response);

				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if(count == 0)
		{
			LOGGER.info("Id not found");
			out.print("<h2>Id not found</h2>");
			request.getRequestDispatcher("deleteCustomer.html").include(request, response);
		}
	
	}

}
