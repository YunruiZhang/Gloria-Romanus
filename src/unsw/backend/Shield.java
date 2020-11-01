package unsw.backend;

import java.util.Arrays;

/**
 * the class for shield and all the initial info (unit contain this)
 */
public class Shield {
    private double shield;

    /**
     * constructor
     * @param type the type of the unit
     */
    public Shield(String type) {
        String[] a = {"Berserker", "NetFighter"};
        String[] b = {"Hopitle", "Spearman", "Trebuchet", "Crossbowman", "Lancer"};
        String[] c = {"Pikeman", "Javelin", "Chariot", "MissileMan"};
        String[] d = {"HorseArcher", "Elephant", "Swordsman", "Camel", "Druid", "Cannon", "legionary"};

        if (Arrays.stream(a).anyMatch(type::equals)) {
            this.shield = 0;
        } else if (Arrays.stream(b).anyMatch(type::equals)) {
            this.shield = 1;
        } else if (Arrays.stream(c).anyMatch(type::equals)) {
            this.shield = 2;
        } else if (Arrays.stream(d).anyMatch(type::equals)){
            this.shield = 3;
        } 
    }

    /**
     * @return double return the shield
     */
    public double getShield() {
        return shield;
    }

    /**
     * @param shield the shield to set
     */
    public void setShield(double shield) {
        this.shield = shield;
    }

}
