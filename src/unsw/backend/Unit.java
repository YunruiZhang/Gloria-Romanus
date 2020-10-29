package unsw.backend;

import java.util.Arrays;

abstract public class Unit {
    private int soldiers;
    private String name;
    private Province location;
    private String type;
    private boolean melee;
    private double reduceEnemyDamage;
    private double reduceEnemyDamagePercent;
    private int movementPoint;

    public abstract void move(Province dest, int point);
    public Unit(String name, Province location, String type, String ClassName, int MovePoint){
        this.name = name;
        this.location = location;
        this.type = type;
        this.reduceEnemyDamage = 0;
        this.reduceEnemyDamagePercent = 0;
        this.soldiers = 0;
        String[] ranged = {"ArcherMan","HorseArcher", "missileInfantry"};
        if (Arrays.stream(ranged).anyMatch(type::equals) || ClassName.equals("Artillery")) {
            melee = false; 
        } else {
            melee = true; 
        }
        this.movementPoint = MovePoint;
    }
    public void setMovePoint(int point){
        this.movementPoint = point;
    }
    public void SubtractPoint(int point){
        this.movementPoint -= point;
    }
    public int getPoint(){
        return this.movementPoint;
    }
    public String getName(){
        return name;
    }
    public void soliders_die(int num){
        soldiers -= num;
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

    
}
