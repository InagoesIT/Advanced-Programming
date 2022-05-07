package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "continents")
@NamedQueries({
		@NamedQuery(name = "Continent.findAll",
				query = "select continent from Continent continent order by continent.name"),
		@NamedQuery(name = "Continent.findByName",
				query = "select continent from Continent continent where continent.name=?1"),
})

public class Continent implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
	@Column(name = "id")
	protected int id;

	@Column(name = "name")
	protected String name;

	public Continent(){}

	public Continent(int id, String name)
	{
		this.id = id;
		if (name.equals("N/A") || name.equals("NULL") || name.equals(""))
			name = null;
		this.name = name;
	}

	public Continent(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (name.equals("N/A") || name.equals("NULL") || name.equals(""))
			name = null;
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
