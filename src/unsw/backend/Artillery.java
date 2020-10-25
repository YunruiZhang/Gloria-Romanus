package unsw.backend;

public class Artillery extends Unit {
    final static int full_cap = 400;
    public Artillery(String name, Province location, String type) {
        super(full_cap, name, location, type);
    }
}
