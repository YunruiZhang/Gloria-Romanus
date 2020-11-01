package unsw.backend;

import java.util.Arrays;

/**
 * the class for morale and all the initial info (unit contain this)
 */
public class Morale {
    private double morale;

    /**
     * constructor
     * @param type the type of the unit
     */
    public Morale(String type) {
        String[] a = {"Camel", "Pikeman"};
        String[] b = {"Hopitle", "NetFighter", "Spearman", "Trebuchet"};
        String[] c = {"Crossbowman", "Lancer", "Javelin"};
        String[] d = {"HorseArcher", "Elephant", "Swordsman"};
        String[] e = {"Druid", "Cannon", "legionary","Chariot", "MissileMan"};
        String[] f = {"Berserker"};

        if (Arrays.stream(a).anyMatch(type::equals)) {
            this.morale = 5;
        } else if (Arrays.stream(b).anyMatch(type::equals)) {
            this.morale = 6;
        } else if (Arrays.stream(c).anyMatch(type::equals)) {
            this.morale = 7;
        } else if (Arrays.stream(d).anyMatch(type::equals)){
            this.morale = 8;
        } else if (Arrays.stream(e).anyMatch(type::equals)){
            this.morale = 9;
        } else if (Arrays.stream(f).anyMatch(type::equals)){
            this.morale = 9999999;
        } 
    }

    /**
     * @return int return the morale
     */
    public double getmorale() {
        return morale;
    }

    /**
     * @param morale the morale to set
     */
    public void setmorale(double num) {
        this.morale = num;
    }

    /**
     * increases the morale of unit
     */
    public void increasemorale(double num) {
        this.morale += num;
    }

    /**
     * decreases the morale of unit
     */
    public void decreasemorale(double num) {
        this.morale -= num;
    }

    /**
     * increase the morale for a percentage
     * @param perCent the percentage
     */
    public void percentIncrease(double perCent) {
        double moral = morale + (morale * perCent);
        if (morale < 9) {
            this.morale = moral;
        } else {
            this.morale = 9;
        }
    }

}
