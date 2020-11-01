package unsw.backend;

import java.util.ArrayList;
import java.util.Random;

public class GoalSystem implements GoalComponent{
    GoalComponent goal; 
    public GoalSystem(){
        Random random = new Random();
        int num =  random.nextInt(2 - 1 + 1) + 1;
        switch (num){
            case 1:
                goal =  chooseOneGoal();
            case 2:
                GoalComponent temp1 = chooseOneGoal();
                GoalComponent temp2 = chooseOneGoal();
                while(temp1.getClass().equals(temp2.getClass())){
                    temp2 = chooseOneGoal();
                }
                int num1 =  random.nextInt(2 - 1 + 1) + 1;
                goal = new GoalGroup(temp1, temp2, num1); 
        }
    }

    public GoalComponent chooseOneGoal(){
        Random random = new Random();
        int num1 =  random.nextInt(3 - 1 + 1) + 1;
        switch(num1){
            case 1:
                return new TreasuryGoal();
            case 2:
                return new ConquestGoal();
        }
        return new WealthGoal();
        
    }

    public boolean checkMeet(Player player){
        return goal.checkMeet(player);
    }

}
