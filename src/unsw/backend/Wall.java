package unsw.backend;

public class Wall extends Infrastructure{
    //these provide buffers to all troops defending a province. 
    //They have a random chance of causing damage to attacking troops 
    //if upgraded to have archer towers or ballista towers. 
    //Towers have infinite morale, and can only be damaged by artillery (but are instantly repaired without cost after the battle).
    static private String type = "Wall";
    static private int BuildTime = 4;
    static private int maxUpgrade = 3;

    public Wall (Province province) {
        super(BuildTime, maxUpgrade, type, province);
    }

}
