package db_object_dao;

import db_object.Country;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//the class can't be instanced, so we will be able to use only the public methods
//the public methods have a body to show the behavior in case further instantiation
//will be desired
public class CountryDAO implements DBObjectDAO<Country>
{
	private static CountryDAO instance = null;

	private CountryDAO(){};

	public static CountryDAO getInstance()
	{
		if (instance == null)
			instance = new CountryDAO();
		return instance;
	}

	@Override
	public void create(Country country) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"insert into countries (name, code, continent) values (?,?,?);"))
		{
			preparedStatement.setString(1, country.getName().replace('\'', '"'));
			preparedStatement.setString(2, country.getCode());
			preparedStatement.setInt(3, country.getContinent());
			preparedStatement.executeUpdate();
		} catch (SQLException exception)
		{
			System.out.println("Couldn't add the country with the name = " + country.getName());
			System.out.println("The reason: " + exception);
		}
	}

	@Override
	public Country findByName(String name) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select id, code, continent from countries where name='" + name + "'"))
		{
			Country country = null;
			if (resultSet.next())
				country = new Country(resultSet.getInt(1), name,
						resultSet.getString(2), resultSet.getInt(3));
			return country;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	public Country findById(int id) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select (name, code, continent) from countries where id='" + id + "'"))
		{
			Country country = null;
			if (resultSet.next())
				country = new Country(id, resultSet.getString(1),
						resultSet.getString(2), resultSet.getInt(3));
			return country;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}

	public Country findByCode(String code) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select id, name, continent from countries where code='" + code + "'"))
		{
			Country country = null;
			if (resultSet.next())
				country = new Country(resultSet.getInt(1), resultSet.getString(2),
						code, resultSet.getInt(2));
			return country;
		}
		catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return null;
	}

	public List<Country> findByContinent(int continentId) throws SQLException
	{
		Connection connection = Database.getConnection();
		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(
				     "select id, name, code from countries where continent id='" + continentId + "'"))
		{
			List<Country> countries = new ArrayList<>();
			while (resultSet.next())
			{
				Country country = new Country(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getString(3), continentId);
				countries.add(country);
			}
			return countries;

		} catch (SQLException exception)
		{
			System.err.println("An error occured at the database level...");
			exception.printStackTrace();
		}
		return new ArrayList<>();
	}
}
