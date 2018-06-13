package by.epam.trainingrpa.mainclasses;

import by.epam.trainingrpa.furniture.*;


public class Main {
    public static void main(String[] args) {
        Building building = new Building("Building 1");
        building.addRoom(new Room("Room 1", 80, 0));
        building.getRoom("Room 1").addFurniture(new Table("Table 1", 3));
        building.getRoom("Room 1").addFurniture(new Bed("Bed 1", 5, 3));
        building.getRoom("Room 1").addFurniture(new SoftChair("SoftChair 1", 4, 2));
        building.getRoom("Room 1").addFurniture(new Cupboard("Cupboard 1", 4));
        building.getRoom("Room 1").addLamp(new Lamp("Lamp 1", 200));
        building.addRoom(new Room("Room 2", 100, 2));
        building.getRoom("Room 2").addFurniture(new Table("Table 2", -5));
        building.getRoom("Room 2").addFurniture(new Bed("Bed 2", 6, 3));
        building.getRoom("Room 2").addFurniture(new SoftChair("SoftChair 2", 5, 2));
        building.getRoom("Room 2").addFurniture(new Cupboard("Cupboard 2", 8));
        building.getRoom("Room 2").addLamp(new Lamp("Lamp 2", 200));
        building.getRoom("Room 2").addLamp(new Lamp("Lamp 3", 500));
        building.addRoom(new Room("Room 3", 20, 1));
        building.getRoom("Room 3").addFurniture(new Table("Table 3", 19));
        building.getRoom("Room 3").addLamp(new Lamp("Lamp 3", -20));
        System.out.println(building.validate());
    }
}
