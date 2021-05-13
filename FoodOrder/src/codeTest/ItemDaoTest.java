package codeTest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;



import itemService.ServiceDaoImpl;
import items.Items;


public class ItemDaoTest {

	ServiceDaoImpl service = new ServiceDaoImpl();
	public static Items items = new Items();
	
    static int id;
	
	{
		items.setItemName("kurkure");
		items.setItemType("food");
		items.setItemPrice(10);
		items.setItemDescription("food");
		items.setQuantity(5);
	}
	
	@Test
	public void test1() throws ClassNotFoundException, SQLException
	{

		int count = service.insertItem(items);
		assertEquals("insertItemMethod test",1, count);
	}
	
	

	@Test
	public void test2() throws ClassNotFoundException, SQLException
	{
    
       List<Items> item = service.readItem();
       int flag = 0;
       
       for(Items item1 : item)
       {
    	   
    	   if(item1.getItemName().equals(items.getItemName()))
    	   {
    		   id = item1.getItemId();
    		   flag =1;
    	   }
       }
       
       assertEquals("Item read method successfully",1, flag);
	}
	
	
	@Test
	public void test3() throws ClassNotFoundException, SQLException
	{	
		
		items.setItemId(id);
		int count =  service.updateItem(items);
		assertEquals("itemUpdate Method",1, count);

	}
	
	@Test
	public void test4() throws ClassNotFoundException, SQLException {

		items.setItemId(id);
		int count =  service.deleteItem(items);
		assertEquals("itemDelete method",1, count);
	}
	
	
	
	/*

	@Test
	public void readItemTestFail() throws ClassNotFoundException, SQLException
	{
    
       List<Items> item = service.readItem();
       int flag = 0;
       for(Items item1 : item)
       {

    	   if(item1.getItemName().equals(items.getItemName()))
    	   {
    		   id = item1.getItemId();
    		   flag =1;
    	   }
       }
       
       assertEquals("Item read method Failed",0, flag);
	}
	
	

	
	@Test
	public void updateItemTestFail() throws ClassNotFoundException, SQLException
	{	items.setItemId(id);
	items.setItemName("kurkureTede");
	items.setItemType("food");
	items.setItemPrice(50);
	items.setItemDescription("food");
	items.setQuantity(10);
	
	int count = service.updateItem(items);
	
	assertEquals(0, count);


	}



	@Test
	public void deleteItemTestFail() throws ClassNotFoundException, SQLException {

		items = new Items();
		service = new ServiceDaoImpl();
		items.setItemId(id);
		int count =  service.deleteItem(items);
		assertEquals(0, count);
	}


*/



}
