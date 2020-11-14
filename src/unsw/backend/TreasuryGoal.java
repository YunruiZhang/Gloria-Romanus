package unsw.backend;

/**
 * class for treasurygoal
 */
public class TreasuryGoal implements GoalComponent {
    /**
     * check the treasury goal
     */
    public boolean checkMeet(Player player){
        if(player.getGold() >= 100000){
            return true;
        }else{
            return false;
        }
    }

    public String getType(){
        return "treasure";
    }
}
