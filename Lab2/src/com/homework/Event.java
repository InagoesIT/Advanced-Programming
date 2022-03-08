package com.homework;

import java.util.Objects;

public class Event
{
	private String name;
	private int start;
	private int end;
	private int size;

	public Event()
	{
	}

	public Event(String name)
	{
		this.name = name;
	}

	public Event(String name, int start, int end, int size)
	{
		this.name = name;
		this.start = start;
		this.end = end;
		this.size = size;
	}

	public Event(Event event)
	{
		this.name = event.getName();
		this.start = event.getStart();
		this.end = event.getEnd();
		this.size = event.getSize();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getEnd()
	{
		return end;
	}

	public void setEnd(int end)
	{
		this.end = end;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	@Override
	public String toString()
	{
		return name + "(size=" + size + ", start=" + start + ", end=" + end + ")";
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

		Event event = (Event) object;
		return Objects.equals(name, event.getName());
	}
}