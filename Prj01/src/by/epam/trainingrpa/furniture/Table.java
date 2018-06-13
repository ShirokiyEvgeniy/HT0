package by.epam.trainingrpa.furniture;

/**
 * This class describes a table in the room, extends abstract class, that has field with static square
 * @author Eugene
 * @version 1.0
 * @see by.epam.trainingrpa.furniture.FurnitureWithStaticSquare
 * */
public class Table extends FurnitureWithStaticSquare{

    /**
     * Constructor with the parameter <b>square</b>
     * @param square square of the furniture
     * @param name name of the furniture
     */
    public Table(String name, double square) {
        super(name, square);
    }

    @Override
    public String toString() {
        return super.toString() + " (Table)";
    }
}
