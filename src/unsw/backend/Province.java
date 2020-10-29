package unsw.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Province implements Observer{
    private String Name;
    private Player Owner;
    private ArrayList<Unit> units;
    private ArrayList<Infrastructure> buildings;
    private double provinceWealth;
    private double taxRate;
    private double recruitmentCost = 200;
    private ArrayList<Object[]> soldierTraining;
    private ArrayList<Infrastructure> buildinConstruction;
    private int turn;
    private String faction;
    private double buidingPrice = 2000;
    private double buildingUpgrade = 1000; 

    public Province(String name, String faction) {
        this.soldierTraining = new ArrayList<Object[]>();
        this.buildinConstruction = new ArrayList<Infrastructure>();
        this.units = new ArrayList<Unit>();
        this.buildings = new ArrayList<Infrastructure>();
        this.Name = name;
        this.faction = faction;
        this.taxRate = 15;
    }

    

    public void update (Object o){
        this.turn = (int) o;
        trainSoldier();
        decreaseBBTime();
        setBuildingUpgrade();
        setRecruitmentCost();
        setBuidingPrice();
        calculateWealth();
        Owner.addGold(getTax());
    }

    public String getName() {
        return Name;
    }

    public Player getOwner() {
        return Owner;
    }

    public String getFaction(){
        return this.faction;
    }

    public void setFaction(String faction){
        this.faction = faction;
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
     * changes the wealth every turn according to the tax rate.
     */
    public void calculateWealth() {
        if (taxRate == 10) {
            provinceWealth += 10;
        } else if (taxRate == 15) {
            provinceWealth += 0;
        } else if (taxRate == 20) {
            if (provinceWealth-10 < 0) {
                provinceWealth = 0;
            } else {
                provinceWealth -= 10;
            }
        } else if (taxRate == 25) {
            if (provinceWealth-30 < 0) {
                provinceWealth = 0;
            } else {
                provinceWealth -= 30;
            }
            decreaseAllMorale();
        }
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
        return provinceWealth*taxRate*0.01;
    }



    public boolean generateTroop(String type, String uName) {
        for (Unit j: units) {
            if (j.getName().equals(uName) && j.getType().equals(type) && cfsProductionBuilding()) {
                if (Owner.CheckIfGoldAvailable(recruitmentCost)) {
                    Owner.subGold(100);
                    findProductionBuilding(type, uName);
                    return true;
                } else {
                    return false;
                    //System.out.println("Request denied"); //JAVAFX+++++++++++++++++++++++++++++++
                }
            }
        }
        return false;
    }

    public void addToSchool(String type, int num, String uName) {
        Object[] soldier = new Object[3];
        soldier[0] = num;
        soldier[1] = uName;
        soldier[2] = trainTime(type);
        soldierTraining.add(soldier);
    }

    public boolean cfsProductionBuilding() {
        for (Infrastructure i: buildings) {
            if (i instanceof TroopProduction) {
                return true;
            }
        }
        return false;
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

    public void trainSoldier() {
        try {
            Object[] slotA = soldierTraining.get(0);
            if ((int)slotA[2] != 0) {
                decreaseTrainTime(0);
            } else {
                addToUnit(slotA);
            }
        } catch (Exception e){
            //no soldiers currently being trained.
            return;
        }

        try {
            Object[] slotB = soldierTraining.get(1);
            if ((int)slotB[2] != 0) {
                decreaseTrainTime(1);
            } else {
                addToUnit(slotB);
            }
        } catch (Exception e){
            //no soldiers currently being trained.
            return;
        }
    }

    public void addToUnit(Object[] troop) {
        for (Unit i: units) {
            if (i.getName().equals((String)troop[1])) {
                i.addSoldiers((int)troop[0]);
                provinceWealth += ((int)troop[0]*5);    //each troop adds 5 gold to the economy.
                soldierTraining.remove(troop);
            }
        }
    }

    public void decreaseTrainTime(int index) {
        int temp = (int)soldierTraining.get(index)[2];
        temp -= 1;
        soldierTraining.get(index)[2] = (Object)temp;
    }

    public void constructNBuilding(Infrastructure temp) {
        buildinConstruction.add(temp);
    }

    public void decreaseBBTime() {
        Iterator<Infrastructure> itr = buildinConstruction.iterator();
        while (itr.hasNext()) {
            Infrastructure infra = itr.next();
            if (infra.getBuildTime() == 0) {
                buildings.add(infra);
                itr.remove();
                //once building has been built, it contributes to the economy.
                provinceWealth += 700;
            } else {
                infra.setBuildTime();
            }
        }
    }
    
   
    /**
     * sets the discounted soldier creation price every turn according to available markets
     */
    public void setRecruitmentCost() {
        double priceR = buidingPrice;
        for (Infrastructure i: buildings) {
            if (i instanceof Mine) {
                Mine temp = (Mine) i;
                priceR = temp.discountedSoldierPrice();
            }
        }
        this.recruitmentCost = priceR;
    }

    /**
     * @return double return the buidingPrice
     */
    public double getBuidingPrice() {
        return buidingPrice;
    }

    /**
     * sets the upgraded discounted building price every turn according to available markets
     */
    public void setBuidingPrice() {
        double priceR = buidingPrice;
        for (Infrastructure i: buildings) {
            if (i instanceof Market) {
                Market temp = (Market) i;
                priceR = temp.discountedPriceBase();
            }
        }
        this.buidingPrice = priceR;
    }

    /**
     * @return double return the buildingUpgrade
     */
    public double getBuildingUpgrade() {
        return buildingUpgrade;
    }

    /**
     * sets the upgraded discounted building price every turn according to available markets
     */
    public void setBuildingUpgrade() {
        double priceR = buildingUpgrade;
        for (Infrastructure i: buildings) {
            if (i instanceof Market) {
                Market temp = (Market) i;
                priceR = temp.discountedPrice();
            }
        }
        this.buildingUpgrade = priceR;
    }

    public boolean checkIfBuildingExistsA(String type) {
        boolean check = false;
        for (Infrastructure i: buildinConstruction) {
            if (i.getType().equals(type)) {
                check = true;
                return true;
            }
        }
        return check;
    }

    public boolean checkIfBuildingExistsB(String type) {
        boolean check = false;
        for (Infrastructure j: buildings) {
            if (j.getType().equals(type)) {
                check = true;
                return true;
            }
        }
        return check;
    }

    /**
     * if mine is level x then overall build time is reduced by x turns.
     * @return the time reduced by buiding an advanced mine
     */
    public int getBTime() {
        int timeReduce = 0;
        for (Infrastructure i: buildings) {
            if (i instanceof Mine) {
                timeReduce = i.getLevel();
            }
        }
        return timeReduce;
    }

    public void upgradeBUILDING(String type) {
        for (Infrastructure i: buildings) {
            if (i.getType().equals(type)) {
                i.upgradeInfrastructure();
                //once building has been upgraded, it contributes to the economy.
                provinceWealth += 300;
            }
        }
    }

    public boolean CheckIfMAX(String type) {
        boolean temp = false;
        for (Infrastructure i: buildings) {
            if (i.getType().equals(type)) {
                temp = i.checkifmax();
            }
        }
        return temp;
    }

    public void decreaseAllMorale() {
        for (Unit u : units) {
            u.decreaseMorale(1);
        }
    }
}
