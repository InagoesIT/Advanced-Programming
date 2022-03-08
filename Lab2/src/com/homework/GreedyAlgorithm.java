package com.homework;

import java.util.ArrayList;

public class GreedyAlgorithm extends Algorithm
{
	private final Solution solution;

	public GreedyAlgorithm(Problem problem)
	{
		super(problem);
		solution = new Solution(problem.getEventsNr(), problem.getEvents());
	}

	public boolean isRoomFree(Room room, int startTime)
	{
		// is doesn't have an event assigned at that time
		for (int i = 0; i < solution.getSize(); i++)
			if (solution.getAssignment(i) != null && solution.getAssignment(i).equals(room) &&
					problem.getEventWithIndex(i).getEnd() > startTime)
				return false;
		return true;
	}

	public Solution solve()
	{
		// implement greedy
		ArrayList<Event> sortedEvents = problem.getSortedEvents();
		ArrayList<Room> sortedRooms = problem.getSortedRooms();

		for (int i = 0; i < sortedEvents.size(); i++)
		{
			for (int j = 0; j < sortedRooms.size(); j++)
			{
				// if the room is free in that period of time and it can fit the event it's assigned to the event
				if (isRoomFree(sortedRooms.get(j), sortedEvents.get(i).getStart()) &&
						sortedEvents.get(i).getSize() <= sortedRooms.get(j).getCapacity())
				{
					solution.assignRoom(problem.getIndexOfEvent(sortedEvents.get(i)), sortedRooms.get(j));
					break;
				}
			}
		}
		return solution;
	}
}
