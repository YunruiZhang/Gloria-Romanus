package unsw.backend;

import java.util.ArrayList;

public class Player implements Observer{
    private String faction;
    private  ArrayList<Province> provinces;
    private double gold;
    private int turn;
    GoalSystem goalsystem;

    public Player(String faction, GoalSystem goalsystem){
        this.provinces = new ArrayList<Province>();
        this.gold = 10000;
        this.faction = faction;
        this.goalsystem = goalsystem;
    }

    public void update (Object o){
        this.turn = (int) o;
        if(goalsystem.checkMeet(this)){
            System.out.println("Victory!!!");
        }
    }

    public int getCurrentTurn() {
        return turn;
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

    public void removeProvince(Province pro){
        this.provinces.remove(pro);
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

    public boolean CheckIfGoldAvailable(double x) {
        if (gold >= x) {
            return true;
        } else {
            return false;
        }
    }

}

