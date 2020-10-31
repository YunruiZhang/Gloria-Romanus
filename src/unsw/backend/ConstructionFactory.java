package unsw.backend;

public class ConstructionFactory {

    public boolean constructNewBuilding(String type, Province p, Player player) {
        switch(type) {
            case "TroopProduction":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    TroopProduction temp = new TroopProduction(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Farm":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Farm temp = new Farm(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Market":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Market temp = new Market(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Port":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Port temp = new Port(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Road":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Road temp = new Road(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Smith":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Smith temp = new Smith(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Wall":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Wall temp = new Wall(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }
            
            case "Mine":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Mine temp = new Mine(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return true;
                } else {
                    return false;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }
            
        }
        return false;
    }

    public boolean upgradeBuilding(String type, Province p, Player player) {
        if(!p.checkIfBuildingExistsB(type)) {
            return false;
            //System.out.println("cannot upgrade as buiding does not exist"); //JAVAFX ++++++++++++++++++++++++++
        } else {
            if(player.CheckIfGoldAvailable(p.getBuildingUpgrade()) && !p.CheckIfMAX(type)) {
                player.subGold(p.getBuildingUpgrade());
                p.upgradeBUILDING(type);
                return true;
            } else {
                return false;
                //System.out.println("Requst denies ~ not enough gold or alread on max level");//JAVAFX+++++++++++++++++++++
            }
        }
    }
}
