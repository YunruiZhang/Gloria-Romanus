package unsw.backend;

public class Infrastructure {
    private int level;
    private int buildTime;
    private int maxUpgradable;
    private String type;
    private Province province;

    public Infrastructure (int buildTime, int maxUpgradable ,String type, Province province) {
        this.level = 1;
        this.buildTime = buildTime;
        this.maxUpgradable = maxUpgradable;
        this.type = type;
        this.province = province;
        int time = buildTime - province.getBTime();
        if (time < 1) {
            time = 1;
        }
        this.buildTime = time;
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
     * @return int return the maxUpgradable
     */
    public int getMaxUpgradable() {
        return maxUpgradable;
    }

    public void setBuildTime() {
        buildTime -= 1;
    }

    public void upgradeInfrastructure() {
        if (type.equals("Road")) {
            if (level < 2) {
                level += 1;
            }
            if (level == 2 && province.checkIfRoman()) {
                level += 1;
            }
        } else {
            if (level < maxUpgradable) {
                level += 1;
            }
        }
    }

    public boolean checkifmax() {
        if (level == maxUpgradable) {
            return true;
        } else {
            return false;
        }
    }
}
