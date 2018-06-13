package by.epam.trainingrpa.mainclasses;
import by.epam.trainingrpa.exceptions.IlluminanceTooMuchException;
import by.epam.trainingrpa.exceptions.SpaceUsageTooMuchException;
import by.epam.trainingrpa.furniture.*;
import by.epam.trainingrpa.validator.Validator;

import java.util.ArrayList;
import java.util.List;


/**
 * This class describes rooms in the building.
 * Room has square, number of windows and list of the furniture.
 * To learn about adding furniture in the room see furniture classes.
 * @author Eugene
 * @version 1.0
 * @see by.epam.trainingrpa.furniture.Furniture
 * */
public class Room implements Validator {
    /**
     * This constanta is a window illumination
     */
    final private int WINDOW_ILLUMINATION = 700;
    /**
     * This constanta is a minimum required illumination
     */
    final private int MIN_ILLUMINATION = 300;
    /**
     * This constanta is a maximum required illumination
     */
    final private int MAX_ILLUMINATION = 4000;
    /**
     * This field describes name of the room.
     */
    final private String roomName;
    /**
     * This field describes square of the room.
     * It helps to calculate allowable square for the furniture
     */
    final private double roomSquare;
    /**
     * This field describes the number of windows.
     * Every window gives 700 lk.
     */
    final private int numberOfWindows;
    /**
     * This field describes list of the furniture.
     * You can add ({@link Room#addFurniture(Furniture)}) and remove ({@link Room#removeFurniture(Furniture)}) furniture
     *
     * @see Furniture
     */
    private List<Furniture> listOfTheFurniture;
    /**
     * This field is true, if this room passed the validation by Validator
     * @see Validator
     * */
    private boolean validated = false;
    /**
     * This field describes list of the furniture.
     * You can add ({@link Room#addLamp(Lamp)}) and remove ({@link Room#removeLamp(Lamp)}) lamps
     *
     * @see Lamp
     */
    private List<Lamp> listOfLamps;

    /**
     * Constructor with parameters. List of the furniture and lamps are empty, when you use this one.
     *
     * @param roomName        name of the room
     * @param roomSquare      square of the room
     * @param numberOfWindows number of windows in the room
     */
    public Room(String roomName, double roomSquare, int numberOfWindows) {
        this.roomName = roomName;
        this.roomSquare = roomSquare;
        this.numberOfWindows = numberOfWindows;
        this.listOfTheFurniture = new ArrayList<>();
        this.listOfLamps = new ArrayList<>();
    }

    /**
     * Constructor with parameters. List of the furniture should be set. List of lamps will be empty.
     *
     * @param roomName           name of the room
     * @param roomSquare         square of the room
     * @param numberOfWindows    number of windows in the room
     * @param listOfTheFurniture list with the room's furniture
     */
    public Room(String roomName, double roomSquare, int numberOfWindows, List<Furniture> listOfTheFurniture) {
        this.roomName = roomName;
        this.roomSquare = roomSquare;
        this.numberOfWindows = numberOfWindows;
        this.listOfTheFurniture = listOfTheFurniture;
        this.listOfLamps = new ArrayList<>();
    }

    /**
     * Constructor with parameters. List of lamps and furniture should be set.
     *
     * @param roomName           name of the room
     * @param roomSquare         square of the room
     * @param numberOfWindows    number of windows in the room
     * @param listOfLamps        list with the room's lamps
     * @param listOfTheFurniture list with the room's furniture
     */
    public Room(String roomName, double roomSquare, int numberOfWindows, List<Furniture> listOfTheFurniture, List<Lamp> listOfLamps) {
        this.roomName = roomName;
        this.roomSquare = roomSquare;
        this.numberOfWindows = numberOfWindows;
        this.listOfTheFurniture = listOfTheFurniture;
        this.listOfLamps = listOfLamps;
    }

    /**
     * Getter, that returns value of the field {@link Room#roomName} .
     *
     * @return name of the room
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Getter, that returns value of the field {@link Room#roomSquare} .
     *
     * @return square of the room
     */
    public double getRoomSquare() {
        return roomSquare;
    }

