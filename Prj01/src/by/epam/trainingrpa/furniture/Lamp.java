package by.epam.trainingrpa.furniture;
/**
 * This class describes lamps in rooms, has one field <b>Illumination</b>
 * @author Eugene
 * @version 1.0
 * @see by.epam.trainingrpa.furniture.Furniture
 * */
public class Lamp extends Furniture{
    /**
     * Covered square by a lamp
     * */
    final private double illumination;

    /**
     * Constructor with the parameter <b>illumination</b>
     * @param illumination how much square is covered by our lamp
     * @param name name of the furniture
     * */
    public Lamp(String name, double illumination) {
        super(name);
        if (illumination < 20) {
            System.out.println("(Lamp Constructor) Minimal value for illumination is 20. Program will set minimal value.");
            this.illumination = 20;
        }
        else {
            this.illumination = illumination;
        }
    }

    /**
     * Getter, that returns value of the filed {@link Lamp#illumination}
     * @return illumination field
     * */
    public double getIllumination() {
        return illumination;
    }

    @Override
    public String toString() {
        return super.toString() + ", Illumination: " + this.illumination + " lk (Lamp)";
    }
}