package unsw.backend;

/**
 * class for farm extend Infrastructure
 */
public class Farm extends Infrastructure{
    static private int buildTime = 3;
    static private String type = "Farm";
    static private int maxUpgrade = 5;

    /**
     * constructor
     * @param province the province to build in
     */
    public Farm(Province province) {
        super(buildTime, maxUpgrade, type, province);
    }

    /**
     * get the type
     */
    public String getType() {
        return type;
    }

    /**
     * get the max number of solider the province can produce 
     * @return the number
     */
    public int getProductionRate() {
        int rate = 0;
        switch(super.getLevel()) {
            case 1:
                rate = 20;
                break;

            case 2:
                rate = 25;
                break;

            case 3:
                rate = 30;
                break;

            case 4:
                rate = 35;
                break;

            case 5:
                rate = 40;
                break;
        }
        return rate;
    }
}
