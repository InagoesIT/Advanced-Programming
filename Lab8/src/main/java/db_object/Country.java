package db_object;

public class Country extends DBObject
{
	private final String code;
	private final int continent;

	public Country(int id, String name, String code, int continent)
	{
		super(id, name);
		if (code != null && code.equals("NULL"))
			code = null;
		this.code = code;
		this.continent = continent;
	}

	public String getCode()
	{
		return code;
	}

	public int getContinent()
	{
		return continent;
	}

	@Override
	public String toString()
	{
		return "DBObject.Country{" +
				"code='" + code + '\'' +
				", continent=" + continent +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
