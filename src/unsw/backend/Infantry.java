package unsw.backend;


public class Infantry extends Unit {
    final static String className = "Infantry";

    public Infantry(String name, Province location, String type) {
        super(name, location, type, className);
    }
}
