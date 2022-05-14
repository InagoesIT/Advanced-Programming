package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "continents")
@NamedQueries({
		@NamedQuery(name = "Continent.findAll",
				query = "select continent from Continent continent order by continent.name"),
		@NamedQuery(name = "Continent.findById",
				query = "select continent from Continent continent where continent.id=?1"),
		@NamedQuery(name = "Continent.findByName",
				query = "select continent from Continent continent where continent.name=?1"),
})

public class Continent extends AbstractEntity implements Serializable
{
	public Continent(){super();}

	public Continent(Integer id, String name)
	{
		super(id, name);
	}

	public Continent(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Continent{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
