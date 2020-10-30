package unsw.backend;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Battle {
    private ArrayList<Unit> MyArmy;
    private ArrayList<Unit> OppositionArmy;
    private int engagementCounter;
    private boolean draw = false;

    public Battle(ArrayList<Unit> MyArmy, ArrayList<Unit> OppositionArmy) {
        this.MyArmy = MyArmy;
        this.OppositionArmy = OppositionArmy;
        this.engagementCounter = 0;
    }

    public boolean StartBattle() {
        while (MyArmy.size() > 0 && OppositionArmy.size() > 0 && !draw) {
            int myIndex = ThreadLocalRandom.current().nextInt(0, MyArmy.size() + 1);
            int oppositionIndex = ThreadLocalRandom.current().nextInt(0, OppositionArmy.size() + 1);
            Unit friend = MyArmy.get(myIndex);
            Unit enemy = OppositionArmy.get(oppositionIndex);
            startSkirmish(friend, enemy);
        }
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

    public double discountedDamage(Unit friend, Unit enemy) {

    }

    public boolean startEngagement(boolean range, Unit friend, Unit enemy){
        if (engagementCounter + 1 > 200) {
            draw = true;
            return false;
        }
        if (range) {
            return getDestroyedLongRange(friend, enemy);
        } else {
            return getDestroyedShortRange(friend, enemy);
        }
    }

    public boolean EngagementChanceCalculator(Unit friend, Unit enemy) {
        double increaseMeleeChance;
        if (friend.getMelee()) {
            increaseMeleeChance = 0.10 * (friend.getSpeed() - enemy.getSpeed());
        } else {
            increaseMeleeChance = 0.10 * (enemy.getSpeed() - friend.getSpeed());
        }
        double chanceMeele = 1/(0.5 + increaseMeleeChance);
        if (new Random().nextDouble() <= chanceMeele) {
            return true;
        } else {
            return false;
        }  
    }

    public boolean getDestroyedLongRange(Unit friend, Unit enemy) {

    }

    public boolean getDestroyedShortRange(Unit friend, Unit enemy) {

    }
}
