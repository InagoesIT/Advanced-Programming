package com.homework;

public class Main
{
	public static void main(String[] args)
	{
		Event c1 = new Event("C1", 8, 10, 100);
		Event c2 = new Event("C2", 10, 12, 100);
		Event l1 = new Event("L1", 8, 10, 30);
		Event l2 = new Event("L2", 8, 10, 30);
		Event l3 = new Event("L3", 10, 12, 30);

		LabRoom room401 = new LabRoom("401", 30, "Linux");
		LabRoom room403 = new LabRoom("403", 30, "DOS");
		LabRoom room405 = new LabRoom("405", 30, "Windows");
		CourseRoom room309 = new CourseRoom("309", 100, true);

		Problem problem = new Problem();
		problem.addEvent(c1);
		problem.addEvent(c2);
		problem.addEvent(l1);
		problem.addEvent(l2);
		problem.addEvent(l3);

		problem.addRoom(room309);
		problem.addRoom(room401);
		problem.addRoom(room403);
		problem.addRoom(room405);

		Algorithm greedyAlg = new GreedyAlgorithm(problem);
		Solution solution = greedyAlg.solve();
		solution.displaySolution();
	}
}
