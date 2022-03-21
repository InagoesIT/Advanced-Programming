import java.util.*;

public class City
{
	private Map<Intersection, List<Street>> cityMap;

	City(List<Intersection> intersections)
	{
		cityMap = new HashMap<>();
		this.intersections = intersections;
	}

	public List<Intersection> getIntersections()
	{
		return intersections;
	}

	public void setIntersections(List<Intersection> intersections)
	{
		this.intersections = intersections;
	}

	private List<Intersection> intersections;

	public boolean addStreetsToIntersection(Intersection intersection, List<Street> streets)
	{
		if (intersections.contains(intersection))
		{
			cityMap.put(intersection, streets);
			return true;
		}
		return false;
	}

	public List<Street> getStreetsLongerThan(int length)
	{
		Set<Street> result = new HashSet<>();
		cityMap.values().stream().toList().forEach(streets ->
				streets.stream().filter(street -> street.getLength() > length &&
						(getIntersectedNrOfStreets(street.getIntersection1())
								+ getIntersectedNrOfStreets(street.getIntersection2())) >= 5)
						.forEach(result::add));

		return new ArrayList<>(result);
	}

	public int getIntersectedNrOfStreets(Intersection intersection)
	{
		if (cityMap.get(intersection) == null)
			return 0;
		return cityMap.get(intersection).size();
	}
}
