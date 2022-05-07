package db_object;

public abstract class DBObject
{
	protected final int id;
	protected final String name;

	protected DBObject(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}
}
