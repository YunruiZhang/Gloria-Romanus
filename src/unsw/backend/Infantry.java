package unsw.backend;


public class Infantry extends Unit implements Observer{
    final static double killDamage = 100;
    final static double defencePoint = 40;
    static String className = "Infantry";

    public Infantry(String name, Province location, String type) {
        super(name, location, type, killDamage, defencePoint, className, 10);
    }
    public void update(Object o){
        super.setMovePoint(10);
    }

    public void move(Province dest, int point){
        super.setLocation(dest);
        super.SubtractPoint(point);
    }
}
