package unsw.backend;

public class WealthGoal implements GoalComponent{
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
