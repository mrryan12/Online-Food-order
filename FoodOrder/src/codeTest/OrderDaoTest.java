package codeTest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import customer.Customer;
import items.Items;
import orderService.OrderServiceImpl;
import orders.Order;
import orders.OrderItem;

public class OrderDaoTest {
	
	  public static Order order = new Order();
	  public static OrderServiceImpl service = new OrderServiceImpl();
	@Test
	public void ainsertOrder() throws ClassNotFoundException, SQLException
	{
	  
	   
	   Customer customer = new Customer();
	   Items items = new Items();
	   
	   OrderItem orderItem = new OrderItem(2, items);
	   double price = orderItem.getAmountForItem();
	   List<OrderItem> orderItems = new ArrayList<>();
	   orderItems.add(orderItem);
	   
	   items.setItemId(1);
	   customer.setCustomerId(2);
	     
	   
	    order.setCustomer(customer);
	    order.setOrderItem(orderItems);
	    order.setPrice(price);
	    
	   int count = service.insertOrder(order);
	   
	   assertEquals(1,count);
		
	}
	
	
	@Test
	public void dcancelOrderTest() throws ClassNotFoundException, SQLException
	{
		  order.setOrderId(90);
		  
		  int count = service.cancelOrder(order);
		  
		  assertEquals("Cancel order successfully", 1, count);
	}
	
	
	
	@Test
	public void bshowOrder() throws ClassNotFoundException, SQLException
	{
		
		  
		  List<Order> orders = service.showOrder(1);
		  int flag = 0;
		  
		  for(Order order1: orders)
		  {
			  if(order1.getOrderId() == 70)
			  {
				  flag =1;
			  }
		  }
		  
		  assertEquals("show order successfully", 1, flag);
	}
	   
	@Test
	public void cshowOrderByOrderId() throws ClassNotFoundException, SQLException
	{
				  
		  Order orders = service.showOrderByOrderId(70);
		
		  int id = orders.getOrderId();
		  assertEquals("ShowOrderByOrderId successfully", 70, id);
	}
}
