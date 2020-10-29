package unsw.backend;

public class Mine extends Infrastructure{
    static private int buildTime = 2;
    static private String type = "Mine";
    static private int maxUpgrade = 3;
    private double soldierCost = 200;

    public Mine(Province province) {
        super(buildTime, maxUpgrade, type, province);
    }

    public String getType() {
        return type;
    }

    /**
     * if level is 1 then a 10% discount is applied, if level is 2 then 20% adn so on.
     * @return the discounted price for creating more troops.
     */
    public double discountedSoldierPrice() {
        int level = super.getLevel();
        level = 100 - (level*10);
        double ret = (soldierCost*level)/100;
        return ret;
    }
}
