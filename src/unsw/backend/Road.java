package unsw.backend;

public class Road extends Infrastructure{
    static private String type = "Road";
    static private double baseCost = 10000;
    static private double upgradeCost = 5000;
    static private int maxUpgrade = 4;
    public Road(Province province) {
        super(1, baseCost, upgradeCost, maxUpgrade, type, province);
    }
    
    public int crossPoints() {
        int temp = 0;
        switch(super.getLevel()) {
            case 1:
                temp = 4;
                break;
            case 2:
                temp = 3;
                break;
            case 3:
                temp = 2;
                break;
            case 4:
                temp = 1;
                break;
        }
        return temp;
    }

    public String getType() {
        return type;
    }

    public void levelUp() {
        if (super.getLevel() < 3) {
            super.levelUp();
        }
        if (super.getLevel() == 3 && super.getProvince().checkIfRoman()) {
            super.levelUp();
        }
    }
}
