package unsw.backend;

public class Artillery extends Unit {
    final static double killDamage = 100;
    final static double defencePoint = 80;
    public Artillery(String name, Province location, String type) {
        super(name, location, type, killDamage, defencePoint);
    }
}
