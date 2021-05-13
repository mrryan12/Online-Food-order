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

import company.Company;
import customException.CustomException;
import customer.Customer;
import customerService.CustomerServiceImpl;
import itemService.ServiceDaoImpl;
import items.Items;
import orderService.OrderServiceImpl;
import orders.Order;
import orders.OrderItem;
import pdfGenrate.PdfGenrate;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(OrderServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


		HttpSession session = request.getSession(false);

		if(session != null)
		{

			ServiceDaoImpl  service = new ServiceDaoImpl();
			CustomerServiceImpl customer = new CustomerServiceImpl();
			Company company = new Company("rehan");
			OrderServiceImpl orderService = new OrderServiceImpl();

			Order order = new Order();

			String foodName = request.getParameter("food");
			int qty = Integer.parseInt(request.getParameter("qty"));

			List<Customer> customersList = null;
			List<Items> itemList =null;

			int count =0;
			int temp =0;
			try {

				customersList= customer.readItem();
				itemList = service.readItem();

				for(Items items: itemList)
				{
					if(items.getItemName().equals(foodName))
					{
						if(qty <= items.getQuantity())
						{
							OrderItem orderItem = new OrderItem(qty,items);
							order.addOrderItems(orderItem);
							count++;
						}temp++;
					}
				}

				// ------------------------bill generate ------------------------------



				if(temp ==0)
				{
					LOGGER.info("Oops! sorry we don't have that food ..!!!");
					throw new CustomException("Oops! sorry we don't have that food exception..!!!");

				}

				if(count != 0)
				{	

					int id=(int)session.getAttribute("customerId");

					out.println("<center><h1>Order Details</h1>");
					for(Customer customers : customersList)
					{
						if(customers.getCustomerId() == id)
						{
							company.addCustomer(customers);
							customers.placeOrder(order);
							out.println(
									"<link rel='stylesheet' href='./Resorces/css/ItemList.css'>"
											+"<table border='solid' class='content-table'>"
											+ "<thead>"
											+ "<tr>"
											+ "<td>Item Id</td>"
											+ "<td>Item Name</td>"
											+ "<td>Item Price</td>"
											+ "<td>Item quantity</td>"
											+ "</tr>"
											+ "</thead>"
											+ "<tbody>"
									);
							for(OrderItem orderItem : order.getOrderItem())
							{
								out.println(

										"<tr>"
												+ "<td>"+orderItem.getItems().getItemId()+"</td>"
												+ "<td>"+orderItem.getItems().getItemName()+"</td>"
												+ "<td>"+orderItem.getItems().getItemPrice()+"</td>"
												+ "<td>"+orderItem.getQuantity()+"</td>"
												+ "</tr>"
										);


								int itemQty = orderItem.getItems().getQuantity();
								orderItem.getItems().setQuantity(itemQty - orderItem.getQuantity());
								service.modifyQuantity(orderItem.getItems());

							}

							order.setCustomer(customers);
							orderService.insertOrder(order);
							out.println( "</tbody>"
									+ "</table>");

							out.print("Total Amount is "+company.getTotalWorthOfOrdersPlaced());

							out.println("<h3>Thanks for shopping "+ session.getAttribute("username")+ "</h3>");
      						out.println("<a href='UserDashboard.html'> home</a>");

						}
					}

					out.println("</center>");
    				PdfGenrate pdf = new PdfGenrate();  
					pdf.pdfg(order,company);

				}
				else{
					LOGGER.info("Oops! sorry we don't have enough food..!!!");
					throw new CustomException("Oops! sorry we don't have enough food exception");
				}


			} catch (ClassNotFoundException | SQLException | CustomException e) {
				
				out.print(e);
			}

		}
		else
		{
			LOGGER.info("Login First..!!!");
			out.println("login first");	
			request.getRequestDispatcher("UserLogin.html").include(request, response);
		}

	}

}