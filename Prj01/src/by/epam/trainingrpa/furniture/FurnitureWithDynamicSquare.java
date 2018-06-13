package by.epam.trainingrpa.furniture;

/**
 * Some types of the furniture can be folded, this is a reason of this class.
 * Classes, that extend this abstract class, have two fields: max and min square
 * @author Eugene
 * @version 1.0
 * @see by.epam.trainingrpa.furniture.Furniture
 * */
public abstract class FurnitureWithDynamicSquare extends Furniture{
    /**
     * Square of the furniture when it is folded
     * */
    final private double maxSquare;
    /**
     * Square of the furniture when it isn't folded
     * */
    final private double minSquare;

    /**
     * Constructor with two parameters: folded furniture square(maxSquare) and unfolded furniture square(minSquare)
     * @param maxSquare folded furniture square
     * @param minSquare unfolded furniture square
     * @param name name of the furniture
     * */
    FurnitureWithDynamicSquare(String name, double maxSquare, double minSquare) {
        super(name);
        if (maxSquare < 0.5) {
            System.out.println("(FurnitureWithDynamicSquare Constructor) Minimal value for maxSquare is 0.5. Program will set minimal value");
            this.maxSquare = 0.5;
        }
        else {
            this.maxSquare = maxSquare;
        }
        if (minSquare < 0.5) {
            System.out.println("(FurnitureWithDynamicSquare Constructor) Minimal value for minSquare is 0.5. Program will set minimal value");
            this.minSquare = 0.5;
        }
        else {
            this.minSquare = minSquare;
        }
    }

    /**
     * Getter, that returns value of the field {@link FurnitureWithDynamicSquare#maxSquare}
     * @return folded furniture square
     * */
    public double getMaxSquare() {
        return maxSquare;
    }

    /**
     * Getter, that returns value of the field {@link FurnitureWithDynamicSquare#minSquare}
     * @return unfolded furniture square
     * */
    public double getMinSquare() {
        return minSquare;
    }

    @Override
    public String toString() {
        return super.toString() + ", MaxSquare: " + this.maxSquare + " m^2, MinSquare: " + this.minSquare + " m^2";
    }
}
