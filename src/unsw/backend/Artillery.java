package unsw.backend;

/**
 * class for artillery
 */
public class Artillery extends Unit implements Observer{
    final static String className = "Artillery";

    /**
     * constructor
     * @param name name of the unit
     * @param location the location
     * @param type the type
     */
    public Artillery(String name, Province location, String type) {
        super(name, location, type, className, 4);
    }

    /**
     * Observer method
     */
    public void update(Object o){
        super.setMovePoint(4);
    }

    /**
     * strategy move
     */
    public void move(Province dest, int point){
        super.setLocation(dest);
        super.SubtractPoint(point);
        dest.addUnits(this);
    }
}
