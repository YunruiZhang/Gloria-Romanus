package unsw.backend;

/**
 * class for port extend Infrastructure
 */
public class Port extends Infrastructure{
    static private int buildTime = 4;
    static private String type = "Port";
    static private int maxUpgrade = 4;

    /**
     * constructor
     * @param province the province to build in
     */
    public Port(Province province) {
        super(buildTime, maxUpgrade, type, province);
    }
     /**
     * get the type of this infustructure (Port)
     */
    public String getType() {
        return type;
    }
}
