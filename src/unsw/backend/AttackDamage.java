package unsw.backend;

import java.util.Arrays;

/**
 * the class for Attack damage and all the initial info (unit contain this)
 */
public class AttackDamage {
    private double damage;

    /**
     * constructor
     * @param type the type of the unit
     */
    public AttackDamage(String type) {
        String[] a = {"Chariot", "Lancer"};
        String[] b = {"Hopitle", "NetFighter", "Camel"};
        String[] c = {"Pikeman", "Spearman", "Trebuchet", "Javelin"};
        String[] d = {"HorseArcher", "Elephant", "Berserker", "Swordsman"};
        String[] e = {"Crossbowman", "Druid", "Cannon", "legionary", "MissileMan"};

        if (Arrays.stream(a).anyMatch(type::equals)) {
            this.damage = 1;
        } else if (Arrays.stream(b).anyMatch(type::equals)) {
            this.damage = 2;
        } else if (Arrays.stream(c).anyMatch(type::equals)) {
            this.damage = 3;
        } else if (Arrays.stream(d).anyMatch(type::equals)){
            this.damage = 4;
        } else if (Arrays.stream(e).anyMatch(type::equals)){
            this.damage = 5;
        }
    }
    

    /**
     * @return double return the damage
     */
    public double getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(double damage) {
        this.damage = damage;
    }

    /**
     * increase the attack damage
     * @param perCent the percentage he want to increase
     */
    public void increaseAttackDamage(double perCent) {
        double dam = damage + (damage * perCent); 
        if (dam < 5) { //5 is the maximum attack damage
            this.damage = dam;
        } else {
            dam = 5;
        }
    }

}
