package db_object;

public class Continent extends DBObject
{
	public Continent(int id, String name)
	{
		super(id, name);
	}

	@Override
	public String toString()
	{
		return "DBObject.Continent{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
