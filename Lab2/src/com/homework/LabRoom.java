package com.homework;

public class LabRoom extends Room
{
	private String operatingSystem;

	LabRoom(String name, int capacity, String operatingSystem)
	{
		super(name, capacity);
		this.operatingSystem = String.copyValueOf(operatingSystem.toCharArray());
	}

	public LabRoom(Room room)
	{
		super(room.getName(), room.getCapacity());
	}

	public void setOperatingSystem(String operatingSystem)
	{
		this.operatingSystem = operatingSystem;
	}

	public String getOperatingSystem()
	{
		return operatingSystem;
	}

	public String getType()
	{
		return "LAB";
	}
}
