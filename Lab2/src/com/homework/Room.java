package com.homework;

import java.util.Objects;

public abstract class Room
{
	protected String name;
	protected int capacity;

	protected Room()
	{
	}

	protected Room(String name)
	{
		this.name = name;
	}

	protected Room(String name, int capacity)
	{
		this.name = name;
		this.capacity = capacity;
	}

	public abstract String getType();

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	@Override
	public String toString()
	{
		return name + "(cap=" + capacity + ")";
	}

	@Override
	public boolean equals(Object object)
	{
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;

		Room room = (Room) object;
		return name.equals(room.getName());
	}
}