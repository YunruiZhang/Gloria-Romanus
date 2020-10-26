package unsw.backend;

public class Mine extends Infrastructure{
    static private String type = "Mine";
    static private double baseCost = 3000;
    static private double upgradeCost = 1100;
    static private int maxUpgrade = 5;

    public Mine(Province province) {
        super(2, baseCost, upgradeCost, maxUpgrade, type, province);
    }

    public void upgradeMine() {
        int temp = super.getLevel();
        if (temp < 5) {
            super.levelUp();
        }
    }

    public String getType() {
        return type;
    }
}
