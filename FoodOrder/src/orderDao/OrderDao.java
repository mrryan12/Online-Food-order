package orderDao;

import java.sql.SQLException;
import java.util.List;

import orders.Order;

public interface OrderDao {
    
	int insertOrder(Order order) throws SQLException,ClassNotFoundException; 
	List<Order> showOrder(int id) throws SQLException,ClassNotFoundException;
	int cancelOrder(Order order) throws SQLException, ClassNotFoundException;
	Order showOrderByOrderId(int id) throws SQLException,ClassNotFoundException;

}
