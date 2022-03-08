package com.homework;

import java.util.ArrayList;

public class Solution
{
	private Room[] assignment;
	// assignment[i] = what room received the event with index i
	private ArrayList<Event> events;

	public Solution(int eventsNr, ArrayList<Event> events)
	{
		assignment = new Room[eventsNr];
		this.events = new ArrayList<>(events);
	}

	public int computeUsedRooms()
	{
		int sum = 0;
		for (Room room : assignment)
			if (room != null) sum++;
		return sum;
	}

	public Room getAssignment(int index)
	{
		return assignment[index];
	}

	public int getSize()
	{
		return assignment.length;
	}

	public void assignRoom(int eventIndex, Room room)
	{
		assignment[eventIndex] = room;
	}

	public void displaySolution()
	{
		for (int i = 0; i < assignment.length; i++)
		{
			System.out.println(events.get(i).getName() + " -> " + assignment[i].getName());
		}
	}

	public boolean hasRoom(int eventIndex)
	{
		return (assignment[eventIndex] != null);
	}
}
