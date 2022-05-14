package utils;

import entities.City;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import repositories.CityRepository;

import javax.persistence.EntityManager;
import java.util.*;

public class ChocoSolverDemo
{
	private final int boundStart;
	private final int boundEnd;
	private final EntityManager entityManager;

	public ChocoSolverDemo(EntityManager entityManager, int boundStart, int boundEnd)
	{
		this.entityManager = entityManager;
		this.boundStart = boundStart;
		this.boundEnd = boundEnd;
	}

	public List<City> getResult()
	{
		Model model = new Model("Choco Solver: " + boundStart + boundEnd);
		IntVar boundStartModel = model.intVar(boundStart);
		IntVar boundEndModel = model.intVar(boundEnd);
		List<City> allCities = new CityRepository(entityManager).findAll();

		//get all the population nr of the cities
		//IT IS STORED AS A SET, NEEDS TO BE MADE INTO MULTIPLE SETS...
		IntVar[] populationNr = model.intVarArray(1, allCities.stream().mapToInt(City::getPopulation).toArray());

		System.out.println(Arrays.toString(populationNr));
		model.sum(populationNr, ">=", boundStartModel).post();
		model.sum(populationNr, "<=", boundEndModel).post();

		// Compute a solution by filtering repeatedly all the cities
		while (model.getSolver().solve())
		{
			List<City> citiesResult = filterCitiesForPopulation(allCities, Arrays.stream(populationNr).mapToInt(IntVar::getValue).toArray());
			System.out.println(citiesResult);
			if (haveSameLetter(citiesResult) && areFromDiffCountries(citiesResult))
				return citiesResult;
		}
		return Collections.emptyList();
	}

	//get the cities which have one of the values mentioned in populationNr
	private List<City> filterCitiesForPopulation(List<City> cities, int[] populationNr)
	{
		//find the corresponding city for the every population amount
		List<City> result = new ArrayList<>();
		Set<Integer> populationSet = new HashSet<>();

		//make a set out of the array in case that the population amount repeats itself
		//so, we optimise the time of the function
		for (int population : populationNr)
			populationSet.add(population);
		populationSet.forEach(population ->
				result.addAll(cities.stream().filter(city -> city.getPopulation() == population).toList()));

		return result;
	}

	private boolean haveSameLetter(List<City> cities)
	{
		char firstLetter = cities.get(0).getName().charAt(0);
		return cities.stream().noneMatch(city -> city.getName().charAt(0) != firstLetter);
	}

	private boolean areFromDiffCountries(List<City> cities)
	{
		//add all the countries to a set, and if it has the same size as the cities,
		//then they all have different country ids
		Set<Integer> countryIds = new HashSet<>();
		cities.stream().mapToInt(city -> city.getCountry().getId()).forEach(countryIds::add);

		return countryIds.size() == cities.size();
	}
}