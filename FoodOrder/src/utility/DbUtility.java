package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import orderDao.OrderDaoImpl;

public class DbUtility {
	
	public static final String url = "jdbc:mysql://localhost:3306/food";
	public static final String userName = "root";
	public static final String password = "";
	public static Connection connection = null;

	static final Logger LOGGER = Logger.getLogger(DbUtility.class); 
	public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		LOGGER.info("driver class Load Succeesfully");
		connection = DriverManager.getConnection(url, userName, password);
		LOGGER.info("Connection established");
		return connection;
	}
	public static void getDbConnectionClose() throws SQLException
	{
       if(connection != null)
    	   {
    	   LOGGER.info("Connection closed");
    	   connection.close();	
    	   }
	}

}
