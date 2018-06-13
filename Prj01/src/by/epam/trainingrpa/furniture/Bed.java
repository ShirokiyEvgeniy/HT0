package by.epam.trainingrpa.furniture;

/**
 * This class describes a bed in the room, extends abstract class, that has fields max and min square
 * @author Eugene
 * @version 1.0
 * @see by.epam.trainingrpa.furniture.FurnitureWithDynamicSquare
 * */
public class Bed extends FurnitureWithDynamicSquare{

    /**
     * Constructor with two parameters: folded furniture square(maxSquare) and unfolded furniture square(minSquare)
     * @param maxSquare folded furniture square
     * @param minSquare unfolded furniture square
     * @param name name of the furniture
     */
    public Bed(String name, double maxSquare, double minSquare) {
        super(name, maxSquare, minSquare);
    }

    @Override
    public String toString() {
        return super.toString() + " (Bed)";
    }
}
