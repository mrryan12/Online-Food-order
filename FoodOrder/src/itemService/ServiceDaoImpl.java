package itemService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import itemDao.ItemDaoImpl;
import items.Items;

public class ServiceDaoImpl implements ServiceDao {
	
	private ItemDaoImpl itemDao = new ItemDaoImpl();

	static final Logger LOGGER = Logger.getLogger(ServiceDaoImpl.class); 
	
	public int insertItem(Items itemDetails) throws ClassNotFoundException, SQLException {
		int count =0; 
		try
		{
			LOGGER.info("inside insertItem in Service Layer");
		count  = itemDao.insertItem(itemDetails);
		}catch(Exception e)
		
		{
			throw e;
		}
		return count;
		
	}


	public int updateItem(Items itemDetails) throws ClassNotFoundException, SQLException {
		int count= 0 ;
		try
		{
			LOGGER.info("inside updateItem in Service Layer");
		count = itemDao.updateItem(itemDetails);
		}catch(Exception e)
		{
			throw e;
		}
		return count; 		
	}


	public int deleteItem(Items itemDetails) throws ClassNotFoundException, SQLException {
		int count = 0;
		try
		{
			LOGGER.info("inside DeleteItem in Service Layer");
		count = itemDao.deleteItem(itemDetails);
		}catch(Exception e)
		{
			throw e;
		}
		return count; 		
	}


	public List<Items> readItem() throws ClassNotFoundException, SQLException {
		
		List<Items> item = new ArrayList<>();
		try
		{
			LOGGER.info("inside showItem in Service Layer");
		item = itemDao.readItem();
		}catch(Exception e)
		{
			throw e;
		}
		return item;
	}


	
	public void modifyQuantity(Items items) throws ClassNotFoundException, SQLException {
		try
		{
			LOGGER.info("inside modifyQuantityItem in Service Layer");
		itemDao.modifyQuantity(items);
		}catch(Exception e)
		{
			throw e;
		} 
		
	}



	public String showItemsLike(String str) throws ClassNotFoundException, SQLException {
		String name = null;
		try{
			LOGGER.info("inside showItemsLike in Service Layer");
			name = itemDao.showItemsLike(str);
		}catch(Exception e)
		{
			throw e;
		}
		return name;
	}

}
