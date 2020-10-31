package unsw.backend;

public class Artillery extends Unit implements Observer{
    final static String className = "Artillery";

    public Artillery(String name, Province location, String type) {
        super(name, location, type, className, 4);
    }
    public void update(Object o){
        super.setMovePoint(4);
    }
    public void move(Province dest, int point){
        super.setLocation(dest);
        super.SubtractPoint(point);
        dest.addUnits(this);
    }
}
