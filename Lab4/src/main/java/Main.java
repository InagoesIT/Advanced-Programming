import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		// creating all the streets
		Street street1 = new Street("street1", 2);
		Street street2 = new Street("street2", 2);
		Street street3 = new Street("street3", 2);
		Street street4 = new Street("street4", 2);
		Street street5 = new Street("street5", 1);
		Street street6 = new Street("street6", 3);
		Street street7 = new Street("street7", 3);
		Street street8 = new Street("street8", 2);
		Street street9 = new Street("street9", 2);
		Street street10 = new Street("street10", 1);
		Street street11 = new Street("street11", 3);
		Street street12 = new Street("street12", 1);
		Street street13 = new Street("street13", 1);
		Street street14 = new Street("street14", 1);
		Street street15 = new Street("street15", 1);
		Street street16 = new Street("street16", 2);

		// creating a list of intersection names
		List<String> intersectionNames = new ArrayList<>();
		for (int i = 0; i < 9; i++)
			intersectionNames.add("intersection" + i);

		// creating intersections using the names created above
		List<Intersection> intersections = intersectionNames.stream().map(Intersection::new).toList();

		// setting the intersections for every street
		street1.setIntersections(new Intersection[]{intersections.get(0), intersections.get(1)});
		street2.setIntersections(new Intersection[]{intersections.get(0), intersections.get(3)});
		street3.setIntersections(new Intersection[]{intersections.get(0), intersections.get(2)});
		street4.setIntersections(new Intersection[]{intersections.get(1), intersections.get(3)});
		street5.setIntersections(new Intersection[]{intersections.get(2), intersections.get(3)});
		street6.setIntersections(new Intersection[]{intersections.get(1), intersections.get(5)});
		street7.setIntersections(new Intersection[]{intersections.get(2), intersections.get(4)});
		street8.setIntersections(new Intersection[]{intersections.get(3), intersections.get(4)});
		street9.setIntersections(new Intersection[]{intersections.get(3), intersections.get(7)});
		street10.setIntersections(new Intersection[]{intersections.get(5), intersections.get(4)});
		street11.setIntersections(new Intersection[]{intersections.get(4), intersections.get(8)});
		street12.setIntersections(new Intersection[]{intersections.get(7), intersections.get(8)});
		street13.setIntersections(new Intersection[]{intersections.get(7), intersections.get(6)});
		street14.setIntersections(new Intersection[]{intersections.get(6), intersections.get(8)});
		street15.setIntersections(new Intersection[]{intersections.get(5), intersections.get(6)});
		street16.setIntersections(new Intersection[]{intersections.get(5), intersections.get(8)});

		// creating a Linked List with some Streets
		List<Street> streetsLL = new LinkedList<>();
		addStreets(street1, street2, street3, street11, street5, street13, street7, street15, streetsLL);

		streetsLL.sort((Street street1Param, Street street2Param) -> street1Param.compareTo(street2Param));

		// creating a HashSet with all the intersections
		Set<Intersection> intersectionSet = new HashSet<>(intersections);

		// verifying if we can add the same element twice
		if (intersectionSet.add(intersections.get(3)))
			System.out.println("SENSATION! THE SET ACCEPTS DUPLICATES!");
		else
			System.out.println("The set already has this element, so it didn't add it, it doesn't have duplicates.");
	}

	private static void addStreets(Street street1, Street street2, Street street3, Street street4, Street street5, Street street6, Street street7, Street street8, List<Street> streetsLL)
	{
		streetsLL.add(street1);
		streetsLL.add(street2);
		streetsLL.add(street3);
		streetsLL.add(street4);
		streetsLL.add(street5);
		streetsLL.add(street6);
		streetsLL.add(street7);
		streetsLL.add(street8);
	}
}
