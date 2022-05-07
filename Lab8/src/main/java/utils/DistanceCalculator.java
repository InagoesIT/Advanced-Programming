package utils;

import db_object.City;
import db_object_dao.CityDAO;

import java.sql.SQLException;
import java.util.List;

public class DistanceCalculator
{
	private DistanceCalculator() {}

	public static void displayDistanceBetweenCities() throws SQLException
	{
		List<City> cities = CityDAO.getInstance().getAllCities();
		if (cities.isEmpty())
		{
			System.out.println(TextStylization.BOLD_ON + "There are no cities to show distances for." + TextStylization.BOLD_OFF);
			return;
		}

		City city1;
		City city2;
		System.out.println(TextStylization.BOLD_ON + "The distances between the cities are:" + TextStylization.BOLD_OFF);

		//comparing a city with another city only once
		for (int i = 0; i < cities.size(); i++)
		{
			city1 = cities.get(i);
			for (int j = i + 1; j < cities.size(); j++)
			{
				city2 = cities.get(j);
				System.out.println(TextStylization.BOLD_ON + city1.getName() + TextStylization.BOLD_OFF + " and " +
						TextStylization.BOLD_ON + city2.getName() + TextStylization.BOLD_OFF + ": " +
						distance(city1.getLatitude(), city1.getLongitude(), city2.getLatitude(), city2.getLongitude())
						+ " kilometers.");
			}
		}
	}

	public static double distance(double latitude1, double longitude1,
	                              double latitude2, double longitude2)
	{
		longitude1 = Math.toRadians(longitude1);
		longitude2 = Math.toRadians(longitude2);
		latitude1 = Math.toRadians(latitude1);
		latitude2 = Math.toRadians(latitude2);

		// Haversine formula
		double differenceInLongitude = longitude2 - longitude1;
		double differenceInLatitude = latitude2 - latitude1;
		double exprFromSqrt = Math.pow(Math.sin(differenceInLatitude / 2), 2)
				+ Math.cos(latitude1) * Math.cos(latitude2)
				* Math.pow(Math.sin(differenceInLongitude / 2), 2);
		double earthRadius = 6371;
		return 2 * earthRadius * Math.asin(Math.sqrt(exprFromSqrt));
	}
}
