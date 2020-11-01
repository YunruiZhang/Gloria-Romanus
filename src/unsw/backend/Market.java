package unsw.backend;

public class Market extends Infrastructure implements Observer{
    static private String type = "Market";
    static private int maxUpgrade = 5;
    private double d = 1000;
    private double build = 2000;

    public Market(Province province) {
        super(3, maxUpgrade, type, province);
    }

    public void update (Object o){
        setBuildingUpgrade();
        setBuidingPrice();
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

    /**
     * sets the upgraded discounted building price every turn according to available markets
     */
    public void setBuildingUpgrade() {
        Province p = super.getProvince();
        p.setBuildingUpgrade(discountedPrice());
    }

    /**
     * sets the upgraded discounted building price every turn according to available markets
     */
    public void setBuidingPrice() {
        Province p = super.getProvince();
        p.setBuidingPrice(discountedPriceBase());
    }
}
