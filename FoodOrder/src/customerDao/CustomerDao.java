package customerDao;

import java.sql.SQLException;
import java.util.List;

import customer.Customer;

public interface CustomerDao {
	int insertItem(Customer customer) throws ClassNotFoundException,SQLException;
	int updateItem(Customer customer) throws ClassNotFoundException,SQLException;
	int deleteItem(Customer customer)throws ClassNotFoundException,SQLException;
	List<Customer> readItem()throws ClassNotFoundException,SQLException;

}
