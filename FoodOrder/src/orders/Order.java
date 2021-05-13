package orders;

import java.util.ArrayList;
import java.util.List;

import customer.Customer;

public class Order {
	private int orderId;
	private List<OrderItem> orderItem = new ArrayList<>();
    private Customer customer;
    private double price;
	
    
	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public void addOrderItems(OrderItem order)
	{
	  orderItem.add(order);	
	}
	
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getOrderId() {
		return orderId;
	}
	
	public List<OrderItem> getOrderItem()
	{
		return orderItem;
	}
	
	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}


	public double getAmountForOrder()
	{
		double total =0;
		for(OrderItem orderItems : orderItem)
		{
		total += orderItems.getAmountForItem();
		}
		return total;
	}


}
