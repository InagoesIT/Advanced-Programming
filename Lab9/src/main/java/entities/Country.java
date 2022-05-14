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
		@NamedQuery(name = "Country.findById",
				query = "select country from Country country where country.id=?1"),
		@NamedQuery(name = "Country.findByCode",
				query = "select country from Country country where country.code=?1"),
})

public class Country extends AbstractEntity implements Serializable
{
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "continent")
	private Continent continent;

	private String code;

	public Country() {super();}

	public Country(Integer id, String name, String code, Continent continent)
	{
		super(id, name);
		this.name = name;
		if (code.equals("NULL"))
			code = null;
		this.code = code;
		this.continent = continent;
	}

	public Continent getContinent()
	{
		return continent;
	}

	public void setContinent(Continent continent)
	{
		this.continent = continent;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
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
