package unsw.backend;

public class Smith extends Infrastructure{
    static private int buildTime = 3;
    static private String type = "Smith";
    static private int maxUpgrade = 4;

    public Smith(Province province) {
        super(buildTime, maxUpgrade, type, province);
    }

    public void receiveHelmetBonus(Unit unit) {
        switch(super.getLevel()) {

            case 1:
                unit.setReduceEnemyDamage(1);
                break;

            case 2:
                unit.setReduceEnemyDamage(1.25);
                break;

            case 3:
                unit.setReduceEnemyDamage(1.5);
                break;

            case 4:
                unit.setReduceEnemyDamage(2);
                break;
        }
    }

    public void receiveArmourBonus(Unit unit) {
        switch(super.getLevel()) {

            case 1:
                unit.setReduceEnemyDamagePercent(0.50);
                unit.setSoldierSpeed(0.8);
                break;

            case 2:
                unit.setReduceEnemyDamage(0.55);
                unit.setSoldierSpeed(0.64);
                break;

            case 3:
                unit.setReduceEnemyDamage(0.6);
                unit.setSoldierSpeed(0.512);
                break;

            case 4:
                unit.setReduceEnemyDamage(0.625);
                unit.setSoldierSpeed(0.4096);
                break;
        }
    }

    public void receiveWeaponBonus(Unit unit) {
        switch(super.getLevel()) {
            case 1:
                weaponUp(unit, 0.20, 0.10);
                break;

            case 2:
                weaponUp(unit, 0.205, 0.105);
                break;

            case 3:
                weaponUp(unit, 0.21, 0.11);
                break;

            case 4:
                weaponUp(unit, 0.215, 0.115);
                break;
        }
    }

    public String getType() {
        return type;
    }

    public void weaponUp(Unit unit, double tempDamage, double moraleInc) {
        double dam = unit.getKillDamage() + (unit.getKillDamage() * tempDamage); 
        if (dam < 5) { //5 is the maximum kill damage
            unit.setKillDamage(dam);
        } else {
            unit.setKillDamage(5);
        }
        double morale = unit.getMorale() + (unit.getMorale()*moraleInc);
        if (morale < 5) {
             unit.setMorale(morale);
        } else {
            unit.setMorale(5);
        }
    } 

}
