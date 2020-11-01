package unsw.backend;

import java.util.ArrayList;
import java.util.Random;

/**
 * the goal system with composite design patten
 */
public class GoalSystem implements GoalComponent{
    GoalComponent goal; 
    /**
     * constructor random choose goal/goals
     */
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

    /**
     * helper to randomly choose one goal
     * @return
     */
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

    /**
     * call this method to cheek whether the condition is met
     */
    public boolean checkMeet(Player player){
        return goal.checkMeet(player);
    }

}
