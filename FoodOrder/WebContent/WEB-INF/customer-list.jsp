<%@page import="java.util.List"%>
<%@page import="customer.Customer"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer List</title>
<link rel="stylesheet" href="./Resorces/css/ItemList.css">

</head>
<body>

<% List<Customer> customersList = (List)request.getAttribute("customers"); %>

<div align="center">
   <table class="content-table">
      <thead>
         <tr>
               <td>Customer ID</td>
				<td>Customer Name</td>
				<td>Customer Email</td>
				<td>Customer Address</td>
				<td>Delete</td>
         </tr>  
      </thead>
      
      
      <tbody>
      <% for(Customer customers : customersList){ %>
                <tr>
                          <td><%= customers.getCustomerId() %></td>
                          <td><%= customers.getCustomerName() %></td>
                          <td><%= customers.getCustomerEmail() %></td>
                          <td><%= customers.getCustomerAddress() %></td>
                          <td><a href="DeleteCustomerServlet?customerId=<%= customers.getCustomerId() %>">delete</a></td>
             
                </tr>
          <% } %>
      </tbody>
   
   
   </table>
   </br></br> <button class="btn"><a class="btn-a"href="AdminDashboard.html">back</a></button>

</div>
</body>
</html>