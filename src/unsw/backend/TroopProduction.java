package unsw.backend;

import java.util.ArrayList;
import java.util.Arrays;

public class TroopProduction extends Infrastructure{
    static private String type = "TroopProduction";
    static private double baseCost = 7220;
    static private double upgradeCost = 4100;
    static private int maxUpgrade = 3;
    private Province province;
    private int trainTime = 2;

    public TroopProduction (Province province) {
        super(3, baseCost, upgradeCost, maxUpgrade, type, province);
        this.province = province;
    }

    public void upgradeTroopProduction() {
        int temp = super.getLevel();
        if (temp < 3) {
            super.levelUp();
        }
    }

    public String getType() {
        return type;
    }

    public int generate(String type) {
        int totalTroopsProvided = 0;
        String[] level1 = {"ArcherMan", "Camel", "Cannon", "Chariot", "Crossbowman", "Druid", "Elephant"};
        String[] level2 = {"ArcherMan", "Camel", "Cannon", "Chariot", "Crossbowman", "Druid", "Elephant", "Flagbearer", "Hopitle", "Horse", "NetFighter"};
        String[] level3 = {"ArcherMan", "Camel", "Cannon", "Chariot", "Crossbowman", "Druid", "Elephant", "Flagbearer", "Hopitle", "Horse", "NetFighter", "Pikeman", "Slingerman", "Spearman", "Swordsman", "Trebuchet"};
        // more advanced troop priduction building can produce more type of soldiers.
        switch(super.getLevel()) {
            case 1:
                if (Arrays.stream(level1).anyMatch(type::equals)) {
                    totalTroopsProvided = province.findFarm();  //more advanced farm produces more soldiers
                } else {
                    //make the button grey. building not advanced enough
                }
                break;

            case 2:
                if (Arrays.stream(level2).anyMatch(type::equals)) {
                    totalTroopsProvided = province.findFarm();
                } else {
                    //make the button grey. building not advanced enough
                }
                break;

            case 3:
                if (Arrays.stream(level3).anyMatch(type::equals)) {
                    totalTroopsProvided = province.findFarm();
                } else {
                    //make the button grey. building not advanced enough
                }
                break;
        }
        return totalTroopsProvided;
    }
}
