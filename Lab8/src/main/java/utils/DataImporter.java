package utils;

import db_object.City;
import db_object.Continent;
import db_object.Country;
import db_object_dao.CityDAO;
import db_object_dao.ContinentDAO;
import db_object_dao.CountryDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DataImporter
{
	private static final ContinentDAO continentDAO = ContinentDAO.getInstance();
	private static final CountryDAO countryDAO = CountryDAO.getInstance();
	private static final CityDAO cityDAO = CityDAO.getInstance();
	private static final List<String> unknownValues = List.of(new String[]{"N/A", "NULL", ""});

	private DataImporter() {}

	//reading the file line by line and split the data by "," and import data from there
	public static void importCSV(String filename) throws SQLException
	{
		String line;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
		{
			//ignoring the line containing the file structure
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null)
			{
				String[] lineData = line.split(",");
				importLine(lineData);
			}

			Database.getConnection().commit();
			System.out.println("The data from: " + TextStylization.BOLD_ON + filename + TextStylization.BOLD_OFF + " was imported successfully!");
		} catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}

	//how the data in the .csv file looks:
	//CountryName,CapitalName,CapitalLatitude,CapitalLongitude,CountryCode,ContinentName
	private static void importLine(String[] line) throws SQLException
	{
		//get the continent id and create the continent if it doesn't already exist
		if (unknownValues.contains(line[5]))
			return;
		line[5] = line[5].replace('\'', '`');
		Continent continent = continentDAO.findByName(line[5]);
		if (continent == null)
		{
			continent = new Continent(-1, line[5]);
			continentDAO.create(continent);
		}

		//create the country if it doesn't already exist
		if (unknownValues.contains(line[0]))
			return;
		line[0] = line[0].replace('\'', '`');
		if (!countryExists(line[0], line[4]))
			countryDAO.create(new Country(-1, line[0], line[4], continentDAO.findByName(line[5]).getId()));

		//create the city if the country's capital is mentioned in .csv file,
		//and it verifies the unique(name,country) constraint
		line[1] = line[1].replace('\'', '`');
		if (unknownValues.contains(line[1]) || !isCityNew(line[1], line[0]))
			return;
		City city = new City(-1, line[1], countryDAO.findByName(line[0]).getId(), true,
				Float.parseFloat(line[2]), Float.parseFloat(line[3]));
		cityDAO.create(city);
	}

	private static boolean countryExists(String name, String code) throws SQLException
	{
		return countryDAO.findByName(name) != null || countryDAO.findByCode(code) != null;
	}

	private static boolean isCityNew(String cityName, String countryName) throws SQLException
	{
		List<City> cities = cityDAO.findListByName(cityName);
		Country country = countryDAO.findByName(countryName);

		if (cities.isEmpty())
			return true;

		for (City city : cities)
			if (city.getCountry() == country.getId())
				return false;

		return true;
	}
}