package orderService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import orderDao.OrderDao;
import orderDao.OrderDaoImpl;
import orders.Order;

public class OrderServiceImpl implements OrderService{

	private OrderDaoImpl orderDao = new OrderDaoImpl();

	public int insertOrder(Order order) throws SQLException, ClassNotFoundException {

		int count=0;
		try
		{
		count = orderDao.insertOrder(order);
		}catch(Exception e)
		{
			throw e;
		}
		return count;
	}


	public List<Order> showOrder(int id) throws SQLException, ClassNotFoundException {

		List<Order> orders = null;
		try{
			orders = orderDao.showOrder(id);
		}catch(Exception e)
		{
			throw e;
		}
		return orders;
	}



	public int cancelOrder(Order order) throws SQLException, ClassNotFoundException {
		int count = 0; 
		try{
			count = orderDao.cancelOrder(order);
		}catch(Exception e)
		{
			throw e;
		}
		return count;

	}
	
	public Order showOrderByOrderId(int id) throws SQLException,ClassNotFoundException{
		int count = 0;
		Order order =null;
		try{
			order = orderDao.showOrderByOrderId(id);
		}catch(Exception e)
		{
			throw e;
		}
		return order;
	}
    


}
