package unsw.backend;

public class GoalGroup implements GoalComponent{
    // goal group can have two goals or 1 goalgroup and 1 goal
    private GoalComponent goal1;
    private GoalComponent goal2;
    private int operator;
    public GoalGroup(GoalComponent goal1, GoalComponent goal2, int operator){
        this.goal1 = goal1;
        this.goal2 = goal2;
        this.operator = operator;
    }
    public boolean checkMeet(Player player){
        // if operator is 1 do &&
        if(operator == 1){
            return goal1.checkMeet(player) && goal2.checkMeet(player);
        }else{
            // if operator is 2 do ||
            return goal1.checkMeet(player) || goal2.checkMeet(player);
        }
    }
}
