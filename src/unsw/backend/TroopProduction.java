package unsw.backend;

import java.util.ArrayList;
import java.util.Arrays;

public class TroopProduction extends Infrastructure implements Observer{
    static private String type = "TroopProduction";
    static private int BuildTime = 3;
    static private int maxUpgrade = 3;
    private Province province;

    public TroopProduction (Province province) {
        super(BuildTime, maxUpgrade, type, province);
        this.province = province;
    }

    public void update (Object o){
        TrainSoldier();
    }

    public String getType() {
        return type;
    }

    public int generate(String type) {
        int totalTroopsProvided = 0;
        String[] level1 = {"HorseArcher", "Camel", "Cannon", "Chariot", "Crossbowman", "Druid", "Elephant", "Hopitle"};
        String[] level2 = {"HorseArcher", "Camel", "Cannon", "Chariot", "Crossbowman", "Druid", "Elephant", "Hopitle", "NetFighter", "Berserker", "Lancer", "Javelin", "MissileMan"};
        String[] level3 = {"HorseArcher", "Camel", "Cannon", "Chariot", "Crossbowman", "Druid", "Elephant", "Hopitle", "NetFighter", "Pikeman", "Spearman", "Swordsman", "Trebuchet", "Lancer", "Berserker", "Javelin", "legionary", "MissileMan"};
        // more advanced troop priduction building can produce more type of soldiers.
        switch(super.getLevel()) {
            case 1:
                if (Arrays.stream(level1).anyMatch(type::equals)) {
                    totalTroopsProvided = findFarmRate();  //more advanced farm produces more soldiers
                } else {
                    //make the button grey. building not advanced enough
                }
                break;

            case 2:
                if (Arrays.stream(level2).anyMatch(type::equals)) {
                    totalTroopsProvided = findFarmRate();
                } else {
                    //make the button grey. building not advanced enough
                }
                break;

            case 3:
                if (Arrays.stream(level3).anyMatch(type::equals)) {
                    totalTroopsProvided = findFarmRate();
                } else {
                    //make the button grey. building not advanced enough
                }
                break;
        }
        return totalTroopsProvided;
    }

    public boolean soldierCreator(double cost, int num, String type, String uName) {
        Player owner = province.getOwner();
        if (owner.CheckIfGoldAvailable(cost*num)) {
            owner.subGold(cost*num);
            Object[] soldier = new Object[3];
            soldier[0] = generate(type);
            soldier[1] = uName;
            soldier[2] = trainTime(type);
            province.soldierTrainingadd(soldier);
            return true;
        } else {
            return false;
            //System.out.println("not enough gold available"); //JAVAFX+++++++++++++++++++++++++++++++
        }
    }

    public int trainTime(String type) {
        int traintime = 0;
        String[] a = {"Cannon", "Chariot", "Crossbowman", "Lancer"};
        String[] b = {"Hopitle", "NetFighter", "Elephant", "Javelin", "MissileMan"};
        String[] c = {"Pikeman", "Spearman", "Trebuchet", "Berserker"};
        String[] d = {"HorseArcher", "Camel", "Swordsman", "Druid", "legionary"};
        if (Arrays.stream(a).anyMatch(type::equals)) {
            traintime = 1; 
        } else if (Arrays.stream(b).anyMatch(type::equals)) {
            traintime = 2; 
        } else if (Arrays.stream(c).anyMatch(type::equals)) {
            traintime = 3; 
        } else if (Arrays.stream(d).anyMatch(type::equals)){
            traintime = 4; 
        }
        return traintime;
    }

    public int findFarmRate() {
        int j = 10;
        for (Infrastructure i: province.getBuildings()) {
            if (i instanceof Farm) {
                Farm temp = (Farm) i;
                j = temp.getProductionRate();
            }
        }
        return j;
    }

    public void TrainSoldier() {
        ArrayList<Object[]> soldierTraining = province.getSoldierTraining();
        try {
            Object[] slotA = soldierTraining.get(0);
            if ((int)slotA[2] != 0) {
                province.decreaseTrainTime(0);
            } else {
                province.addToUnit(slotA);
            }
        } catch (Exception e){
            //no soldiers currently being trained.
            return;
        }

        try {
            Object[] slotB = soldierTraining.get(1);
            if ((int)slotB[2] != 0) {
                province.decreaseTrainTime(1);
            } else {
                province.addToUnit(slotB);
            }
        } catch (Exception e){
            //no soldiers currently being trained.
            return;
        }
    }
}
