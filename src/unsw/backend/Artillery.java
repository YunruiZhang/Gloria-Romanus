package unsw.backend;

public class Artillery extends Unit {
    final static String className = "Artillery";

    public Artillery(String name, Province location, String type) {
        super(name, location, type, className);
    }
}
