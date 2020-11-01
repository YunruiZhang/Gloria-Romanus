package unsw.backend;

/**
 * class for Wealth goal
 */
public class WealthGoal implements GoalComponent{
    /**
     * check whether the wealth goal is met
     */
    public boolean checkMeet(Player player){
        double totalWealth = 0;
        for(Province temp: player.getProvinces()){
            totalWealth += temp.getProvinceWealth();
        }
        if(totalWealth >= 400000){
            return true;
        }else{
            return false;
        }
    }
}
