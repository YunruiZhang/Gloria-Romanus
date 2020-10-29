package unsw.backend;

import java.util.Arrays;

public class Armour {
    private double armour;

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
        } 
    }

    /**
     * @return double return the armour
     */
    public double getArmour() {
        return armour;
    }

    /**
     * @param armour the armour to set
     */
    public void setArmour(double armour) {
        this.armour = armour;
    }

}
