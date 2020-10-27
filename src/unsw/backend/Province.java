package unsw.backend;

import java.util.ArrayList;
import java.util.Arrays;


public class Province {
    private String Name;
    private Player Owner;
    private ArrayList<Unit> units;
    private ArrayList<Infrastructure> buildings;
    private double provinceWealth;
    private double taxRate;
    private double recruitmentCost = 500;
    private int trainTime = 2;
    private ArrayList<Object[]> soldierTraining;

    public Province() {
        this.soldierTraining = new ArrayList<Object[]>();
    }

    public String getName() {
        return Name;
    }

    public Player getOwner() {
        return Owner;
    }

    /**
     * this changes the owner of the provice when the territoy is concured.
     * @param dude
     */
    public void changeOwner(Player dude) {
        Owner = dude;
    }
    
    /**
     * @return ArrayList<Unit> return the units.
     */
    public ArrayList<Unit> getUnits() {
        return units;
    }
    
    /**
     * @param unit adds a unit to the unit list.
     */
    public void addUnits(Unit unit) {
        units.add(unit);
    }

    /**
     * @return ArrayList<Infrastructure> return the buildings.
     */
    public ArrayList<Infrastructure> getBuildings() {
        return buildings;
    }

    /**
     * @param building the buildings to set
     */
    public void addBuildings(Infrastructure building) {
        buildings.add(building);
    }

    /**
     * @return double return the provinceWealth
     */
    public double getProvinceWealth() {
        return provinceWealth;
    }

    /**
     * @param  the provinceWealth to set
     */
    public void calculateWealth() {
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    }

    /**
     * @return double return the taxRate
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * the taxRate to set
     */
    public void increaseTaxRate() {
        if (taxRate < 25) {
            taxRate += 5;
        } else {
            //System.out.println("tax rate has reached its limit");
        }
    }

    /**
     * the taxRate to set
     */
    public void decreaseTaxRate() {
        if (taxRate > 10) {
            taxRate -= 5;
        } else {
            //System.out.println("tax rate is already minimum");
        }
    }

    public double getTax() {
        return provinceWealth*taxRate;
    }



    public void generateUnits(String type, String uName) {
        for (Unit j: units) {
            if (j.getName().equals(uName) && j.getType().equals(type)) {
                Owner.subGold(100);
                findProductionBuilding(type, uName);
            }
        }
    }

    public void addToSchool(String type, int num, String uName) {
        Object[] soldier = new Object[3];
        soldier[0] = num;
        soldier[1] = uName;
        soldier[2] = trainTime(type);
        soldierTraining.add(soldier);
    }

    public void findProductionBuilding(String type, String uName) {
        for (Infrastructure i: buildings) {
            if (i instanceof TroopProduction) {
                TroopProduction temp = (TroopProduction) i;
                addToSchool(type, temp.generate(type), uName);
            }
        }
    }

    public int findFarm() {
        int j = 0;
        for (Infrastructure i: buildings) {
            if (i instanceof Farm) {
                Farm temp = (Farm) i;
                j = temp.getProductionRate();
            }
        }
        return j;
    }

    public boolean checkIfRoman() {
        if (Owner.getFaction().equals("Roman")) {
            return true;
        } else {
            return false;
        }
    }

    public int trainTime(String type) {
        int traintime = 0;
        String[] a = {"Cannon", "Chariot", "Crossbowman"};
        String[] b = {"Flagbearer", "Hopitle", "Horse", "NetFighter", "Elephant"};
        String[] c = {"Pikeman", "Slingerman", "Spearman", "Trebuchet"};
        String[] d = {"ArcherMan", "Camel", "Swordsman", "Druid"};
        if (Arrays.stream(a).anyMatch(type::equals)) {
            traintime = 1; 
        } else if (Arrays.stream(b).anyMatch(type::equals)) {
            traintime = 2; 
        } else if (Arrays.stream(c).anyMatch(type::equals)) {
            traintime = 3; 
        } else {
            traintime = 4; 
        }
        return traintime;
    }

    public void trainSoldier() {
        Object[] slotA = soldierTraining.get(0);
        Object[] slotB = soldierTraining.get(1);
        if ((int)slotA[2] != 0) {
            decreaseTrainTime(0);
        } else {
            addToUnit(slotA);
        }

        if ((int)slotB[2] != 0) {
            decreaseTrainTime(1);
        } else {
            addToUnit(slotB);
        }
    }

    public void addToUnit(Object[] troop) {
        for (Unit i: units) {
            if (i.getName().equals((String)troop[1])) {
                i.addSoldiers((int)troop[0]);
                soldierTraining.remove(troop);
            }
        }
    }

    public void decreaseTrainTime(int index) {
        int temp = (int)soldierTraining.get(index)[2];
        temp -= 1;
        soldierTraining.get(index)[2] = (Object)temp;
    }
}
