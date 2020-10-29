package unsw.backend;

public class Port extends Infrastructure{
    static private int buildTime = 4;
    static private String type = "Port";
    static private int maxUpgrade = 4;

    public Port(Province province) {
        super(buildTime, maxUpgrade, type, province);
    }

    public String getType() {
        return type;
    }
}
