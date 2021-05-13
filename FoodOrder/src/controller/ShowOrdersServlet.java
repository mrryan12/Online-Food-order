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

import orderService.OrderServiceImpl;
import orders.Order;
import orders.OrderItem;


@WebServlet("/ShowOrdersServlet")
public class ShowOrdersServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ShowOrdersServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			OrderServiceImpl serviceImpl = new OrderServiceImpl();
			int id = (int)session.getAttribute("customerId");

			String name = (String)session.getAttribute("username");

			try {

				List<Order> orders =serviceImpl.showOrder(id);
                LOGGER.info("Show total order");
				
				// ---------------- modifying-----------------------
				
			/*	
				
				session.setAttribute("orderList", orders);      
				request.getRequestDispatcher("/WEB-INF/showOrderlist.jsp").forward(request, response);
				
				*/
				//---------------------------------------------------
				
				out.println("<center><h1>Total orders of "+name+"</h1>");
				for(Order ord : orders)
				{
					
					out.println(
							"<html>"
							+ "<head>"
							+"<link rel='stylesheet' href='./Resorces/css/ItemList.css'>"
							+ "</head>"
							+ "<body>"
							+"<form action='CancelOrderServlet' method=POST>"
							+ "<table border='solid' class='content-table'>"
							+ "<thead>"
							+ "<tr>"
							+ "<td>Order Id</td>"
							+ "<td>customer Name</td>"
							+ "<td>Item Name</td>"
							+ "<td>Item quantity</td>"
							+ "<td>Item Price</td>"
							+"<td>cancel order</td>"
							+ "</tr>"
							+ "</thead>"
							+ "<tbody>"
							);
					out.println(
							"<tr>"
									+ "<td>"+ord.getOrderId()+"</td>"
									+ "<td>"+ord.getCustomer().getCustomerName()+"</td>");

					List<OrderItem> it= ord.getOrderItem();
					for(OrderItem item : it)
					{
						out.println(
								"<td>"+item.getItems().getItemName()+"</td>"
						     	+"<td>"+item.getQuantity()+"</td>"
						     	
								);
					}
					out.println(
							"<td>"+ord.getPrice()+"</td>"
									+ "<td><a href=\"CancelOrderServlet?cancelOrder="+ord.getOrderId()+"\">Cancel order</a></td>"
									+"</tbody>"
									+ "</table>"
									+ "</form>"
		     						+ "</body>"
									+ "</html>"
									
							);
					session.setAttribute("cancelOrder", ord) ;
				}
				out.println("<button><a href='UserDashboard.html'> Back </a></button></center>");

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}else{
			
			LOGGER.info("Please login first");
			out.println("Please login first");
			request.getRequestDispatcher("UserDashboard.html").include(request, response);
			
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

}
