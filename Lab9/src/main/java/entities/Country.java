package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries")
@NamedQueries({
		@NamedQuery(name = "Country.findAll",
				query = "select country from Country country order by country.name"),
		@NamedQuery(name = "Country.findByContinent",
				query = "select country from Country country where country.continent=?1"),
		@NamedQuery(name = "Country.findByName",
				query = "select country from Country country where country.name=?1"),
})

public class Country implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
	@Column(name = "id")
	protected int id;

	@Column(name = "name")
	protected String name;

	@Column(name = "code")
	private String code;

	@Column(name = "continent")
	private int continent;

	public Country(){
	}

	public Country(int id, String name, String code, int continent)
	{
		this.id = id;
		if (name.equals("N/A") || name.equals("NULL") || name.equals(""))
			name = null;
		this.name = name;
		this.code = code;
		this.continent = continent;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		if (name.equals("N/A") || name.equals("NULL") || name.equals(""))
			name = null;
		this.name = name;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public void setContinent(int continent)
	{
		this.continent = continent;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
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
		return "Country{" +
				"code='" + code + '\'' +
				", continent=" + continent +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
