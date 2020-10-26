package unsw.backend;

public class Port extends Infrastructure{
    static private String type = "Port";
    static private double baseCost = 8000;
    static private double upgradeCost = 3000;
    static private int maxUpgrade = 4;

    public Port(Province province) {
        super(2, baseCost, upgradeCost, maxUpgrade, type, province);
    }

    public void upgradePort() {
        int temp = super.getLevel();
        if (temp < 4) {
            super.levelUp();
        }
    }

    public String getType() {
        return type;
    }
}
