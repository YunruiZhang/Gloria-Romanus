package unsw.backend;

import java.util.ArrayList;
import java.util.Collection;


import org.json.JSONArray;
import org.json.JSONException;

public class Player implements Observer{
    private String faction;
    private  ArrayList<Province> provinces;
    private double gold;
    private int turn;
    //private int total_wealth;
    private boolean conquest_goal;
    private boolean treasury_goal;
    private boolean infustructure_goal;

    public Player(String faction){
        this.provinces = new ArrayList<Province>();
        this.gold = 1000;
        this.faction = faction;
        this.conquest_goal = false;
        this.treasury_goal = false;
        this.infustructure_goal = false;
    }

    public void update (Object o){
        this.turn = (int) o;
    }

   

    /**
     * @return String return the faction
     */
    public String getFaction() {
        return faction;
    }

    /**
     * @param faction the faction to set
     */
    public void setFaction(String faction) {
        this.faction = faction;
    }

    /**
     * @return ArrayList<Province> return the provinces
     */
    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    /**
     * @param provinces the provinces to set
     */
    public void setProvinces(ArrayList<Province> provinces) {
        this.provinces = provinces;
    }

    public void addProvince(Province newP){
        this.provinces.add(newP);
    }

    /**
     * @return int return the gold
     */
    public double getGold() {
        return gold;
    }

    /**
     * @param gold the gold to set
     */
    public void setGold(double gold) {
        this.gold = gold;
    }

    /**
     * @param gold adds gold 
     */
    public void addGold(double numGold) {
        this.gold += numGold;
    }

    /**
     * @param gold subtracts gold 
     */
    public void subGold(double numGold) {
        this.gold -= numGold;
    }

    /**
     * @return boolean return the conquest_goal
     */
    public boolean isConquest_goal() {
        return conquest_goal;
    }

    /**
     * @param conquest_goal the conquest_goal to set
     */
    public void setConquest_goal(boolean conquest_goal) {
        this.conquest_goal = conquest_goal;
    }

    /**
     * @return boolean return the treasury_goal
     */
    public boolean isTreasury_goal() {
        return treasury_goal;
    }

    /**
     * @param treasury_goal the treasury_goal to set
     */
    public void setTreasury_goal(boolean treasury_goal) {
        this.treasury_goal = treasury_goal;
    }

    /**
     * @return boolean return the infustructure_goal
     */
    public boolean isInfustructure_goal() {
        return infustructure_goal;
    }

    /**
     * @param infustructure_goal the infustructure_goal to set
     */
    public void setInfustructure_goal(boolean infustructure_goal) {
        this.infustructure_goal = infustructure_goal;
    }

    public boolean constructNewBuilding(String type, Province p) {
        switch(type) {
            case "TroopProduction":
                if(CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    TroopProduction temp = new TroopProduction(p);
                    subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//+++++++++++++++++++++
                }

            case "Farm":
                if(CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Farm temp = new Farm(p);
                    subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//+++++++++++++++++++++
                }

            case "Market":
                if(CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Market temp = new Market(p);
                    subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//+++++++++++++++++++++
                }

            case "Port":
                if(CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Port temp = new Port(p);
                    subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//+++++++++++++++++++++
                }

            case "Road":
                if(CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Road temp = new Road(p);
                    subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//+++++++++++++++++++++
                }

            case "Smith":
                if(CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Smith temp = new Smith(p);
                    subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Wall":
                if(CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Wall temp = new Wall(p);
                    subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }
        }
        return false;
    }

    public boolean upgradeBuilding(String type, Province p) {
        if(!p.checkIfBuildingExistsB(type)) {
            return false;
            //System.out.println("cannot upgrade as buiding does not exist"); //JAVAFX ++++++++++++++++++++++++++
        } else {
            if(CheckIfGoldAvailable(p.getBuildingUpgrade()) && !p.CheckIfMAX(type)) {
                subGold(p.getBuildingUpgrade());
                p.upgradeBUILDING(type);
                return true;
            } else {
                return false;
                //System.out.println("Requst denies ~ not enough gold or alread on max level");//JAVAFX+++++++++++++++++++++
            }
        }
    }

    public boolean CheckIfGoldAvailable(double x) {
        if (gold >= x) {
            return true;
        } else {
            return false;
        }
    }

}

