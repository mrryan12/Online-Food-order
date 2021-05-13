<%@page import="java.util.List"%>
<%@page import="orders.Order"%>
<%@page import="orders.OrderItem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% List<Order> orders = (List)request.getAttribute("orderList"); %>

<div align="center">

	<table  class="content-table">
		<thead>
			<tr>
				<td>Order ID</td>
				<td>Customer Name</td>
				<td>Item Name</td>
				<td>Item Quntity</td>
				<td>Item Price</td>
				<td>cancel order</td>
			</tr>
		</thead>
		
		<tbody>
		
			<%for(Order order : orders) {%>
			<tr>
				<td><%= order.getOrderId() %></td>
				<td><%= order.getCustomer().getCustomerName()%></td>
				<% List<OrderItem> orderItem  = order.getOrderItem();%>
				<% for(OrderItem ordersItem : orderItem){ %>
				
				<td><%= ordersItem.getItems().getItemName()%></td>
				<td><%= ordersItem.getQuantity()%></td>
				<% } %>
				<td><%= order.getPrice()%></td>
				<td><a href="AddItemServlet?itemId=<%= order.getOrderId() %>">delete</a></td>	
				</tr>
				<%} %>
		    
		</tbody>
	
	</table>
	
    
    <br><br> <button class="btn"><a class="btn-a" href="AdminDashboard.html">Back</a></button>
</div>




</body>
</html>