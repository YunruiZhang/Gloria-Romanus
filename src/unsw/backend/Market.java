package unsw.backend;

public class Market extends Infrastructure{
    //static private int buildTime = 3;
    static private String type = "Market";
    static private int maxUpgrade = 5;
    private double d = 2000;
    private double build = 1000;

    public Market(Province province) {
        super(3, maxUpgrade, type, province);
    }

    /**
     * if level is 1 then a 10% discount is applied, if level is 2 then 20% adn so on.
     * @return the discounted price for upgrading the infrastructure
     */
    public double discountedPrice() {
        int level = super.getLevel();
        level = 100 - (level*10);
        double ret = (d*level)/100;
        return ret;
    }

    /**
     * if level is 1 then a 10% discount is applied, if level is 2 then 20% adn so on.
     * @return the discounted price for building the infrastructure
     */
    public double discountedPriceBase() {
        int level = super.getLevel();
        level = 100 - (level*10);
        double ret = (build*level)/100;
        return ret;
    }

    public String getType() {
        return type;
    }
}
