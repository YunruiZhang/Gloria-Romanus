package unsw.backend;

public class ConquestGoal implements GoalComponent{
    public boolean checkMeet(Player player){
        if(player.getProvinces().size() >= 53){
            return true;
        }else {
            return false;
        }
    }
    
}
