package db_object_dao;

import db_object.City;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//the class can't be instanced, so we will be able to use only the public methods
//the public methods have a body to show the behavior in case further instantiation
//will be desired
public class CityDAO implements DBObjectDAO<City>
{
	private static CityDAO instance = null;

	private CityDAO(){}

	public static CityDAO getInstance()
	{
		if (instance == null)
			instance = new CityDAO();
		return instance;
	}

	@Override
	public void create(City city) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"insert into cities (name, country, capital, latitude, longitude) values (?,?,?,?,?);"))
		{
			preparedStatement.setString(1, city.getName());
			preparedStatement.setInt(2, city.getCountry());
			preparedStatement.setBoolean(3, city.isCapital());
			preparedStatement.setFloat(4, city.getLatitude());
			preparedStatement.setFloat(5, city.getLongitude());

			preparedStatement.executeUpdate();
		}
		catch (SQLException exception)
		{
			System.out.println("Couldn't add the city with the name = " + city.getName());
			System.out.println("The reason: " + exception);
		}
	}

	@Override
	public City findByName(String name) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select * from cities where name='" + name + "';"))
		{
			City city = null;
			if (resultSet.next())
				city = new City(resultSet.getInt(1), name,
						resultSet.getInt(3), resultSet.getBoolean(4),
						resultSet.getFloat(5), resultSet.getFloat(6));
			return city;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}

	public List<City> findListByName(String name) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select * from cities where name='" + name + "';"))
		{
			List<City> cities = new ArrayList<>();
			while (resultSet.next())
				cities.add(new City(resultSet.getInt(1), name,
						resultSet.getInt(3), resultSet.getBoolean(4),
						resultSet.getFloat(5), resultSet.getFloat(6)));
			return cities;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public City findById(int id) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select name, country, capital, latitude, longitude from cities where id='" + id + "'"))
		{
			City city = null;
			if (resultSet.next())
				city = new City(id, resultSet.getString(1),
						resultSet.getInt(2), resultSet.getBoolean(3),
						resultSet.getFloat(4), resultSet.getFloat(5));
			return city;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}

	public List<City> getAllCities() throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select * from cities"))
		{
			List<City> cities = new ArrayList<>();
			while (resultSet.next())
				cities.add(new City(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getInt(3), resultSet.getBoolean(4),
						resultSet.getFloat(5), resultSet.getFloat(6)));
			return cities;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}
}
