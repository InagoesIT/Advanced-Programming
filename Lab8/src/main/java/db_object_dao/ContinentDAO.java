package db_object_dao;

import db_object.Continent;
import utils.Database;

import java.sql.*;

//the class can't be instanced, so we will be able to use only the public methods
//the public methods have a body to show the behavior in case further instantiation
//will be desired
public class ContinentDAO implements DBObjectDAO<Continent>
{
	private static ContinentDAO instance = null;

	private ContinentDAO(){}

	public static ContinentDAO getInstance()
	{
		if (instance == null)
			instance = new ContinentDAO();
		return instance;
	}

	@Override
	public void create(Continent continent) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"insert into continents (name) values (?);"))
		{
			preparedStatement.setString(1, continent.getName().replace('\'', '"'));
			preparedStatement.executeUpdate();
		}
		catch (SQLException exception)
		{
			System.out.println("Couldn't add the continent with the name = " + continent.getName());
			System.out.println("The reason: " + exception);
		}
	}

	@Override
	public Continent findByName(String name) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select id from continents where name='" + name + "'"))
		{
			Continent continent = null;
			if (resultSet.next())
				continent = new Continent(resultSet.getInt(1), name);
			return continent;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	public Continent findById(int id) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select name from continents where id='" + id + "'"))
		{
			Continent continent = null;
			if (resultSet.next())
				continent = new Continent(id, resultSet.getString(1));
			return continent;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}
}
