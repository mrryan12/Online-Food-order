package itemService;

import java.sql.SQLException;
import java.util.List;

import items.Items;

public interface ServiceDao {

	int insertItem(Items itemDetails) throws ClassNotFoundException,SQLException;
	int updateItem(Items itemDetails) throws ClassNotFoundException,SQLException;
	int deleteItem(Items itemDetails)throws ClassNotFoundException,SQLException;
	List<Items> readItem()throws ClassNotFoundException,SQLException;
	void modifyQuantity(Items items) throws ClassNotFoundException,SQLException;
	String showItemsLike(String str) throws ClassNotFoundException,SQLException;
}
