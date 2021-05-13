package codeTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import customer.Customer;
import customerService.CustomerServiceImpl;
import itemService.ServiceDaoImpl;
import items.Items;

public class CustomerDaoTest {
	
	CustomerServiceImpl service = new CustomerServiceImpl();;
	Customer customer = new Customer() ;
	static int id;
	
	{
		customer.setCustomerName("arvind");
		customer.setCustomerEmail("arvind123@gamil.com");
		customer.setCustomerPassword("arvind@123");
		customer.setCustomerAddress("Lucknow");
	}	
	
	@Test
	public void insertCustomerTest() throws ClassNotFoundException, SQLException
	{

		int insertId = service.insertItem(customer);
     	assertEquals("insert method success", 1, insertId);
	}

	@Test
	public void readCustomerTestSucess() throws ClassNotFoundException, SQLException
	{
		List<Customer> customers = service.readItem();
		int flag = 0;
		for(Customer customer1 : customers)
		{
			if(customer1.getCustomerEmail().equals(customer.getCustomerEmail()))
			{
				id = customer1.getCustomerId();
				flag =1;
			}
		}
		
		assertEquals("customer read method successfully",1, flag);
	}
	
	@Test
	public void updateCustomerTestSuccess() throws ClassNotFoundException, SQLException
	{	

		customer.setCustomerId(11);
		customer.setCustomerName("arvindVerma");
		customer.setCustomerEmail("arvindVerma@gamil.com");
		customer.setCustomerPassword("arvind@123");
		customer.setCustomerAddress("Lucknow");

		int insertId = service.updateItem(customer);
        System.out.println(insertId + "update customer test");
		assertEquals("update method success", 1, insertId);

	}
			
	@Test
	public void deleteCustomerTestSuccess() throws ClassNotFoundException, SQLException {

		customer = new Customer();
		service = new CustomerServiceImpl();
		customer.setCustomerId(id);
		int count =  service.deleteItem(customer);
		assertEquals(1, count);
	}

	@Test
	public void deleteCustomerTestFail() throws ClassNotFoundException, SQLException {
		customer = new Customer();
		service = new CustomerServiceImpl();
		customer.setCustomerId(id);
		int count =  service.deleteItem(customer);
		assertEquals(0, count);
	}
/*	@Test
	public void updateCustomerTestFail() throws ClassNotFoundException, SQLException
	{customer = new Customer();
	service = new CustomerServiceImpl();

	customer.setCustomerId(11);
	customer.setCustomerName("rohini");
	customer.setCustomerEmail("rohini@gamil.com");
	customer.setCustomerPassword("rohini@123");
	customer.setCustomerAddress("delhi");

	int insertId = service.updateItem(customer);

	assertEquals("update method fail", 0, insertId);
	}




	@Test
	public void deleteCustomerTestFail() throws ClassNotFoundException, SQLException {
		customer = new Customer();
		service = new CustomerServiceImpl();
		customer.setCustomerId(13);
		int count =  service.deleteItem(customer);
		assertEquals(0, count);
	}


	@Test
	public void readCustomerTestFail() throws ClassNotFoundException, SQLException
	{
		customer = new Customer();
		service = new CustomerServiceImpl();

		List<Customer> customers = service.readItem();
		int flag = 0;
		for(Customer customer1 : customers)
		{
			if(customer1.getCustomerId() == 1)
			{
				flag =1;
			}
		}

		assertEquals("customer read method Fail",1, flag);
	}*/

}
