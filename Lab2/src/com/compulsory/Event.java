package com.compulsory;


public class Event
{
	private String name;
	private int start;
	private int end;
	private int size;

	public Event() { }

	public Event(String name) {
		this.name = name;
	}

	public Event(String name, int start, int end, int size)
	{
		this.name = name;
		this.start = start;
		this.end = end;
		this.size = size;
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
	public String toString() {
		return name + "(size=" + size + ", start=" + start + ", end=" + end + ")";
	}
}
