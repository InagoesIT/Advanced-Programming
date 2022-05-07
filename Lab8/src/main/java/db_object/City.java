package db_object;

import java.util.Objects;

public class City extends DBObject
{
	private final int country;
	private final boolean capital;
	private final float latitude;
	private final float longitude;

	public City(int id, String name, int country, boolean capital, float latitude, float longitude)
	{
		super(id, name);
		//System.out.println("CITY FUCKING NAME: "+ name);
		this.country = country;
		this.capital = capital;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getCountry()
	{
		return country;
	}

	public boolean isCapital()
	{
		return capital;
	}

	public float getLatitude()
	{
		return latitude;
	}

	public float getLongitude()
	{
		return longitude;
	}

	@Override
	public boolean equals(Object object)
	{
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		City city = (City) object;
		return id == city.id && Objects.equals(name, city.name)
				&& country == city.country && capital == city.capital
				&& Float.compare(city.latitude, latitude) == 0 && Float.compare(city.longitude, longitude) == 0;
	}

	@Override
	public String toString()
	{
		return "DBObject.DBObject.City{" +
				"country=" + country +
				", capital=" + capital +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
