package unsw.backend;


public class Infantry extends Unit {
    final static double killDamage = 100;
    final static double defencePoint = 40;
    public Infantry(String name, Province location, String type) {
        super(name, location, type, killDamage, defencePoint);
    }
}
