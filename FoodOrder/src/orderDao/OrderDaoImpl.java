package orderDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import customer.Customer;
import customerService.CustomerServiceImpl;
import items.Items;
import orders.Order;
import orders.OrderItem;
import utility.DbUtility;

public class OrderDaoImpl implements OrderDao {

	private Connection connection =null;
	private Statement statement =null;

	static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class); 
	public int insertOrder(Order order) throws SQLException,ClassNotFoundException {

		int customerId = order.getCustomer().getCustomerId();
		double price = 0;
		int itemId =0 ;
		int qty =0 ;
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("inside insertOrder in DAO");
			List<OrderItem> orderItem = order.getOrderItem();
			for(OrderItem ordersItem : orderItem)
			{
				itemId  = ordersItem.getItems().getItemId();
				qty = ordersItem.getQuantity();
				price = ordersItem.getAmountForItem();
			}

			System.out.println(customerId+" "+itemId+ " "+qty + " "+price );

			String query = "insert into orders(customerId,itemId,itemQty,totalPrice) values"+" ("+customerId+","+itemId+","+qty+","+price+")";
			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			rowsUpdateCount = statement.executeUpdate(query);
			if (rowsUpdateCount > 0) {
				LOGGER.info(" InsertOrder successfully in database");
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("\nItem Register Succesfully");
				System.out.println("\n--------------------------------------------------------------------------");
			} else {
				System.out.println("OOPS something went wrong we are on process");
			}
		} catch(Exception e)
		{
			LOGGER.info("Throwing error while inserting order " +e);
			throw e;
		}finally{
			DbUtility.getDbConnectionClose();
		}
		return rowsUpdateCount;
	}


	public List<Order> showOrder(int id) throws SQLException, ClassNotFoundException {

		List<Order> orderDetails = new ArrayList<>();

		try{
			
			LOGGER.info("inside showOrder in DAO");
			String sql = "	SELECT orders.orderID, customer.Name, items.itemName ,orders.itemQty, orders.totalPrice	FROM ((orders"
	        + "	INNER JOIN customer ON orders.CustomerId = Customer.CustomerId) "
				 + "INNER JOIN items ON orders.itemId = items.itemId) where orders.customerId= "+id;
				

			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			ResultSet rs =  statement.executeQuery(sql);	
			
			if(rs != null)
			{
				LOGGER.info(" ShowOrder successfully in database");
			}
			while(rs.next())
			{
				
				
				Order orders = new Order();
				Customer customer = new Customer();
				Items item = new Items();

				customer.setCustomerName(rs.getString("Name"));
				item.setItemName(rs.getString("itemName"));
				item.setQuantity(rs.getInt("itemQty"));
				orders.setOrderId(rs.getInt("orderID"));
				orders.setPrice(rs.getDouble("totalPrice"));

				OrderItem orderItem1 = new OrderItem(rs.getInt("itemQty"), item);

				List<OrderItem> orderItem = new ArrayList<>();
				orderItem.add(orderItem1);

				orders.setCustomer(customer);
				orders.setOrderItem(orderItem);


				orderDetails.add(orders);

				


			}
		

		}catch(Exception e){
			LOGGER.info("Throwing error while Show order " +e);
			throw e;
		}finally{
			DbUtility.getDbConnectionClose();
		}
		return orderDetails;
	}

	
	

	public int cancelOrder(Order order) throws SQLException, ClassNotFoundException
	{
		LOGGER.info("inside CancelOrder in DAO");
		int id= order.getOrderId();
		int rowsCountUpdate= 0;
		try{
			String query = "delete from orders where orderId ="+id;
			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			rowsCountUpdate = statement.executeUpdate(query);
			if(rowsCountUpdate>0)
			{
				LOGGER.info(" CancelOrder successfully in database");
				System.out.println("\nItem Deleted Successfully");
			}else
			{
				System.out.println("OOPs something went wrong|| we are on process");
			}		
		}catch(Exception e){
			LOGGER.info("Throwing error while Cancel order " +e);
			throw e;
		}finally{
			DbUtility.getDbConnectionClose();
		}
		return rowsCountUpdate;
	}



	public Order showOrderByOrderId(int id) throws SQLException, ClassNotFoundException {
		
		Order orders = new Order();
		try{
			LOGGER.info("inside showOrderByOrderId in DAO");
        	String query = "select * from orders where orderId ="+id;
			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			ResultSet rs =  statement.executeQuery(query);
			
			if(rs != null)
			{
				LOGGER.info(" ShowOrderByOrderId successfully in database");
			}
			
			while(rs.next())
			{
				
				Customer customer = new Customer();
				Items item = new Items();

				customer.setCustomerId(rs.getInt("customerId"));
				item.setItemId(rs.getInt("itemId"));
				item.setQuantity(rs.getInt("itemQty"));
				orders.setOrderId(rs.getInt("orderID"));
				orders.setPrice(rs.getDouble("totalPrice"));

				OrderItem orderItem1 = new OrderItem(rs.getInt("itemQty"), item);

				List<OrderItem> orderItem = new ArrayList<>();
				orderItem.add(orderItem1);

				orders.setCustomer(customer);
				orders.setOrderItem(orderItem);


				


			}



		}catch(Exception e){
			LOGGER.info("Throwing error while Show orderBy ID " +e);
			throw e;
		}finally{
			DbUtility.getDbConnectionClose();
		}
		return orders;
	}

	
	
}
