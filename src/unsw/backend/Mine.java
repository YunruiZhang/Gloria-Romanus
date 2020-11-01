package unsw.backend;

public class Mine extends Infrastructure implements Observer{
    static private int buildTime = 2;
    static private String type = "Mine";
    static private int maxUpgrade = 3;
    private double soldierCost = 50;

    public Mine(Province province) {
        super(buildTime, maxUpgrade, type, province);
    }

    public void update (Object o){
        SetRecruitmentCost();
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

    /**
     * sets the discounted soldier creation price every turn according to available markets
     */
    public void SetRecruitmentCost() {
        Province p = super.getProvince();
        p.setRecruitmentCost(discountedSoldierPrice());
    }
}
