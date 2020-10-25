package unsw.backend;

abstract public class Unit {
    private int soldiers;
    private String name;
    private Province location;
    private String type;
    private double reduceEnemyDamage;
    private double soldierSpeed;
    private double reduceEnemyDamagePercent;
    private double killDamage;
    private double morale;

    public abstract void move(Province dest);
    public abstract void attact();
    public Unit(int soldiers, String name, Province location, String type, double kd, ){
        this.soldiers = soldiers;
        this.name = name;
        this.location = location;
        this.type = type;
        this.reduceEnemyDamage = 0;
        this.soldierSpeed = 1;
        this.reduceEnemyDamagePercent = 0;
        this.killDamage = 0;
        this.morale = 0;
    }
    public String getName(){
        return name;
    }
    public void soliders_die(int num){
        soldiers -= num;
    }

    public double caculateDefencePoint(){
        double damageLeft = (100 * (1+reduceEnemyDamagePercent)) + reduceEnemyDamage;
        return damageLeft;
    }

    public double caculateAttactPoint(){

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
    public void setSoldiers(int soldiers) {
        this.soldiers = soldiers;
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
     * @param morale the morale to set
     */
    public void setMorale(double morale) {
        this.morale = morale;
    }
}
