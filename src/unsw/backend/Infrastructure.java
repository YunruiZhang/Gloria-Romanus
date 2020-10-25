package unsw.backend;

public class Infrastructure {
    private int level;
    private int buildTime;
    private double baseCost;
    private double upgradeCost;
    private int maxUpgradable;

    public Infrastructure (int buildTime, double baseCost, double upgradeCost, int maxUpgradable) {
        this.level = 1;
        this.buildTime = buildTime;
        this.baseCost = baseCost;
        this.upgradeCost = upgradeCost;
        this.maxUpgradable = maxUpgradable;
    }

    public void levelUp() {
        level += 1;
    }

    public int getLevel() {
        return level;
    }
}