    /**
     * Getter, that returns value of the field {@link Room#numberOfWindows} .
     *
     * @return number of windows
     */
    public int getNumberOfWindows() {
        return numberOfWindows;
    }

    /**
     * Getter, that returns value of the field {@link Room#listOfTheFurniture} .
     *
     * @return list of the furniture
     */
    public List<Furniture> getListOfTheFurniture() {
        return listOfTheFurniture;
    }

    /**
     * Setter, that is used to set a new list of the furniture ({@link Room#listOfTheFurniture} ).
     *
     * @param listOfTheFurniture a new list of the furniture
     */
    public void setListOfTheFurniture(List<Furniture> listOfTheFurniture) {
        this.listOfTheFurniture = listOfTheFurniture;
    }

    /**
     * Getter, that returns value of the field {@link Room#listOfLamps} .
     *
     * @return list of lamps
     */
    public List<Lamp> getListOfLamps() {
        return listOfLamps;
    }

    /**
     * Setter, that is used to set a new list of the furniture ({@link Room#listOfLamps} ).
     *
     * @param listOfLamps a new list of lamps
     */
    public void setListOfLamps(List<Lamp> listOfLamps) {
        this.listOfLamps = listOfLamps;
    }

    /**
     * This method is used to add new furniture to the room.
     *
     * @param furniture new furniture to add
     */
    public void addFurniture(Furniture furniture) {
        try {
            double square = this.getMaxSquare();
            if (furniture.getClass() == Bed.class) {
                square += ((Bed) (furniture)).getMinSquare();
            } else if (furniture.getClass() == SoftChair.class) {
                square += ((SoftChair) (furniture)).getMinSquare();
            } else if (furniture.getClass() == Cupboard.class) {
                square += ((Cupboard) (furniture)).getSquare();
            } else if (furniture.getClass() == Table.class) {
                square += ((Table) (furniture)).getSquare();
            }

            if (square > 0.7 * this.roomSquare) {
                throw new SpaceUsageTooMuchException("Can't add furniture " + furniture.getName() + ", exceeded the allowed value");
            }
            listOfTheFurniture.add(furniture);
        } catch (SpaceUsageTooMuchException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used to remove furniture from the room.
     *
     * @param furniture furniture to remove
     */
    public void removeFurniture(Furniture furniture) {
        listOfTheFurniture.remove(furniture);
    }

    /**
     * This method returns furniture by its name
     *
     * @param string name of the furniture for finding
     * @return found furniture or null
     */
    public Furniture getFurniture(String string) {
        for (Furniture furn : this.getListOfTheFurniture()) {
            if (furn.getName().equals(string)) {
                return furn;
            }
        }
        return null;
    }

    /**
     * This method is used to add new lamps to the room.
     *
     * @param lamp new lamp to add
     */
    public void addLamp(Lamp lamp) {
        try {
            double illum = this.getIllumination() + lamp.getIllumination();
            if (illum > MAX_ILLUMINATION) {
                throw new IlluminanceTooMuchException("Can't add lamp" + lamp.getName() + ", exceeded the allowed value");
            }
            listOfLamps.add(lamp);
        } catch (IlluminanceTooMuchException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used to remove lamps from the room.
     *
     * @param lamp lamp to remove
     */
    public void removeLamp(Lamp lamp) {
        listOfLamps.remove(lamp);
    }

    /**
     * This method returns a lamp by its name
     *
     * @param string name of the lamp for finding
     * @return found lamp or null
     */
    public Lamp getLamp(String string) {
        for (Lamp lamp : this.getListOfLamps()) {
            if (lamp.getName().equals(string)) {
                return lamp;
            }
        }
        return null;
    }

    /**
     * This method sums illumination of lamps and windows
     *
     * @return room illumination
     */
    public int getIllumination() {
        int wndIllum = this.getNumberOfWindows() * WINDOW_ILLUMINATION;
        int lampsIllum = 0;
        for (Lamp lamp : this.getListOfLamps()) {
            lampsIllum += lamp.getIllumination();
        }
        return wndIllum + lampsIllum;
    }

    /**
     * This method returns minimum furniture square
     * @return minimum furniture square
     * */
    private double getMinSquare() {
        double minSquare = 0;
        for (Furniture furniture : this.getListOfTheFurniture()) {
            if (furniture.getClass() == Bed.class) {
                minSquare += ((Bed) (furniture)).getMinSquare();
            }
            if (furniture.getClass() == SoftChair.class) {
                minSquare += ((SoftChair) (furniture)).getMinSquare();
            }
            if (furniture.getClass() == Cupboard.class) {
                minSquare += ((Cupboard) (furniture)).getSquare();
            }
            if (furniture.getClass() == Table.class) {
                minSquare += ((Table) (furniture)).getSquare();
            }
        }
        return minSquare;
    }

    /**
     * This method returns maximum furniture square
     * @return maximum furniture square
     * */
    private double getMaxSquare() {
        double maxSquare = 0;
        for (Furniture furniture : this.getListOfTheFurniture()) {
            if (furniture.getClass() == Bed.class) {
                maxSquare += ((Bed) (furniture)).getMaxSquare();
            }
            if (furniture.getClass() == SoftChair.class) {
                maxSquare += ((SoftChair) (furniture)).getMaxSquare();
            }
            if (furniture.getClass() == Cupboard.class) {
                maxSquare += ((Cupboard) (furniture)).getSquare();
            }
            if (furniture.getClass() == Table.class) {
                maxSquare += ((Table) (furniture)).getSquare();
            }
        }
        return maxSquare;
    }

    /**
     * Getter, that returns value of the field {@link Room#validated}
     * @return room is validated or not
     * */
    public boolean isValidated() {
        return validated;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.roomName).append(": \n");
        builder.append("\t\tIllumination: ").append(this.getIllumination()).append(" lk");
        builder.append(" (").append(this.numberOfWindows).append(" windows for 700 lk and lamps for ");
        int count = 0;
        for (Lamp lamp : this.getListOfLamps()) {
            if (count == this.getListOfLamps().size() - 1) {
                builder.append(lamp.getIllumination()).append(" lk)\n");
                break;
            }
            builder.append(lamp.getIllumination()).append(" lk, ");
            count++;
        }
        builder.append("\t\tSquare: ").append(this.getRoomSquare()).append(" m^2 (Occupied square is ");

        double minSquare = this.getMinSquare();
        double maxSquare = this.getMaxSquare();
        if (minSquare == maxSquare) {
            builder.append(maxSquare).append(" m^2");
        }
        else {
            builder.append(minSquare).append("-").append(maxSquare).append(" m^2");
        }
        builder.append(", guaranteed ");
        double guaranteed = 100.0 - (maxSquare / this.roomSquare) * 100.0;
        double guaranteedM = this.roomSquare - maxSquare;
        String guaranteedSq = String.format("%.0f", guaranteed);
        String guaranteedSqM = String.format("%.2f", guaranteedM);
        builder.append(guaranteedSq).append("% or ").append(guaranteedSqM).append(" m^2 of free square)\n");
        if (this.getListOfTheFurniture().size() != 0) {
            builder.append("\t\tFurniture:\n");
            for (Furniture furn : this.getListOfTheFurniture()) {
                builder.append("\t\t\t").append(furn).append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public String validate() {
        validated = true;
        StringBuilder builder = new StringBuilder();
        builder.append(this.roomName).append(" problems:\n");
        double illum = this.getIllumination();
        if (illum < MIN_ILLUMINATION) {
           builder.append("\t\tRoom illumination < minimum required illumination. (Add lamps to this room with method addLamp())\n");
           validated = false;
        }
        else if (illum > MAX_ILLUMINATION) {
            builder.append("\t\tRoom illumination > maximum required illumination. (Remove lamps with method removeLamp())\n");
            validated = false;
        }
        double maxSquare = this.getMaxSquare();
        double percentOfSquare = (maxSquare / this.roomSquare) * 100.0;
        if (percentOfSquare > 70.0) {
            builder.append("\t\tSquare usage > 70% (Remove furniture to make more free space)\n");
            validated = false;
        }
        builder.append("\t\tResult: ");
        if (validated) {
            builder.append("\tValidation is passed\n");
        }
        else {
            builder.append("\tValidation is failed\n");
        }
        return builder.toString();
    }
}