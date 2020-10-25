package unsw.backend;


public class Cavalry extends Unit {
    final static double killDamage = 60;
    final static double defencePoint = 50;
    public Cavalry(String name, Province location, String type) {
        super(name, location, type, killDamage, defencePoint);
    }
    public Move(Province dest){
        
    }
}
