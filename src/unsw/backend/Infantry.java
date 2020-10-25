package unsw.backend;


public class Infantry extends Unit {
    final static int full_cap = 600;
    public Infantry(String name, Province location, String type) {
        super(full_cap, name, location, type);
    }
}
