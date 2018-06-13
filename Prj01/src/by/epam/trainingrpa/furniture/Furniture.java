package by.epam.trainingrpa.furniture;

/**
 * This is a general class for all types of the furniture
 * @author Eugene
 * @version 1.0
 * */
public abstract class Furniture {
    /**
     * This field describes name of the furniture
     * */
    final private String name;

    /**
     * Constructor with the parameter <b>name</b>
     * @param name Name of the furniture
     * */
    Furniture(String name) {
        this.name = name;
    }

    /**
     * Getter, that returns value of the field {@link Furniture#name}
     * @return name of the furniture
     * */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + this.name;
    }
}
