package unsw.backend;

import java.util.Arrays;

abstract public class Unit {
    private int soldiers;
    private String name;
    private Province location;
    private String type;
    private boolean melee;
    private double reduceEnemyDamage;
    private double soldierSpeed;
    private double reduceEnemyDamagePercent;
    private double killDamage;
    private double morale;
    private double basicDefencepoint;

    public abstract void move(Province dest);
    public Unit(String name, Province location, String type, double killDamage, double basicDefencepoint, String ClassName){
        this.name = name;
        this.location = location;
        this.type = type;
        this.reduceEnemyDamage = 0;
        this.soldierSpeed = 1;
        this.reduceEnemyDamagePercent = 0;
        this.killDamage = killDamage;
        this.morale = 0;
        this.basicDefencepoint = basicDefencepoint;
        this.soldiers = 0;
        String[] ranged = {"ArcherMan","HorseArcher", "missileInfantry"};
        if (Arrays.stream(ranged).anyMatch(type::equals) || ClassName.equals("Artillery")) {
            melee = false; 
        } else {
            melee = true; 
        }
    }
    public String getName(){
        return name;
    }
    public void soliders_die(int num){
        soldiers -= num;
    }

    public double caculateDefencePoint(){
        double defencepoint = (basicDefencepoint * (1+reduceEnemyDamagePercent)) + reduceEnemyDamage;
        return defencepoint*soldiers;
    }

    public double caculateAttactPoint(){
        return killDamage*soldiers;
    }

    /**
     * @return int return the soldiers
     */
    public int getSoldiers() {
        return soldiers;
    }

    /**
     * @param soldiers the soldiers to set
     */
    public void addSoldiers(int soldiers) {
        this.soldiers += soldiers;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Province return the location
     */
    public Province getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Province location) {
        this.location = location;
    }

    /**
     * @return String return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return double return the reduceEnemyDamage
     */
    public double getReduceEnemyDamage() {
        return reduceEnemyDamage;
    }

    /**
     * @param reduceEnemyDamage the reduceEnemyDamage to set
     */
    public void setReduceEnemyDamage(double reduceEnemyDamage) {
        this.reduceEnemyDamage = reduceEnemyDamage;
    }

    /**
     * @return double return the soldierSpeed
     */
    public double getSoldierSpeed() {
        return soldierSpeed;
    }

    /**
     * @param soldierSpeed the soldierSpeed to set
     */
    public void setSoldierSpeed(double soldierSpeed) {
        this.soldierSpeed = soldierSpeed;
    }

    /**
     * @return double return the reduceEnemyDamagePercent
     */
    public double getReduceEnemyDamagePercent() {
        return reduceEnemyDamagePercent;
    }

    /**
     * @param reduceEnemyDamagePercent the reduceEnemyDamagePercent to set
     */
    public void setReduceEnemyDamagePercent(double reduceEnemyDamagePercent) {
        this.reduceEnemyDamagePercent = reduceEnemyDamagePercent;
    }

    /**
     * @return double return the killDamage
     */
    public double getKillDamage() {
        return killDamage;
    }

    /**
     * @param killDamage the killDamage to set
     */
    public void setKillDamage(double killDamage) {
        this.killDamage = killDamage;
    }

    /**
     * @return double return the morale
     */
    public double getMorale() {
        return morale;
    }

    /**
     * increases the morale of unit
     */
    public void increaseMorale(int num) {
        this.morale += num;
    }

    /**
     * decreases the morale of unit
     */
    public void decreaseMorale(int num) {
        this.morale -= num;
    }

    /**
     * @param morale the morale to set
     */
    public void setMorale(double morale) {
        this.morale = morale;
    }
}
