package by.epam.trainingrpa.mainclasses;

import by.epam.trainingrpa.validator.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describes buildings.
 * Building has a name and rooms.
 * @version 1.0
 * @author Eugene
 * */
public class Building implements Validator{
    /**
     * This field is a name of the building
     * */
    final private String buildingName;
    /**
     * This field is a list of rooms in the building
     * */
    private List<Room> rooms;
    /**
     * This field is true, if this building passed the validation by Validator
     * @see Validator
     * */
    private boolean validated = false;

    /**
     * Constructor with a parameter <b>Name of the building</b>.
     * List of rooms will be empty
     * @param buildingName name of the building
     * */
    public Building(String buildingName) {
        this.buildingName = buildingName;
        this.rooms = new ArrayList<>();
    }
    /**
     * Constructor with parameters <b>Name of the building</b> and <b>List of rooms</b>.
     * @param buildingName name of the building
     * @param rooms list of rooms of the building
     * */
    public Building(String buildingName, List<Room> rooms) {
        this.buildingName = buildingName;
        this.rooms = rooms;
    }

    /**
     * Getter, that returns value of the field {@link Building#buildingName}
     * @return name of the building
     * */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Getter, that returns value of the field {@link Building#rooms}
     * @return list of rooms
     * */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Setter, that set a new value to the field {@link Building#rooms}
     * @param rooms a new list of rooms
     * */
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * This method is used to add a new room to the list of rooms
     * @param room a new room to add
     * */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * This method is used to remove the room to the list of rooms
     * @param room the room to remove
     * */
    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    /**
     * This method returns a room by its name
     * @param string name of the room for finding
     * @return found room or null
     * */
    public Room getRoom(String string) {
        for (Room room : this.getRooms()) {
            if (room.getRoomName().equals(string)) {
                return room;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.buildingName).append(": \n");
        for (Room room : this.getRooms()) {
            builder.append("\t").append(room);
        }
        return builder.toString();
    }

    /**
     * @see Validator
     * */
    @Override
    public String validate() {
        validated = true;
        StringBuilder builder = new StringBuilder();
        builder.append(this.getBuildingName()).append(" problems: \n");
        for (Room room : this.getRooms()) {
            builder.append("\t").append(room.validate());
            if (!room.isValidated()) {
                validated = false;
            }
        }
        if (validated) {
            return builder.append(this.getBuildingName()).append(" result: ").append("\tValidation is passed").toString();
        }
        else {
            return builder.append(this.getBuildingName()).append(" result: ").append("\tValidation is failed").toString();
        }
    }
}