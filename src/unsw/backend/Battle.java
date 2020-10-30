package unsw.backend;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Battle {
    private ArrayList<Unit> MyArmy;
    private ArrayList<Unit> OppositionArmy;
    private int engagementCounter;
    private boolean draw = false;
    private int mysize;
    private int enemysize;
    private int myloss;
    private int enemyloss;
    private ArrayList<Unit> cowardfriend;
    private ArrayList<Unit> cowardenemy;

    public Battle(ArrayList<Unit> MyArmy, ArrayList<Unit> OppositionArmy) {
        this.MyArmy = MyArmy;
        this.OppositionArmy = OppositionArmy;
        this.engagementCounter = 0;
        cowardfriend = new ArrayList<Unit>();
        cowardenemy = new ArrayList<Unit>();
    }

    public int StartBattle() {
        while (MyArmy.size() > 0 && OppositionArmy.size() > 0 && !draw) {
            int myIndex = ThreadLocalRandom.current().nextInt(0, MyArmy.size() + 1);
            int oppositionIndex = ThreadLocalRandom.current().nextInt(0, OppositionArmy.size() + 1);
            Unit friend = MyArmy.get(myIndex);
            Unit enemy = OppositionArmy.get(oppositionIndex);
            startSkirmish(friend, enemy);
        }
        if (draw || (MyArmy.size() <= 0 && OppositionArmy.size() <= 0)) return 0;   //nobody won
        if (MyArmy.size() <= 0) return 2;   //opposition won
        return 1; //We won!
    }

    public void startSkirmish(Unit friend, Unit enemy) {
        int x = Boolean.compare(friend.getMelee(), enemy.getMelee());
        boolean resolve = true;
        while (resolve) {
            if (x == 0) {
                if(friend.getMelee() == true){
                    resolve = startEngagement(false, friend, enemy);
                }else{
                    resolve = startEngagement(true, friend, enemy);
                }
            }else{
                boolean temp = EngagementChanceCalculator(friend, enemy);
                if (temp) {
                    resolve = startEngagement(false, friend, enemy);
                } else {
                    resolve = startEngagement(true, friend, enemy);
                }
            }
        }
    }

    public double discountedDamage(Unit a, Unit b) {
        //gets the damage that the b has on a.
        double damage = b.getAttackDamage();
        double reduction = (damage - a.getReduceEnemyDamage())*(1-a.getReduceEnemyDamagePercent());
        if (reduction < 1) {
            return 1;
        } else {
            return reduction;
        }

    }

    public boolean startEngagement(boolean range, Unit friend, Unit enemy){
        mysize = friend.getSoldiers();
        enemysize = enemy.getSoldiers();
        if (engagementCounter++ > 200) {
            draw = true;
            return false;
        }
        boolean ashesh;
        if (range) {
            ashesh = getDestroyedLongRange(friend, enemy);
        } else {
            ashesh = getDestroyedShortRange(friend, enemy);
        }
        if (!ashesh) {
            if (MyArmy.contains(friend)) {
                FleaCalculator(friend, null);
            } else {
                //flee shit enemy
                FleaCalculator(null, enemy);
            }
        } else { 
            FleaCalculator(friend, enemy);
        }
        return ashesh;
    }

    public boolean EngagementChanceCalculator(Unit friend, Unit enemy) {
        double increaseMeleeChance;
        if (friend.getMelee()) {
            increaseMeleeChance = 0.10 * (friend.GetSpeed() - enemy.GetSpeed());
        } else {
            increaseMeleeChance = 0.10 * (enemy.GetSpeed() - friend.GetSpeed());
        }
        if (new Random().nextDouble() <= 0.5 + increaseMeleeChance) {
            return true;
        } else {
            return false;
        }  
    }

    public boolean getDestroyedLongRange(Unit friend, Unit enemy) {
        Random r = new Random();
        if (friend.getMelee()) {
            double damage  = (friend.getSoldiers() * 0.1)*(discountedDamage(friend, enemy)/(friend.GetArmour() + friend.GetShield()))*(1 + r.nextGaussian());
            friend.soliders_die((int)damage);
            myloss = (int)damage;
            enemyloss = 0;
        } else {
            double damagee  = (enemy.getSoldiers() * 0.1)*(discountedDamage(enemy, friend)/(enemy.GetArmour() + enemy.GetShield()))*(1 + r.nextGaussian());
            enemy.soliders_die((int)damagee);
            enemyloss = (int)damagee;
            myloss = 0;
        }
        if (friend.getSoldiers() <= 0 || enemy.getSoldiers() <= 0) {
            removeFromArmy(friend, enemy);
            return false;
        } else return true;
    }

    public boolean getDestroyedShortRange(Unit friend, Unit enemy) {
        Random r = new Random();
        
        double damA = (friend.getSoldiers() * 0.1)*(discountedDamage(friend, enemy)/(friend.GetArmour() + friend.GetShield() + friend.getCSkill()))*(1 + r.nextGaussian());
        double damB = (enemy.getSoldiers() * 0.1)*(discountedDamage(enemy, friend)/(enemy.GetArmour() + enemy.GetShield() + enemy.getCSkill()))*(1 + r.nextGaussian());
        friend.soliders_die((int)damA);
        enemy.soliders_die((int)damB);
        myloss = (int)damA;
        enemyloss = (int)damB;
        if (friend.getSoldiers() <= 0 || enemy.getSoldiers() <= 0) {
            removeFromArmy(friend, enemy);
            return false;
        } else return true;
    }

    public void removeFromArmy(Unit friend, Unit enemy) {
        if (friend.getSoldiers() <= 0) {
            MyArmy.remove(friend);
        }
        if (enemy.getSoldiers() <= 0) {
            OppositionArmy.remove(enemy);
        }
    }

    public void FleaCalculator(Unit friend, Unit enemy) {
        double friendFleaBase = -1;
        double enemyFleaBase = -1;
        if (friend != null) {
            if ((1 - (friend.getMorale() * 0.1)) < 0.05) friendFleaBase = 0.05;
            else friendFleaBase = 1 - (friend.getMorale() * 0.1);
        }
        if (enemy != null) {
            if ((1 - (enemy.getMorale() * 0.1)) < 0.05) enemyFleaBase = 0.05;
            else enemyFleaBase = 1 - (enemy.getMorale() * 0.1);
        }
        if (friendFleaBase != -1) friendFleaBase += (((myloss/mysize)/(enemyloss/enemysize))*(0.1));
        if (enemyFleaBase != -1) enemyFleaBase += (((enemyloss/enemysize)/(myloss/mysize))*(0.1));
        if(friendFleaBase != -1){
            boolean friendHasFlee = FleeChanceCaculater(friendFleaBase);
            if(friendHasFlee == true){
                if(enemy == null ){
                    MyArmy.remove(friend);
                    cowardfriend.add(friend);
                }else{
                    MyArmy.remove(friend);
                    boolean temp = Routing(friend, enemy);
                    if(temp = true){
                        cowardfriend.add(friend);
                    }
                }
            }
        }
        if(enemyFleaBase != -1){
            boolean enemyHasFlee = FleeChanceCaculater(enemyFleaBase);
            if(enemyHasFlee == true){
                if(friend == null){
                    OppositionArmy.remove(enemy);
                    cowardenemy.add(enemy);
                }else{
                    OppositionArmy.remove(enemy);
                    Routing(enemy, friend);
                    boolean temp = Routing(enemy, friend);
                    if(temp = true){
                        cowardenemy.add(enemy);
                    }
                }
            }
        }
        

    }

    public boolean FleeChanceCaculater (double chance){
        if (new Random().nextDouble() <= chance) {
            return true;
        } else {
            return false;
        }  
    }

    public boolean Routing(Unit coward, Unit chaseing){
        boolean flee = CanTheyEscape(chaseing, coward);
        if(flee){
            return true;
        }else{
            while(!flee){
                Random r = new Random();
                double damA = (coward.getSoldiers() * 0.1)*(discountedDamage(coward, chaseing)/(coward.GetArmour() + coward.GetShield() + coward.getCSkill()))*(1 + r.nextGaussian());
                engagementCounter++;
                coward.soliders_die((int)damA);
                if(coward.getSoldiers() <= 0){
                    return false;
                }
                flee = CanTheyEscape(chaseing, coward);
            }
        }
        return flee;
    }

    public boolean CanTheyEscape(Unit police, Unit flee){
        double chance = 0.5 + (0.1*(flee.GetSpeed() - police.GetSpeed()));
        return FleeChanceCaculater(chance);
    }
}
