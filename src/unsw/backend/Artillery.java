package unsw.backend;

public class Artillery extends Unit implements Observer{
    final static double killDamage = 100;
    final static double defencePoint = 80;
    final static String className = "Artillery";

    public Artillery(String name, Province location, String type) {
        super(name, location, type, killDamage, defencePoint, className, 4);
    }
    public void update(Object o){
        super.setMovePoint(4);
    }
    public void move(Province dest, int point){
        super.setLocation(dest);
        super.SubtractPoint(point);
    }
}
