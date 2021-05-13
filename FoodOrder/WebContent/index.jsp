
<%@page import="utility.DbUtility"%>
<%@page import="itemDao.ItemDaoImpl"%>
<%@page import="items.Items"%>
<%@page import="java.util.List"%>
<%@page import="itemService.ServiceDaoImpl"%>
<%@ page import="java.sql.*"%>

 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
<%-- <%  
String s=request.getParameter("val");  
if(s==null || s.trim().equals("")){  
out.print("Please enter id");  
}else{  
int id=Integer.parseInt(s);  
out.print(id);  
try{  
ServiceDaoImpl service = new ServiceDaoImpl(); 
List<Items> item = service.readItem();

for(Items items : item)
{
	if(items.getItemId() == id)
	{
		out.print(items.getItemId()+" "+items.getItemName());
	}
}
 
}catch(Exception e){e.printStackTrace();}  
}  
%>  --%>
<%-- 
<% 
   String str = request.getParameter("q")+"%";
try{  
ServiceDaoImpl service = new ServiceDaoImpl(); 
ItemDaoImpl dao = new ItemDaoImpl();

String sql ="select * from items where itemName like '"+str+"'";
try {
	Connection connection = DbUtility.getDbConnection();
	Statement statement = connection.createStatement();
	ResultSet rs = statement.executeQuery(sql);
	
	while(rs.next())
	{%>
		<tr><td><%=rs.getString("itemName") %></td></tr> <% 
	}
	
} catch (ClassNotFoundException | SQLException e) {
	e.printStackTrace();
}
 
}catch(Exception e){e.printStackTrace();}  

%> --%>


<%
   String str = request.getParameter("q")+"%"; 
   ServiceDaoImpl service = new ServiceDaoImpl(); 
   String name =service.showItemsLike(str);

   %>
	<tr><td><%= name %></td></tr> 


</body>
</html>