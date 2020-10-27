package unsw.backend;

public class Farm extends Infrastructure{
    static private int buildTime = 3;
    static private String type = "Farm";
    static private int maxUpgrade = 5;

    public Farm(Province province) {
        super(buildTime, maxUpgrade, type, province);
    }

    public String getType() {
        return type;
    }

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
