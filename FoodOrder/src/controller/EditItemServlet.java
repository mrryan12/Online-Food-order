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
import utility.IdGenration;

@WebServlet("/EditItemServlet")
public class EditItemServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(EditItemServlet.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		if(session != null)
		{
			int id = Integer.parseInt(request.getParameter("modifyitemid"));
			String name = request.getParameter("itemname");
			String type = request.getParameter("itemtype");
			double price = Double.valueOf(request.getParameter("itemprice"));
			String description = request.getParameter("itemdetails");
			int qty = Integer.parseInt(request.getParameter("quantity")); 

			ServiceDaoImpl service = new ServiceDaoImpl();

			Items item= new Items();
			item.setItemId(id);
			item.setItemName(name);
			item.setItemType(type);
			item.setItemPrice(price);
			item.setItemDescription(description);
			item.setQuantity(qty);
			int count =0;

			try {				
				count++;
				service.updateItem(item);
				LOGGER.info("Data Updated successfully");
				out.print("data updated succesfully");
				RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.html");
				dispatcher.include(request, response);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			if(count ==0)
			{
				LOGGER.info("Id not found");
				out.println("<h2>Id not found</h2>");
				request.setAttribute("id", id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("ModifyItem.html");
				dispatcher.include(request, response);
			}

		}else
			LOGGER.info("Login first");
			out.println("Login First");
	}


}
