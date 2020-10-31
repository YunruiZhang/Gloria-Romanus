package unsw.backend;

public class TreasuryGoal implements GoalComponent {
    public boolean checkMeet(Player player){
        if(player.getGold() >= 100000){
            return true;
        }else{
            return false;
        }
    }
}
