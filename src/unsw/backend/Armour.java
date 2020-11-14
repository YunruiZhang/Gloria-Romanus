package unsw.backend;

import java.util.Arrays;

/**
 * the class for armour all the initial info
 */
public class Armour {
    private double armour;

    /**
     * constructor
     * @param type the type of the unit
     */
    public Armour(String type) {
        String[] a = {"Chariot", "Berserker"};
        String[] b = {"Hopitle", "NetFighter", "Spearman", "Trebuchet"};
        String[] c = {"Pikeman","Crossbowman", "Lancer", "Javelin"};
        String[] d = {"HorseArcher", "Elephant", "Swordsman", "Camel"};
        String[] e = {"Druid", "Cannon", "legionary", "MissileMan"};

        if (Arrays.stream(a).anyMatch(type::equals)) {
            this.armour = 1;
        } else if (Arrays.stream(b).anyMatch(type::equals)) {
            this.armour = 2;
        } else if (Arrays.stream(c).anyMatch(type::equals)) {
            this.armour = 3;
        } else if (Arrays.stream(d).anyMatch(type::equals)){
            this.armour = 4;
        } else if (Arrays.stream(e).anyMatch(type::equals)){
            this.armour = 5;
        } else {
            this.armour = 1;
        }
    }

    /**
     * @return double return the armour
     */
    public double getArmour() {
        System.out.println("armour is -> "+armour);
        return armour;
    }

    /**
     * @param armour the armour to set
     */
    public void setArmour(double armour) {
        this.armour = armour;
    }

}
