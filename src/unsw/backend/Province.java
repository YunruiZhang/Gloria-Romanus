package unsw.backend;

import java.util.ArrayList;


public class Province implements Observer{
    private String Name;
    private Player Owner;
    private ArrayList<Unit> units;
    private ArrayList<Infrastructure> buildings;
    private double provinceWealth;
    private double taxRate;
    private double recruitmentCost = 500;
    private int trainTime = 2;
    private int turn;

    public Province() {

    }

    public void update (Object o){
        this.turn = (int) o;
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

    public void addToUnit(String type, int num, String uName) {
        for (Unit i: units) {
            if (i.getType().equals(type)) {
                i.addSoldiers(num);
            }
        }
    }

    public void findProductionBuilding(String type, String uName) {
        for (Infrastructure i: buildings) {
            if (i instanceof TroopProduction) {
                TroopProduction temp = (TroopProduction) i;
                addToUnit(type, temp.generate(type), uName);
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
}
