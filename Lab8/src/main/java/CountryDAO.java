import java.sql.*;

public class CountryDAO
{
	int maxId = 0;

	public void create(String name, int continentId) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"insert into countries (id, name, continent) values (?,?,?)"))
		{
			preparedStatement.setInt(1, maxId++);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, continentId);
			preparedStatement.executeUpdate();
		} catch (SQLException exception)
		{
			System.out.println("Couldn't add the country with the name = " + name
					+ " with the continent id = " + continentId);
		}
	}

	public Integer findByName(String name) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select id from countries where name='" + name + "'"))
		{
			return resultSet.next() ? resultSet.getInt(1) : -1;
		} catch (SQLException exception)
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
				     "select name from countries where id='" + id + "'"))
		{
			return resultSet.next() ? resultSet.getString(1) : "";
		} catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}

	public String findByContinent(int id) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select name from countries where continent='" + id + "'"))
		{
			StringBuilder result = new StringBuilder();
			while (resultSet.next())
			{
				result.append(resultSet.getString(1));
				result.append(", ");
			}
			if (result.length() == 0)
				return "";
			if (result.charAt(result.length() - 1) == ' ')
				return result.substring(0, result.length() - 2);
		} catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return "";
	}
}
