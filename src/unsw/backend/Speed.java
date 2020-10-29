package unsw.backend;

import java.util.Arrays;

public class Speed {
    private double speed;

    public Speed(String type) {
        String[] a = {"MissileMan", "Cannon", "Trebuchet", "Crossbowman"};
        String[] b = {"Hopitle", "Swordsman"};
        String[] c = {"Pikeman", "Javelin", "Spearman"};
        String[] d = {"Elephant", "Camel", "NetFighter", "Berserker"};
        String[] e = {"Druid", "legionary", "Chariot", "HorseArcher", "Lancer"};

        if (Arrays.stream(a).anyMatch(type::equals)) {
            this.speed = 1;
        } else if (Arrays.stream(b).anyMatch(type::equals)) {
            this.speed = 2;
        } else if (Arrays.stream(c).anyMatch(type::equals)) {
            this.speed = 3;
        } else if (Arrays.stream(d).anyMatch(type::equals)){
            this.speed = 4;
        } else if (Arrays.stream(e).anyMatch(type::equals)){
            this.speed = 5;
        } 
    }

    /**
     * @return double return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * decreases the soldier speed by a percentage
     * @param slow
     */
    public void decreasesoldierspeed(double slow) {
        this.speed = this.speed * (1-slow);
    }

}
