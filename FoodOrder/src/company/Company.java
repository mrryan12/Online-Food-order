package company;

import java.util.ArrayList;
import java.util.List;

import customer.Customer;
import items.Items;

public class Company {

	private String name;
	private List<Customer> customers = new ArrayList<>();
	private List<Items> items = new ArrayList<>();
	
	public Company(String name)
	{
		this.name = name;
	}
	
	public void addCustomer(Customer customer)
	{
		customers.add(customer);
	}
	public void addItems(Items item)
	{
		items.add(item);
	}

	public String getName() {
		return name;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<Items> getItems() {
		return items;
	}
	
	public double getTotalWorthOfOrdersPlaced()
	{
		double total =0;
		for(Customer customer:customers)
		{
		total += customer.getTotalAmountForCustomer();
		}
		return total;
	}
}
