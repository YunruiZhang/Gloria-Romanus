package unsw.backend;

public class TroopProduction extends Infrastructure{
    private String type = "TroopProduction";
    static private double baseCost = 7220;
    static private double upgradeCost = 4100;
    static private int maxUpgrade = 9;

    public TroopProduction() {
        super(2, baseCost, upgradeCost, maxUpgrade);
    }

    public void upgradeTroopProduction() {
        int temp = super.getLevel();
        if (temp < 9) {
            super.levelUp();
        }
    }

    public String getType() {
        return type;
    }
}
