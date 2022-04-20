import java.sql.*;

public class ContinentDAO
{
	int maxId = 0;

	public void create(String name) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"insert into continents (id, name) values (?,?)"))
		{
			preparedStatement.setInt(1, maxId++);
			preparedStatement.setString(2, name);
			preparedStatement.executeUpdate();
		}
		catch (SQLException exception)
		{
			System.out.println("Couldn't add the continent with the name = " + name);
		}
	}

	public Integer findByName(String name) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select id from continents where name='" + name + "'"))
		{
			return resultSet.next() ? resultSet.getInt(1) : -1;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}

	public String findById(int id) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select name from continents where id='" + id + "'"))
		{
			return resultSet.next() ? resultSet.getString(1) : "";
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}
}
