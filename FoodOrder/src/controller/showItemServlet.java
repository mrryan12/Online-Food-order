package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import itemService.ServiceDaoImpl;
import items.Items;

@WebServlet("/showItemServlet")
public class showItemServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(showItemServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  response.setContentType("text/html");
	      PrintWriter out = response.getWriter();


		HttpSession session=request.getSession(false);  
		if(session!=null)
		{
			ServiceDaoImpl service = new ServiceDaoImpl();
			List<Items> items = null;
			try {
				items = service.readItem();
				LOGGER.info("Show totals items");
				request.setAttribute("items", items);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			request.getRequestDispatcher("/WEB-INF/item-list.jsp").forward(request, response);

		}
		else
		{
			LOGGER.info("Please register first");
			out.println("Please register first");
		}



	}

	

}
