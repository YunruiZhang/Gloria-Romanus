package unsw.backend;

public class Farm extends Infrastructure{
    static private String type = "Farm";
    static private double baseCost = 3000;
    static private double upgradeCost = 1100;
    static private int maxUpgrade = 5;

    public Farm(Province province) {
        super(2, baseCost, upgradeCost, maxUpgrade, type, province);
    }

    public void upgradeFarm() {
        int temp = super.getLevel();
        if (temp < 5) {
            super.levelUp();
        }
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
