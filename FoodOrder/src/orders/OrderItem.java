package orders;

import items.Items;

public class OrderItem {
	
	
	private int quantity;
	private Items  items;
	
	public OrderItem(int quantity,Items items)
	{
	this.quantity = quantity;
	this.items = items;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setItems(Items items) {
		this.items = items;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public Items getItems()
	{
		return items;
	}
	public double getAmountForItem()
	{
		double total =0 ;
		total += this.quantity*items.getItemPrice();
		return total;
	}


}
