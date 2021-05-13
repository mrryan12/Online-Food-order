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
import itemService.ServiceDaoImpl;
import items.Items;
import orderDao.OrderDaoImpl;
import orderService.OrderServiceImpl;
import orders.Order;
import orders.OrderItem;


@WebServlet("/CancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
	
	private final static Logger LOGGER = Logger.getLogger( CancelOrderServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		OrderServiceImpl service = new OrderServiceImpl();
		ServiceDaoImpl serviceDao = new  ServiceDaoImpl();
		OrderDaoImpl order = new OrderDaoImpl();
		
		
	HttpSession session= request.getSession(false);
		
		if(session != null)
		{
		int orderId = Integer.parseInt(request.getParameter("cancelOrder"));
		
		Order orders = new Order();
		try {
			
			
			orders = order.showOrderByOrderId(orderId);
		    service.cancelOrder(orders);
		    int oldqty = 0;
		    
		    
		    List<OrderItem>  orderItem = orders.getOrderItem();
		    List<Items> itemService = serviceDao.readItem();
	
		    int qty = 0; 
		  
		    for(OrderItem orderItems : orderItem)
		    {
		    	qty = orderItems.getQuantity();
		    	
		    	
		    	
		    	 for(Items items : itemService)
				    {
				    	if(orderItems.getItems().getItemId() == items.getItemId())
				    	{
				    		oldqty = items.getQuantity();
				    	}
				    }
		    	
		    	orderItems.getItems().setQuantity(oldqty + qty);
				
				serviceDao.modifyQuantity(orderItems.getItems());
				
		    }
		    


		out.println("Order cancel succesfully");
		LOGGER.info("Order Cancel Successfully");
		request.getRequestDispatcher("UserDashboard.html").include(request, response);  
		    
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		}else{
			out.println("login first");
		}
 	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	/*	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Order order = new Order();
		Customer customer = new Customer();
		Items item = new Items();
		
		HttpSession session= request.getSession(false);
		
		if(session != null)
		{
		
		
		Order orders = (Order)session.getAttribute("cancelOrder");
	
		List<OrderItem>  orderItem = orders.getOrderItem();
		
		order.setOrderId(orders.getOrderId());
		order.setOrderItem(orderItem);
		
		OrderServiceImpl service = new OrderServiceImpl();
		ServiceDaoImpl serviceDao = new  ServiceDaoImpl();
		
		try {
			service.cancelOrder(order);
			int Oldqty = 0;
			for(OrderItem ord : orderItem)
			{
				
				
				List<Items> itemService = serviceDao.readItem();
				
				for(Items items : itemService)
				{
					if(ord.getItems().getItemId() == items.getItemId())
					{
						Oldqty = items.getQuantity();
					}
				}
				

				ord.getItems().setQuantity(Oldqty + ord.getQuantity());
		
				serviceDao.modifyQuantity(ord.getItems());
				
			}
			out.println("Order cancel succesfully");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		}else{
			out.println("login first");
		}
 	}
*/
}
}
