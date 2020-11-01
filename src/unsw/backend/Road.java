package unsw.backend;
/**
 * class for Road extend Infrastructure
 */
public class Road extends Infrastructure{
    static private int buildTime = 1;
    static private String type = "Road";
    static private int maxUpgrade = 3;

    /**
     * constructor
     * @param province the province to build in
     */
    public Road(Province province) {
        super(buildTime, maxUpgrade, type, province);
    }
    
    /**
     * get the point to move across this province
     * @return the point needed
     */
    public int crossPoints() {
        int temp = 0;
        switch(super.getLevel()) {
            case 1:
                temp = 3;
                break;
            case 2:
                temp = 2;
                break;
            case 3:
                temp = 1;
                break;
        }
        return temp;
    }

    /**
     * get the type of this infustructure (Road)
     */
    public String getType() {
        return type;
    }
}
