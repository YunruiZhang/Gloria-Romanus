package unsw.backend;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BattleExtended {
    ArrayList<Unit> Myarmy;
    ArrayList<Unit> Enemyarmy;
    Province src;
    Province dest;
    public BattleExtended(ArrayList<Unit> Myarmy, ArrayList<Unit> Enemyarmy, Province src, Province dest){
        this.Myarmy = Myarmy;
        this.Enemyarmy = Enemyarmy;
        this.src = src;
        this.dest = dest;
    }

    public int StartBattle(){
        double MyarmyStrength = 0;
        double EnemyarmyStrength = 0;
        int result;
        for(Unit temp : Myarmy){
            if(temp.getSoldiers() != 0){
                MyarmyStrength += temp.getSoldiers() * temp.getAttackDamage() * temp.GetArmour();
            }
        }
        for(Unit temp1 : Enemyarmy){
            if(temp1.getSoldiers() != 0){
                EnemyarmyStrength += temp1.getSoldiers() * temp1.getAttackDamage() * temp1.GetArmour();
            }
        }
        double winChance = MyarmyStrength/(MyarmyStrength + EnemyarmyStrength);
        if( new Random().nextDouble() <= winChance ){
            result = 1; // we win
            Enemyarmy.clear();
            double mylosspercentage = EnemyarmyStrength/(MyarmyStrength + EnemyarmyStrength);
            if(mylosspercentage == 0){
                mylosspercentage = 0.1;
            }
            for(Unit tp1 : Myarmy){
                double random1 = ThreadLocalRandom.current().nextDouble(0, mylosspercentage);
                double soliders = (double) tp1.getSoldiers();
                int dies = (int)Math.ceil(soliders*random1);
                tp1.soliders_die(dies);
            }
            dest.getUnits().addAll(Myarmy);
        }else{
            result = 2; // we loss
            double Enemylosspercentage = MyarmyStrength/(MyarmyStrength + EnemyarmyStrength);
            if(Enemylosspercentage == 0){
                Enemylosspercentage = 0.1;
            }
            for(Unit tp : Enemyarmy){
                double random = ThreadLocalRandom.current().nextDouble(0, Enemylosspercentage);
                double soliders = (double) tp.getSoldiers();
                int dies = (int)Math.ceil(soliders*random);
                tp.soliders_die(dies);
            }
            Myarmy.clear();
        }
        return result;
    }
}
