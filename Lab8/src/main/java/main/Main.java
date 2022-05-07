package main;

import db_object.Continent;
import db_object.Country;
import db_object_dao.ContinentDAO;
import db_object_dao.CountryDAO;
import utils.DataImporter;
import utils.Database;
import utils.DistanceCalculator;
import utils.TextStylization;

import java.sql.SQLException;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			// for COMPULSORY
//			Continent europe = demonstrateContinents();
//
//			if (europe == null)
//				System.err.println("Didn't find a continent with name Europe");
//			else
//				demonstrateCountries(europe);

			//HOMEWORK
			//import capitals of the world with their associated continent and countries
			// from the .csv dataset
			DataImporter.importCSV("src/main/resources/world_capitals.csv");
			System.out.println(TextStylization.SEPARATOR);
			DistanceCalculator.displayDistanceBetweenCities();

			Database.closeConnection();
		} catch (SQLException sqlException)
		{
			System.err.println(sqlException);
			try
			{
				Database.getConnection().rollback();
			} catch (SQLException exception)
			{
				exception.printStackTrace();
			}
		}
	}

	//create a continent and then retrieve it from the DB
	//also, try to retrieve a non-existent object from DB
	public static Continent demonstrateContinents() throws SQLException
	{
		ContinentDAO.getInstance().create(new Continent(0, "Europe"));
		Database.getConnection().commit();
		Continent europe1Found;
		Continent europeFound;

		try
		{
			europe1Found = ContinentDAO.getInstance().findByName("Europe1");
			System.out.println("The continent with the name Europe1 from db is: " + europe1Found);
		} catch (NullPointerException exception)
		{
			System.out.println("Didn't find a continent with name Europe1");
		}

		try
		{
			europeFound = ContinentDAO.getInstance().findByName("Europe");
			System.out.println("The continent with the name Europe from db is: " + europeFound);
			return europeFound;
		} catch (NullPointerException exception)
		{
			System.out.println("Didn't find a continent with name Europe");
		}
		return null;
	}

	//create two countries that are from the given continent
	//then, retrieve all the countries which are from the given continent
	public static void demonstrateCountries(Continent continent) throws SQLException
	{
		try
		{
			CountryDAO.getInstance().create(new Country(0, "Romania", "RO", continent.getId()));
			CountryDAO.getInstance().create(new Country(1, "Republic of Moldova", "MD", continent.getId()));

			Database.getConnection().commit();

			System.out.println("All the countries in Europe are: " + CountryDAO.getInstance().findByContinent(continent.getId()));
		} catch (NullPointerException exception)
		{
			System.out.println("Didn't find a continent with name Europe1");
		}
	}
}
