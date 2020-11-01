package unsw.backend;

/**
 * Infantry unit extends unit implements Observer
 */
public class Infantry extends Unit implements Observer{
    static String className = "Infantry";

    /**
     * constructor 
     * @param name name of the unit
     * @param location location of the unit
     * @param type the type of this unit
     */
    public Infantry(String name, Province location, String type) {
        super(name, location, type, className, 10);
    }

    /**
     * for Observer reset move point
     */
    public void update(Object o){
        super.setMovePoint(10);
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
