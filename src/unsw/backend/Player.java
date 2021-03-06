package unsw.backend;

import java.util.ArrayList;

public class Player implements Observer{
    private String faction;
    private  ArrayList<Province> provinces;
    private double gold;
    private int turn;
    GoalSystem goalsystem;
    private int moneyborrowed;


    /**
     * constructor
     * @param faction the fraction of the player
     * @param goalsystem the random generated goal system
     */
    public Player(String faction, GoalSystem goalsystem){
        this.provinces = new ArrayList<Province>();
        this.gold = 10000;
        this.faction = faction;
        this.goalsystem = goalsystem;
        this.moneyborrowed = 0;
    }

    /**
     * observer method for checking goals
     */
    public void update (Object o){
        this.turn = (int) o;
        if(goalsystem.checkMeet(this)){
            System.out.println("Victory!!!");
        }
        this.moneyborrowed = (int)(moneyborrowed*1.1);
    }

    public void borrowmoney(int amount){
        this.moneyborrowed += amount;
    }

    public int getLoan(){
        return this.moneyborrowed;
    }
    public boolean payback(int amount){
        if(amount > this.moneyborrowed || amount > this.gold){
            return false;
        }else{
            this.moneyborrowed -= amount;
            return true;
        }
    }
    public String getGoal(){
        return goalsystem.getType();
    }
    /**
     * get the current turn 
     * @return the current turn
     */
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

    /**
     * add a province unser the player's name
     * @param newP the province
     */
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
     * check whether the gold is avaiable for x amount
     * @param x the amount
     * @return true or false
     */
    public boolean CheckIfGoldAvailable(double x) {
        if (gold >= x) {
            return true;
        } else {
            return false;
        }
    }

}

