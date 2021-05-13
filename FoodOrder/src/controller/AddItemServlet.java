package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import itemService.ServiceDaoImpl;
import items.Items;
import logger.AddItemContext;

@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(AddItemServlet.class); 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	
		// logger
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

	    if(session != null)
		{
			String name = request.getParameter("itemname");
			String type = request.getParameter("itemtype");
			double price = Double.valueOf(request.getParameter("itemprice"));
			String description = request.getParameter("itemdesciptions");
            int quantity =Integer.parseInt(request.getParameter("quantity"));


			ServiceDaoImpl service = new ServiceDaoImpl();

			Items item= new Items();

			item.setItemName(name);
			item.setItemType(type);
			item.setItemPrice(price);
			item.setItemDescription(description);
			item.setQuantity(quantity);

			try {
				service.insertItem(item);
				LOGGER.info("Item inserted Successfully");
				out.println("<center><h2>Item inserted succesfully</h2></center>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.html");
				dispatcher.include(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				LOGGER.info("Oops! Item isertion failed");
				e.printStackTrace();
			}
		}else
		{
			out.println("Login First");
		}

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		
		HttpSession session = request.getSession(false);

		if(session != null)
		{

			int id= Integer.parseInt(request.getParameter("itemId"));
			System.out.println(id);
			ServiceDaoImpl service = new ServiceDaoImpl();



			if(id != 0)
			{
				try {
					List<Items> it = service.readItem();
					for(Items items : it)
					{
						if(items.getItemId() == id)
						{
							service.deleteItem(items);
							out.println("<center><h2>Item Deleted succesfully</h2></center>");
							RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.html");
							dispatcher.include(request, response);
						}
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		}else
			out.println("Login First");
	}

}
