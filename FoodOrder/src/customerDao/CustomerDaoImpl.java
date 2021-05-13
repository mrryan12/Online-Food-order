package customerDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import org.apache.log4j.Logger;

import controller.AddItemServlet;
import customer.Customer;
import utility.DbUtility;

public class CustomerDaoImpl implements CustomerDao {


	private Connection connection= null;
	private Statement statement = null;

	static final Logger LOGGER = Logger.getLogger(CustomerDaoImpl.class); 

	public int insertItem(Customer customer) throws ClassNotFoundException, SQLException {

		LOGGER.info("inside insertCustomer in DAO class");
		int count = 0;
		try
		{

			String name = customer.getCustomerName();
     		String Email = customer.getCustomerEmail();
			String password = customer.getCustomerPassword();
			String address =customer.getCustomerAddress();
			int id = customer.getCustomerId();

			String query = "insert into customer (CustomerId,Name,Email,Password,Address) values " +"("+id+",'"+name+"','"+Email+"','"+password+"','"+address+"')";

			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();

			count = statement.executeUpdate(query);

			if (count > 0) {
				LOGGER.info("Customer Data Successfully inserted in database name = "+name);
				System.out.println("\n--------------------------------------------------------------------------");
				System.out.println("\nCustomer Register Succesfully" +" Id -"+id +"\tName - "+name+"\tpassword - "+password);
				System.out.println("\n--------------------------------------------------------------------------");

			} else {
				LOGGER.info("OOps something went wrong while inserting customerData");
				System.out.println("OOPS something went wrong we are on process");
			}

		}catch(Exception e)
		{
			LOGGER.info("Throwing error while inserting customerData " +e);
			throw e;
		}finally {
			DbUtility.getDbConnectionClose();
		}
		return count;


	}


	public int updateItem(Customer customer) throws ClassNotFoundException, SQLException {
		int count = 0;
		try
		{
			LOGGER.info("Inside UpdateCustomer in Dao class");
			String name = customer.getCustomerName();
			String email = customer.getCustomerEmail();
			String password = customer.getCustomerPassword();
			String Address =customer.getCustomerAddress();
			int id = customer.getCustomerId();
            System.out.println(id+"from dao");
			String query = "update customer set Name = '"+name+"', Email ='"+email+"',Password = '"+password+"'"
					+ " ,Address = '"+Address+"' where CustomerId = "+id;
			
			
			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();

			count = statement.executeUpdate(query);
			System.out.println("inside update" + count);
			if (count > 0) {
				LOGGER.info(" UpdateCustomer successfully");
				System.out.println("\n--------------------------------------------------------------------------");
				System.out.println("\nCustomer Details Updated Succesfully");
				System.out.println("\n--------------------------------------------------------------------------");		
				} else {
					LOGGER.info("OOPS something went wrong while updating customer");
					System.out.println("OOPS something went wrong we are on process");
				}

		}catch(Exception e)
		{
			LOGGER.info(" Throwing error while updating customerData " +e);
			throw e;
		}finally {
			DbUtility.getDbConnectionClose();
		}
		return count;		
	}

	public int deleteItem(Customer customer) throws ClassNotFoundException, SQLException {
		int rowsCountUpdate = 0;
		try
		{
			LOGGER.info("inside DeleteCustomer method in DAO of Customer");
			int id = customer.getCustomerId();
			String query = "delete from customer where CustomerId ="+id;
			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			rowsCountUpdate = statement.executeUpdate(query);
			if(rowsCountUpdate>0)
			{
				LOGGER.info("Delete Customer Successfully with id = "+id);
				System.out.println("Customer Deleted Successfully");
			}else
			{
				LOGGER.info(" OOPs something went wrong while delete customer");
				System.out.println("OOPs something went wrong|| we are on process");
			}
		}catch(Exception e)
		{
			LOGGER.info(" Throwing error while delete customerData " +e);	
			throw e;
		}
		finally
		{
			DbUtility.getDbConnectionClose();
		}
		return rowsCountUpdate;
	}


	public List<Customer> readItem() throws ClassNotFoundException, SQLException {

		List<Customer> customers = new ArrayList<>();
		try
		{	
			LOGGER.info("inside showCustomer method in DAO of Customer");
			String query = "select * from customer";
			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			ResultSet resultset =  statement.executeQuery(query);

			while(resultset.next())
			{
				Customer customer = new Customer();

				customer.setCustomerId(resultset.getInt("CustomerId"));
				customer.setCustomerName(resultset.getString("Name"));
				customer.setCustomerEmail(resultset.getString("Email"));
				customer.setCustomerPassword(resultset.getString("Password"));
				customer.setCustomerAddress(resultset.getString("Address"));


				customers.add(customer);

			}
		}catch(Exception e)
		{
			LOGGER.info(" Throwing error while showing customerData " +e);
		}finally
		{
			DbUtility.getDbConnectionClose();
		}
		return customers;
	}



}
