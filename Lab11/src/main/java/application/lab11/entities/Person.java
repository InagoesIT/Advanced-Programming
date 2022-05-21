package application.lab11.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "persons")

public class Person implements Serializable
{
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@javax.persistence.Id
	@javax.persistence.Column(name = "id")
	private Integer id;
	@javax.persistence.Basic
	@javax.persistence.Column(name = "name")
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public boolean equals(Object object)
	{
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		Person person = (Person) object;
		return Objects.equals(id, person.id) && Objects.equals(name, person.name);
	}

	public Person(Integer id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public Person()
	{
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}