package unsw.backend;


public class Cavalry extends Unit implements Observer{
    final static double killDamage = 60;
    final static double defencePoint = 50;
    final static String className = "Cavalry";

    public Cavalry(String name, Province location, String type) {
        super(name, location, type, killDamage, defencePoint, className, 15);
    }

    public void update(Object o){
        super.setMovePoint(15);
    }
    public void move(Province dest, int point){
        super.setLocation(dest);
        super.SubtractPoint(point);
    }
}
