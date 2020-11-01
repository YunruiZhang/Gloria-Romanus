package unsw.backend;

/**
 * class for Infrastructure
 */
public class Infrastructure {
    private int level;
    private int buildTime;
    private int maxUpgradable;
    private String type;
    private Province province;

    /**
     * constructor
     * @param buildTime the time to build
     * @param maxUpgradable the max level can be upgrade to
     * @param type the type of the infrasture
     * @param province the province it is in
     */
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

    /**
     * get the level of this building
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * get the type of the Infrastructure
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * get the province it is in
     * @return the province
     */
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

    /**
     * reduce the build time by one
     */
    public void setBuildTime() {
        buildTime -= 1;
    }

    /**
     * upgrade the Infrastructure
     */
    public void upgradeInfrastructure() {
        if (type.equals("Road")) {
            if (level < 2) {
                level += 1;
            }
            if (level == 2 && checkIfRoman()) {
                level += 1;
            }
        } else {
            if (level < maxUpgradable) {
                level += 1;
            }
        }
    }

    /**
     * check whether the Infrastructure is at max level
     * @return true or false
     */
    public boolean checkifmax() {
        if (level == maxUpgradable) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check is the faction is roman
     * @return true or false
     */
    public boolean checkIfRoman() {
        Player p = province.getOwner();
        if (p.getFaction().equals("Rome")) {
            return true;
        } else {
            return false;
        }
    }
}
