import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
	private static final String URL = "jdbc:mysql://localhost/PA";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static Connection connection = null;

	private Database() {}

	public static Connection getConnection() throws SQLException
	{
		if (connection == null)
			createConnection();
		return connection;
	}

	private static void createConnection() throws SQLException
	{
		connection  = DriverManager.getConnection(URL, USER, PASSWORD);
		connection.setAutoCommit(false);
	}

	public static void closeConnection()
	{
		try
		{
			connection.close();
		} catch (SQLException exception)
		{
			System.err.println("Couldn't close the connection to the database...");
			exception.printStackTrace();
		}
	}
}
