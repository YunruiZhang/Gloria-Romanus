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
    private double recruitmentCost = 50;
    private ArrayList<Object[]> soldierTraining;
    private ArrayList<Infrastructure> buildinConstruction;
    private int turn;
    private String faction;
    private double buidingPrice = 2000;
    private double buildingUpgrade = 1000; 
    private Tax tax;

    public Province(String name, String faction, Player owner) {
        this.soldierTraining = new ArrayList<Object[]>();
        this.buildinConstruction = new ArrayList<Infrastructure>();
        this.units = new ArrayList<Unit>();
        this.buildings = new ArrayList<Infrastructure>();
        this.Name = name;
        this.faction = faction;
        this.Owner = owner;
        this.tax = new Tax(this);
        this.provinceWealth = 100;
    }

    public void update (Object o){
        this.turn = (int) o;
        decreaseBBTime();
        calculateWealth();
        Owner.addGold(this.getTax());
    }

    public int getCurrTurn() {
        return turn;
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

    public int totalSolider(){
        int result = 0;
        for(Unit temp: units){
            result += temp.getSoldiers();
        }
        return result;
    }
    /**
     * this changes the owner of the provice when the territoy is concured.
     * @param dude
     */
    public void changeOwner(Player dude) {
        this.Owner = dude;
    }
    
    /**
     * @return ArrayList<Unit> return the units.
     */
    public ArrayList<Unit> getUnits() {
        return units;
    }
    
    public void removeUnit(Unit ut){
        this.units.remove(ut);
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

    public void provinceWealthAdder(double money) {
        this.provinceWealth += money;
    }

    public void setWealthprov(double money) {
        this.provinceWealth = 0;
    }

    /**
     * changes the wealth every turn according to the tax rate.
     */
    public void calculateWealth() {
        tax.CalculateWealth();
    }

    /**
     * @return double return the taxRate
     */
    public double getTaxRate() {
        return tax.GetTaxRate();
    }

    /**
     * the taxRate to set
     */
    public void increaseTaxRate() {
        tax.IncreaseTaxRate();
    }

    /**
     * the taxRate to set
     */
    public void decreaseTaxRate() {
        tax.DecreaseTaxRate();
    }

    public double getTax() {
        return provinceWealth*getTaxRate()*0.01;
    }

    public void setTax(int level){
        tax.SetTax(level);
    }

    public boolean generateTroops(String type, String uName, int num) {
        for (Infrastructure i: buildings) {
            if (i instanceof TroopProduction && doesUnitExist(type, uName)) {
                TroopProduction k = (TroopProduction) i;
                boolean check = k.soldierCreator(recruitmentCost, num, type, uName);
                return check;
            }
        }
        return false;
    }

    public boolean doesUnitExist(String type, String uName) {
        for (Unit j : units) {
            if (j.getName().equals(uName) && j.getType().equals(type)) return true;
        }
        return false;
    }

    public ArrayList<Object[]> getSoldierTraining(){
        return soldierTraining;
    }

    public void soldierTrainingadd(Object[] soldier) {
        soldierTraining.add(soldier);
    }

    public void constructNBuilding(Infrastructure temp) {
        buildinConstruction.add(temp);
    }

    public ArrayList<Infrastructure> getBuildinCons() {
        return buildinConstruction;
    }

    public void decreaseBBTime() {
        Iterator<Infrastructure> itr = buildinConstruction.iterator();
        while (itr.hasNext()) {
            Infrastructure infra = itr.next();
            if (infra.getBuildTime() == 1) {
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
    public void setRecruitmentCost(double cost) {
        this.recruitmentCost = cost;
    }

    public double getRecruitCost() {
        return recruitmentCost;
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
    public void setBuidingPrice(double priceR) {
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
    public void setBuildingUpgrade(double price) {
        this.buildingUpgrade = price;
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
                provinceWealth += 300;    //once building has been upgraded, it contributes to the economy.
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