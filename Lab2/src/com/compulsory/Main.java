package com.compulsory;


public class Main {

    public static void main(String[] args) {
	    Event c1 = new Event("C1", 8, 10, 100);
        Event c2 = new Event("C2", 10, 12, 100);
        Event l1 = new Event("L1", 8, 10, 30);
        Event l2 = new Event("L2", 8, 10, 30);
        Event l3 = new Event("L3", 10, 12, 30);

        Room room401 = new Room("401", RoomType.LAB, 30);
        Room room403 = new Room("404", RoomType.LAB, 30);
        Room room405 = new Room("405", RoomType.LAB, 30);
        Room room309 = new Room("309", RoomType.LECTURE_HALL, 100);

        System.out.println("The created events are: ");
        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(l1.toString());
        System.out.println(l2.toString());
        System.out.println(l3.toString());

        System.out.println("The created rooms are: ");
        System.out.println(room401.toString());
        System.out.println(room403.toString());
        System.out.println(room405.toString());
        System.out.println(room309.toString());
    }
}
