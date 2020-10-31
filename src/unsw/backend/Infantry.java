package unsw.backend;


public class Infantry extends Unit implements Observer{
    static String className = "Infantry";

    public Infantry(String name, Province location, String type) {
        super(name, location, type, className, 10);
    }
    public void update(Object o){
        super.setMovePoint(10);
    }

    public void move(Province dest, int point){
        super.setLocation(dest);
        super.SubtractPoint(point);
        dest.addUnits(this);
    }
}
