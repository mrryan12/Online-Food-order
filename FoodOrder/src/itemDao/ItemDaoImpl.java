package itemDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import customerService.CustomerServiceImpl;
import items.Items;
import utility.DbUtility;

public class ItemDaoImpl implements ItemDao {
	
	private static Connection connection = null;
	private static Statement statement = null;

	
	static final Logger LOGGER = Logger.getLogger(ItemDaoImpl.class); 

	// -------------------inserting record -------------------

	public int insertItem(Items itemDetails) throws ClassNotFoundException, SQLException {
		int rowsUpdateCount = 0;

		try {

			LOGGER.info("inside insertItem in DAO class");
			String name = itemDetails.getItemName();
			String type = itemDetails.getItemType();
			double price = itemDetails.getItemPrice();
			String description = itemDetails.getItemDescription();
			int qty = itemDetails.getQuantity();

			String query = "insert into Items (itemName,itemType,itemPrice,itemDetails,itemQuantity) values " + "('"
					+ name + "','" + type + "'," + price + ",'" + description + "',"+qty+")";

			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			rowsUpdateCount = statement.executeUpdate(query);
			if (rowsUpdateCount > 0) {
				LOGGER.info(" InsertCustomer successfully in database");
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("\nItem Register Succesfully" +"\n\n\tName - " +name+"\tPrice - "+price );
				System.out.println("\n--------------------------------------------------------------------------");
			} else {
				LOGGER.info("OOPS something went wrong while inserting customer");
				System.out.println("OOPS something went wrong we are on process");
			}
		} catch (Exception e) {
			LOGGER.info("Throwing error while inserting itemData " +e);
			throw e;
		} finally {
			DbUtility.getDbConnectionClose();
		}
		return rowsUpdateCount;
		
	}

	//------------updating record -------------------


	public int updateItem(Items itemDetails) throws ClassNotFoundException, SQLException {

		int rowsUpdateCount = 0;
		try
		{
			LOGGER.info("inside updateItem in DAO class");
			int id = itemDetails.getItemId();
			String name = itemDetails.getItemName();
			String type = itemDetails.getItemType();
			double price = itemDetails.getItemPrice();
			String description = itemDetails.getItemDescription();
			int qty = itemDetails.getQuantity();
			

			String query = "update items set itemName = '"+name+"' , itemType = '"+type+"',itemPrice = "
					+price+", itemDetails ='"+description+"',itemQuantity= "+qty+" where itemId = "+id;

			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			rowsUpdateCount = statement.executeUpdate(query);
			
			
			if (rowsUpdateCount > 0) {
				LOGGER.info(" UpdateCustomer successfully in database");
				System.out.println("\n--------------------------------------------------------------------------");
				System.out.println("Item Details Update Succesfully"  +"\nId - "+id +"\tName - " +name+"\tPrice - "+price +"\tqty" +qty);
				System.out.println("\n--------------------------------------------------------------------------");

			} else {
				LOGGER.info("OOPS something went wrong while updating customer");
				System.out.println("OOPS something went wrong we are on process");
			}

		}catch(Exception e)
		{
			LOGGER.info("Throwing error while updating itemData " +e);
			throw e;
		} finally {
			DbUtility.getDbConnectionClose();
		}
		return rowsUpdateCount;
	}



	// --------------------- Delete Query ----------------------


	public int deleteItem(Items itemDetails) throws ClassNotFoundException, SQLException {

		int rowsCountUpdate = 0;
		try
		{
			LOGGER.info("inside deleteItem in DAO class");
			int id = itemDetails.getItemId();
			String query = "delete from Items where itemId = "+id+"";
			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			 rowsCountUpdate = statement.executeUpdate(query);
			if(rowsCountUpdate>0)
			{
				LOGGER.info(" DeleteCustomer successfully in database");
				System.out.println("\nItem Deleted Successfully");
			}else			{
				LOGGER.info("OOPS something went wrong while delete customer");
				System.out.println("OOPs something went wrong|| we are on process");
			}
		}catch(Exception e)
		{
			LOGGER.info("Throwing error while delete itemData " +e);
			throw e;
		}
		finally
		{
			DbUtility.getDbConnectionClose();
		}
		return rowsCountUpdate;
	}

	
	// -------------------- reading details ----------
	
	public List<Items> readItem() throws ClassNotFoundException, SQLException {
		
	
		List<Items> item = new ArrayList<>();
         try
         {
             LOGGER.info("inside showItem in DAO class"); 
        	 String query = "select * from items";
        	 
        	 connection = DbUtility.getDbConnection();
        	 statement = connection.createStatement();
        	ResultSet resultset =  statement.executeQuery(query);
        	
        	 while(resultset.next())
        	 {
             	Items items = new Items();
             	
        	   items.setItemId(resultset.getInt("ItemId"));
        	   items.setItemName(resultset.getString("ItemName"));
        	   items.setItemType(resultset.getString("ItemType"));
        	   items.setItemPrice(resultset.getDouble("ItemPrice"));
        	   items.setItemDescription(resultset.getString("ItemDetails"));
        	   items.setQuantity(resultset.getInt("itemQuantity"));

        	   
        	   item.add(items);
        	    
        	 }
        	
         }catch(Exception e)
         {
        	 LOGGER.info("Throwing error while show itemData " +e);
        	 throw e;
         }finally
         {
        	 DbUtility.getDbConnectionClose();
         }
		 return item;
	}


	// ---------------Modify Quantity
	
	public void modifyQuantity(Items items) throws ClassNotFoundException, SQLException {
       
		int qty = items.getQuantity();
		int id = items.getItemId();
		
		try{
			String query="update items set itemQuantity ="+qty+" where itemId ="+id;
			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
		}catch(Exception e)
		{
			LOGGER.info("Throwing error while ModifyQuantity itemData " +e);
			throw e;
		}finally
        {
       	 DbUtility.getDbConnectionClose();
        }
	}
		
	public String showItemsLike(String str)
	{
		String name = null;
		String sql ="select * from items where itemName like '"+str+"'";
		try {
			connection = DbUtility.getDbConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				 name = rs.getString("itemName");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.info("Throwing error while showItemsLike itemData " +e);
			e.printStackTrace();
		}
		return name;
	}

}