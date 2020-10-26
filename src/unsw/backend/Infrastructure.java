package unsw.backend;

public class Infrastructure {
    private int level;
    private int buildTime;
    private double baseCost;
    private double upgradeCost;
    private int maxUpgradable;
    private String type;
    private Province province;

    public Infrastructure (int buildTime, double baseCost, double upgradeCost, int maxUpgradable ,String type, Province province) {
        this.level = 1;
        this.buildTime = buildTime;
        this.baseCost = baseCost;
        this.upgradeCost = upgradeCost;
        this.maxUpgradable = maxUpgradable;
        this.type = type;
        this.province = province;
    }

    public void levelUp() {
        level += 1;
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }

    public Province getProvince(){
        return province;
    }

    /**
     * @return int return the buildTime
     */
    public int getBuildTime() {
        return buildTime;
    }

    /**
     * @return double return the baseCost
     */
    public double getBaseCost() {
        return baseCost;
    }

    /**
     * @return double return the upgradeCost
     */
    public double getUpgradeCost() {
        return upgradeCost;
    }

    /**
     * @return int return the maxUpgradable
     */
    public int getMaxUpgradable() {
        return maxUpgradable;
    }
}
