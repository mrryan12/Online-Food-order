
<%@page import="items.Items"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item List</title>
<link rel="stylesheet" href="./Resorces/css/ItemList.css">

</head>
<body>

<%List<Items> items = (List)request.getAttribute("items"); %>


<div align="center">

	<table  class="content-table">
		<thead>
			<tr>
				<td>Item ID</td>
				<td>Item Name</td>
				<td>Item Type</td>
				<td>Item Price</td>
				<td>Item Description</td>
				<td>Item Quantity</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>
		</thead>
		
		<tbody>
		
			<%for(Items item : items) {%>
			<tr>
				<td><%= item.getItemId()%></td>
				<td><%= item.getItemName()%></td>
				<td><%= item.getItemType()%></td>
				<td><%= item.getItemPrice()%></td>
				<td><%= item.getItemDescription()%></td>
				<td><%= item.getQuantity() %></td>
			    <td><a href="EditItem.html">edit</a></td>   
				<td><a href="AddItemServlet?itemId=<%= item.getItemId() %>">delete</a></td>	
				</tr>
				<%} %>
		    
		</tbody>
	
	</table>
	
    
    <br><br> <button class="btn"><a class="btn-a" href="AdminDashboard.html">Back</a></button>
</div>

</body>
</html>