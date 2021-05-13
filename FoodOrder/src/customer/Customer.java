package customer;

import java.util.ArrayList;
import java.util.List;

import orders.Order;

public class Customer {
  
	private int customerId;

	private String customerName;
	private String customerEmail;
	private String customerPassword;
	private String customerAddress;
    private List<Order> order = new ArrayList<>();
	
	public void placeOrder(Order orders)
	{
		order.add(orders);
	}
	
	public List<Order> getOrder() {
		return order;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	public double getTotalAmountForCustomer()
	{
		double total = 0;
		for(Order orders : order)
		{
		total += orders.getAmountForOrder();
		}
		return total;
	}
}
