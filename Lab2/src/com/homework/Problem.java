package com.homework;

import java.util.ArrayList;
import java.util.List;

public class Problem
{
	private ArrayList<Event> events;
	private ArrayList<Room> rooms;

	public Problem()
	{
		events = new ArrayList<>();
		rooms = new ArrayList<>();
	}

	public boolean addEvent(Event newEvent)
	{
		for (Event event : events)
			if (newEvent.equals(event))
				return false;

		events.add(newEvent);
		return true;
	}

	public boolean addRoom(Room newRoom)
	{
		for (Room room : rooms)
			if (newRoom.equals(room))
				return false;

		rooms.add(newRoom);
		return true;
	}

	public int getEventsNr()
	{
		return events.size();
	}

	public int getRoomsNr()
	{
		return rooms.size();
	}

	public Event getEventWithIndex(int index)
	{
		return new Event(events.get(index));
	}

	public ArrayList<Event> getEvents()
	{
		return new ArrayList<>(events);
	}

	public Room getRoomWithIndex(int index)
	{
		if (rooms.get(index).getType().equals("LAB"))
			return new LabRoom(rooms.get(index));
		else
			return new CourseRoom(rooms.get(index));
	}

	public int getIndexOfEvent(Event event)
	{
		for (int i = 0; i < events.size(); i++)
		{
			if (event.equals(events.get(i)))
				return i;
		}
		return -1;
	}

	public ArrayList<Room> getRooms()
	{
		return new ArrayList<>(rooms);
	}

	// sorting according to ending time
	public ArrayList<Event> getSortedEvents()
	{
		ArrayList<Event> sortedEvents = new ArrayList<>(events);
		for (int i = 0; i < sortedEvents.size() - 1; i++)
			if (sortedEvents.get(i).getEnd() > sortedEvents.get(i + 1).getEnd())
			{
				Event eventTemp = new Event(sortedEvents.get(i));
				sortedEvents.set(i, sortedEvents.get(i + 1));
				sortedEvents.set(i + 1, eventTemp);
			}
		return sortedEvents;
	}

	// sorting according to size
	public ArrayList<Room> getSortedRooms()
	{
		ArrayList<Room> sortedRooms = new ArrayList<>(rooms);
		for (int i = 0; i < sortedRooms.size() - 1; i++)
			if (sortedRooms.get(i).getCapacity() > sortedRooms.get(i + 1).getCapacity())
			{
				if (sortedRooms.get(i).getType().equals("LAB"))
				{
					LabRoom roomTemp = new LabRoom(sortedRooms.get(i));
					sortedRooms.set(i, sortedRooms.get(i + 1));
					sortedRooms.set(i + 1, roomTemp);
				} else
				{
					CourseRoom roomTemp = new CourseRoom(sortedRooms.get(i));
					sortedRooms.set(i, sortedRooms.get(i + 1));
					sortedRooms.set(i + 1, roomTemp);
				}
			}
		return sortedRooms;
	}
}
