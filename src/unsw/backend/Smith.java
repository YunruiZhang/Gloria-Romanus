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
                unit.decreaseSoldierSpeed(0.2);
                break;

            case 2:
                unit.setReduceEnemyDamage(0.55);
                unit.decreaseSoldierSpeed(0.3);
                break;

            case 3:
                unit.setReduceEnemyDamage(0.6);
                unit.decreaseSoldierSpeed(0.4);
                break;

            case 4:
                unit.setReduceEnemyDamage(0.625);
                unit.decreaseSoldierSpeed(0.5);
                break;
        }
    }

    public void receiveWeaponBonus(Unit unit) {
        switch(super.getLevel()) {
            case 1:
                unit.increaseDamage(0.20);
                unit.percentIncreaseMorale(0.10);
                break;

            case 2:
                unit.increaseDamage(0.21);
                unit.percentIncreaseMorale(0.15);
                break;

            case 3:
                unit.increaseDamage(0.22);
                unit.percentIncreaseMorale(0.2);
                break;

            case 4:
                unit.increaseDamage(0.23);
                unit.percentIncreaseMorale(0.25);
                break;
        }
    }

    public String getType() {
        return type;
    }
}
