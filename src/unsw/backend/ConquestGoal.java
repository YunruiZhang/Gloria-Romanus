package unsw.backend;

/**
 * class for conquestgoal
 */
public class ConquestGoal implements GoalComponent{
    /**
     * check whether the conquest goal is met
     */
    public boolean checkMeet(Player player){
        if(player.getProvinces().size() >= 53){
            return true;
        }else {
            return false;
        }
    }
    
    public String getType(){
        return "conquest";
    }
}