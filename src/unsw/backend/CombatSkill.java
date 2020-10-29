package unsw.backend;

import java.util.Arrays;

public class CombatSkill {
    private double skill;

    public CombatSkill(String type) {
        String[] a = {"Cannon", "MissileMan", "HorseArcher", "Trebuchet", "Javelin", "Crossbowman"};
        String[] b = {"Hopitle", "Berserker", "Camel"};
        String[] c = {"Pikeman", "Chariot"};
        String[] d = {"Elephant", "Swordsman", "Spearman"};
        String[] e = {"Lancer", "NetFighter"};
        String[] f = {"Druid"};
        String[] g = {"legionary"};

        if (Arrays.stream(a).anyMatch(type::equals)) {
            this.skill = 0;
        } else if (Arrays.stream(b).anyMatch(type::equals)) {
            this.skill = 1;
        } else if (Arrays.stream(c).anyMatch(type::equals)) {
            this.skill = 2;
        } else if (Arrays.stream(d).anyMatch(type::equals)){
            this.skill = 3;
        } else if (Arrays.stream(e).anyMatch(type::equals)){
            this.skill = 4;
        } else if (Arrays.stream(f).anyMatch(type::equals)){
            this.skill = 5;
        } else if (Arrays.stream(g).anyMatch(type::equals)){
            this.skill = 6;
        } 
    }

    /**
     * @return double return the skill
     */
    public double getSkill() {
        return skill;
    }

    /**
     * @param skill the skill to set
     */
    public void setSkill(double skill) {
        this.skill = skill;
    }

}
