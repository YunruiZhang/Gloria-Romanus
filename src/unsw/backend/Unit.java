package unsw.backend;

import java.util.Arrays;

abstract public class Unit {
    private int soldiers;
    private String name;
    private Province location;
    private String type;
    private boolean melee;
    private double reduceEnemyDamage;//??????????????????????
    private double reduceEnemyDamagePercent;//???????????????
    private Morale morale;
    private AttackDamage attackDamage;
    private Armour armour;
    private Shield shield;
    private CombatSkill skill;
    private Speed speed;
    private int movementPoint;

    public abstract void move(Province dest, int point);
    public Unit(String name, Province location, String type, String ClassName, int MovePoint){
        this.name = name;
        this.location = location;
        this.type = type;
        this.reduceEnemyDamage = 0;
        this.reduceEnemyDamagePercent = 0;
        this.soldiers = 0;
        this.attackDamage = new AttackDamage(type);
        this.armour = new Armour(type);
        this.shield = new Shield(type);
        this.skill = new CombatSkill(type);
        this.speed = new Speed(type);
        this.morale = new Morale(type);
        String[] ranged = {"HorseArcher", "MissileMan", "Javelin", "Trebuchet", "Crossbowman", "Cannon"};
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

    /**
     * @return double return the morale
     */
    public double getMorale() {
        return morale.getmorale();
    }

    /**
     * increases the morale of unit
     */
    public void increaseMorale(double num) {
        morale.increasemorale(num);
    }

    /**
     * decreases the morale of unit
     */
    public void decreaseMorale(double num) {
        morale.decreasemorale(num);
    }

    /**
     * @param morale the morale to set
     */
    public void setMorale(double num) {
        morale.setmorale(num);
    }

    /**
     * @param perCent increase the morale by a percentage
     */
    public void percentIncreaseMorale(double perCent) {
        morale.percentIncrease(perCent);
    }

    /**
     * @param slow decreases the soldier speed by a percentage
     */
    public void decreaseSoldierSpeed(double slow) {
        speed.decreasesoldierspeed(slow);
    }

    /**
     * @return the attack damage the unit can cause
     */
    public double getAttackDamage() {
        return attackDamage.getDamage();
    }

    /**
     * @param damage sets the damage the unit can cause.
     */
    public void increaseDamage(double perCent) {
        attackDamage.increaseAttackDamage(perCent);
    }

    /**
     * @return if the unit is of type melee.
     */
    public boolean getMelee() {
        return melee;
    }

    /**
     * @return the speed of the unit.
     */
    public double GetSpeed() {
        return speed.getSpeed();
    }

    /**
     * @return the armour points of the unit
     */
    public double GetArmour() {
        return armour.getArmour();
    }

    /**
     * @return the shield points of the unit
     */
    public double GetShield() {
        return shield.getShield();
    }

    /**
     * @return the self defence ability of the unit
     */
    public double getCSkill() {
        return skill.getSkill();
    }
}
