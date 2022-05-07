package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@NamedQueries({
		@NamedQuery(name = "City.findAll",
				query = "select city from City city order by city.name"),
		@NamedQuery(name = "City.findByCountry",
				query = "select city from City city where city.country=?1"),
		@NamedQuery(name = "City.findByName",
				query = "select city from City city where city.name=?1"),
})

public class City implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
	@Column(name = "id")
	protected int id;

	@Column(name = "name")
	protected String name;

	@Column(name = "country")
	private int country;

	@Column(name = "capital")
	private boolean capital;

	@Column(name = "latitude")
	private float latitude;

	@Column(name = "longitude")
	private float longitude;

	public City() {}

	public City(int id, String name, int country, boolean capital, float latitude, float longitude)
	{
		this.id = id;
		if (name.equals("N/A") || name.equals("NULL") || name.equals(""))
			name = null;
		this.name = name;
		this.country = country;
		this.capital = capital;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public void setName(String name)
	{
		if (name.equals("N/A") || name.equals("NULL") || name.equals(""))
			name = null;
		this.name = name;
	}

	public void setCountry(int country)
	{
		this.country = country;
	}

	public void setCapital(boolean capital)
	{
		this.capital = capital;
	}

	public void setLatitude(float latitude)
	{
		this.latitude = latitude;
	}

	public void setLongitude(float longitude)
	{
		this.longitude = longitude;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
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
	public String toString()
	{
		return "City{" +
				"country=" + country +
				", capital=" + capital +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
