package com.lab11_requests.entities;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable
{	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
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