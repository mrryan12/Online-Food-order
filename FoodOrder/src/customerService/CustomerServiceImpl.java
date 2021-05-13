package customerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import customer.Customer;
import customerDao.CustomerDaoImpl;

public class CustomerServiceImpl implements CustomerService {
	static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class); 

	private CustomerDaoImpl customerdao = new CustomerDaoImpl();

	public int insertItem(Customer customer) throws ClassNotFoundException, SQLException {
		int count=0;
		try
		{	
			LOGGER.info("inside insertCustomer in Service class");
			count =  customerdao.insertItem(customer);
		}catch(Exception e)
		{
			LOGGER.info("Throwing error while inserting customerData in service Layer " +e);
			throw e;
		}
		return count;

	}

	public int updateItem(Customer customer) throws ClassNotFoundException, SQLException {
		int count=0;
		try
		{
			LOGGER.info("inside updateCustomer in Service class");
			count =  customerdao.updateItem(customer);
		}catch(Exception e)
		{
			LOGGER.info("Throwing error while updating customerData in service Layer " +e);
			throw e;
		}
		return count;
	}


	public int deleteItem(Customer customer) throws ClassNotFoundException, SQLException {
		int count=0;
		try
		{
			LOGGER.info("inside deleteCustomer in Service class");
			count = customerdao.deleteItem(customer);
		}catch(Exception e)
		{
			LOGGER.info("Throwing error while delete customerData in service Layer " +e);
			throw e;
		}
		return count;
	}


	public List<Customer> readItem() throws ClassNotFoundException, SQLException {
		List<Customer> customers = new ArrayList<>();
		try
		{
			LOGGER.info("inside showCustomer in Service class");
			customers=customerdao.readItem();
		}catch(Exception e)
		{
			LOGGER.info("Throwing error while showing customerData in service Layer " +e);
			throw e;
		}
		return customers;
	}

}
