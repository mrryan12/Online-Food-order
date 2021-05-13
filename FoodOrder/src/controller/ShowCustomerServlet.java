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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import customer.Customer;
import customerService.CustomerServiceImpl;

@WebServlet("/ShowCustomerServlet")
public class ShowCustomerServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ShowCustomerServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		if(session != null)
		{
		CustomerServiceImpl service = new CustomerServiceImpl();
		List<Customer> customers = null;
		
		try {
			customers = service.readItem();
			LOGGER.info("Show total customer");
			request.setAttribute("customers", customers);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/customer-list.jsp").forward(request, response);

		}else
			LOGGER.info("Login First");
			out.println("login first");
	}



}
