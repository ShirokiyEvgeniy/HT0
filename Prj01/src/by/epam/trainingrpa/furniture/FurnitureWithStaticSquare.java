package by.epam.trainingrpa.furniture;

/**
 * This is an abstract class, that is used to extend it by furniture classes with static square
 * @author Eugene
 * @version 1.0
 * @see by.epam.trainingrpa.furniture.Furniture
 * */
public abstract class FurnitureWithStaticSquare extends Furniture{
    /**
     * Square of the furniture
     * */
    final private double square;

    /**
     * Constructor with the parameter <b>square</b>
     * @param square square of the furniture
     * @param name name of the furniture
     * */
    FurnitureWithStaticSquare(String name, double square) {
        super(name);
        if (square < 0.5) {
            System.out.println("(FurnitureWithStaticSquare Constructor) Minimal value for square is 0.5. Program will set minimal value");
            this.square = 0.5;
        }
        else {
            this.square = square;
        }
    }

    /**
     * Getter, that returns value of the field {@link FurnitureWithStaticSquare#square}
     * @return square of the furniture
     * */
    public double getSquare() {
        return square;
    }

    @Override
    public String toString() {
        return super.toString() + ", Square: " + this.square + " m^2";
    }
}
