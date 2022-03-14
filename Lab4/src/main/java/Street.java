public class Street implements Comparable<Street>
{
	private String name;
	private int length;
	private Intersection[] intersections;

	public Street(String name, int length, Intersection[] intersections)
	{
		this.name = name;
		this.length = length;
		this.intersections = intersections;
	}

	public Street(String name, int length)
	{
		this.name = name;
		this.length = length;
	}

	public Intersection[] getIntersections()
	{
		Intersection[] intersectionsAux = new Intersection[2];
		intersectionsAux[0] = new Intersection(this.intersections[0]);
		intersectionsAux[1] = new Intersection(this.intersections[1]);
		return intersectionsAux;
	}

	public void setIntersections(Intersection[] intersections)
	{
		this.intersections = intersections;
	}

	public Intersection getIntersection1()
	{
		return new Intersection(intersections[0]);
	}

	public Intersection getIntersection2()
	{
		return new Intersection(intersections[1]);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	@Override
	public int compareTo(Street o)
	{
		return Integer.compare(this.getLength(), o.getLength());
	}

	@Override
	public String toString()
	{
		return this.name + " length: " + this.length;
	}
}
