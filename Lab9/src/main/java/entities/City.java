package entities;

import org.chocosolver.solver.variables.Variable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@NamedQueries({
		@NamedQuery(name = "City.findAll",
				query = "select city from City city order by city.name"),
		@NamedQuery(name = "City.findByCountry",
				query = "select city from City city where city.country=?1"),
		@NamedQuery(name = "City.findById",
				query = "select city from City city where city.id=?1"),
		@NamedQuery(name = "City.findByName",
				query = "select city from City city where city.name=?1"),
})

public class City extends AbstractEntity implements Serializable
{
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "country")
	private Country country;

	private boolean capital;
	private float latitude;
	private float longitude;
	private int population;

	public City() {
		super();
	}

	public City(Integer id, String name, Country country, boolean capital, float latitude, float longitude)
	{
		super(id, name);
		this.name = name;
		this.country = country;
		this.capital = capital;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public Country getCountry()
	{
		return country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
	}

	public void setPopulation(int population)
	{
		this.population = population;
	}

	public int getPopulation()
	{
		return population;
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
