package unsw.backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * class for Troopproduction building extend Infrastructure
 */
public class TroopProduction extends Infrastructure implements Observer{
    static private String type = "TroopProduction";
    static private int BuildTime = 3;
    static private int maxUpgrade = 3;
    private Province province;

    /**
     * constructor
     * @param province the province to build in
     */
    public TroopProduction (Province province) {
        super(BuildTime, maxUpgrade, type, province);
        this.province = province;
    }

    /**
     * for observer train the solider at the biginning of the turn
     */
    public void update (Object o){
        TrainSoldier(1);
        TrainSoldier(0);
    }

    /**
     * get the type
     */
    public String getType() {
        return type;
    }

    /**
     * get the solider number can produce
     * @param type the type of the solider
     * @return the number of the solider it can produce
     */
    public int generate(String type) {
        int totalTroopsProvided = 0;
        String[] level1 = {"HorseArcher", "Camel", "Cannon", "Chariot", "Crossbowman", "Elephant", "Hopitle"};
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

    /**
     * create the solider
     * @param cost the cost per solider
     * @param num num of silider to create
     * @param type type of the solider
     * @param uName the unit name to add in
     * @return success or not
     */
    public boolean soldierCreator(double cost, int num, String type, String uName) {
        Player owner = province.getOwner();
        if (owner.CheckIfGoldAvailable(cost*num) && (num <= generate(type))) {
            owner.subGold(cost*num);
            Object[] soldier = new Object[3];
            soldier[0] = num;
            soldier[1] = uName;
            soldier[2] = trainTime(type);
            province.soldierTrainingadd(soldier);
            return true;
        } else {
            //System.out.println("not enough gold available"); //JAVAFX+++++++++++++++++++++++++++++++
            return false;
        }
    }

    /**
     * the train time getter
     * @param type the type of the solider
     * @return the turns require
     */
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
    
    /**
     * helper to get the fram production rate
     * @return the fram production rate
     */
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

    /**
     * train the solider
     * @param position the position of the solider on the waiting list
     */
    public void TrainSoldier(int position) {
        ArrayList<Object[]> soldierTraining = province.getSoldierTraining();
        try {
            Object[] slotB = soldierTraining.get(position);
            if ((int)slotB[2] != 1) {
                decreaseTrainTime(position);
            } else {
                addToUnit(slotB);
            }
        } catch (Exception e){
            return;
        }
    }

    /**
     * helper function to decreaseTrainTime
     * @param index index on the waiting list
     */
    public void decreaseTrainTime(int index) {
        ArrayList<Object[]> soldierTraining = province.getSoldierTraining();
        int temp = (int)soldierTraining.get(index)[2];
        temp -= 1;
        soldierTraining.get(index)[2] = (Object)temp;
    }

    /**
     * add troop into unit
     * @param troop the troop
     */
    public void addToUnit(Object[] troop) {
        for (Unit i: province.getUnits()) {
            if (i.getName().equals((String)troop[1])) {
                i.addSoldiers((int)troop[0]);
                province.provinceWealthAdder(((int)troop[0]*5)); //each troop adds 5 gold to the economy.
                province.getSoldierTraining().remove(troop);
            }
        }
    }
}
