package utils;

import entities.City;
import entities.Continent;
import entities.Country;
import repositories.CityRepository;
import repositories.ContinentRepository;
import repositories.CountryRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Timer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DataImporter
{
	private final EntityManager entityManager;
	private final CityRepository cityRepository;
	private final ContinentRepository continentRepository;
	private final CountryRepository countryRepository;
	private static final List<String> unknownValues = List.of(new String[]{"N/A", "NULL", ""});
	private static final String LOGGER_FILE = "/mnt/uni/courses/PA/lab/PA_2022_2B4_VIVDICI_INA/Lab9/src/main/resources/persist_log.txt";

	public DataImporter(EntityManager entityManager)
	{
		this.entityManager = entityManager;
		this.cityRepository = new CityRepository(this.entityManager);
		this.continentRepository = new ContinentRepository(this.entityManager);
		this.countryRepository = new CountryRepository(this.entityManager);
	}

	//reading the file line by line and split the data by "," and import data from there
	public void importCSV(String filePath)
	{
		String line;
		Duration duration = Duration.ZERO;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)))
		{
			//ignoring the line containing the file structure
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null)
			{
				String[] lineData = line.split(",");
				duration = duration.plus(importLine(lineData));
			}
			System.out.println("The data from: " + TextStylization.BOLD_ON + filePath + TextStylization.BOLD_OFF + " was imported successfully!");

			logExecutionTime(duration);
		} catch (IOException exception)
		{
			exception.printStackTrace();
			entityManager.getTransaction().rollback();
		}

	}

	//how the data in the .csv file looks:
	//CountryName,CapitalName,CapitalLatitude,CapitalLongitude,CountryCode,ContinentName
	//return the time this function needed for adding the needed cities, countries and continents
	private Duration importLine(String[] line)
	{
		//get the continent id and create the continent if it doesn't already exist
		if (unknownValues.contains(line[5]))
			return Duration.ZERO;

		Continent continent = continentRepository.findByName(line[5]);
		if (continent == null)
			continent = new Continent(null, line[5]);

		//create the country if it doesn't already exist
		if (unknownValues.contains(line[0]))
			return Duration.ZERO;

		Country country = findCountryByNameAndCode(line[0], line[4]);
		if (country == null)
			country = new Country(null, line[0], line[4], continent);

		//create the city if the country's capital is mentioned in .csv file,
		//and it verifies the unique(name,country) constraint
		//persist the city, and in cascade the country and continent if needed

		if (unknownValues.contains(line[1]) || !isCityNew(line[1], line[0]))
			return Duration.ZERO;

		Instant start = Instant.now();

		cityRepository.persist(new City(null, line[1], country, true,
				Float.parseFloat(line[2]), Float.parseFloat(line[3])));

		return Duration.between(start, Instant.now());
	}

	private Country findCountryByNameAndCode(String name, String code)
	{
		Country countryByName = countryRepository.findByName(name);
		if (countryByName != null)
			return countryByName;
		else
			return countryRepository.findByCode(code);
	}

	private boolean isCityNew(String cityName, String countryName)
	{
		List<City> cities = cityRepository.findListByName(cityName);
		Country country = countryRepository.findByName(countryName);

		if (cities.isEmpty())
			return true;

		for (City city : cities)
			if (city.getCountry() == country)
				return false;

		return true;
	}

	private static void logExecutionTime(Duration duration)
	{
		Logger logger = Logger.getLogger("Time logger");

		try
		{
			FileHandler fileHandler = new FileHandler(LOGGER_FILE, true);
			logger.addHandler(fileHandler);
			fileHandler.setFormatter(new SimpleFormatter());
			String logMessage = "The time needed for adding the cities was: " + duration.toMillisPart() + " milliseconds.";
			logger.info(logMessage);
			fileHandler.close();

		} catch (IOException | SecurityException exception)
		{
			exception.printStackTrace();
		}

	}
}