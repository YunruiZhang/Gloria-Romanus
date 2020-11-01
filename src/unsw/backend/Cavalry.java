package unsw.backend;

/**
 * class for cavalry
 */
public class Cavalry extends Unit implements Observer{
    final static String className = "Cavalry";

    /**
     * constructor
     * @param name name of the unit
     * @param location location of the unit
     * @param type type of the unit
     */
    public Cavalry(String name, Province location, String type) {
        super(name, location, type, className, 15);
    }

    /**
     * Observer method
     */
    public void update(Object o){
        super.setMovePoint(15);
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
