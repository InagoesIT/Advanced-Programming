package entities;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
	protected Integer id;
	protected String name;

	protected AbstractEntity(){}

	protected AbstractEntity(Integer id, String name)
	{
		this.id = id;
		if (name.equals("N/A") || name.equals("NULL") || name.equals(""))
			name = null;
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(Integer id)
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
}
