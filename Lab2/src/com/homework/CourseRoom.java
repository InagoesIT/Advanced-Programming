package com.homework;

public class CourseRoom extends Room
{
	boolean hasAProjector;

	CourseRoom(String name, int capacity, boolean hasAProjector)
	{
		super(name, capacity);
		this.hasAProjector = hasAProjector;
	}

	public CourseRoom(Room room)
	{
		super(room.getName(), room.getCapacity());
	}

	public void setHasAProjector(boolean hasAProjector)
	{
		this.hasAProjector = hasAProjector;
	}

	public boolean isHasAProjector()
	{
		return hasAProjector;
	}

	public String getType()
	{
		return "COURSE";
	}
}
