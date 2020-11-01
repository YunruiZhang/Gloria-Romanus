package unsw.backend;

/**
 * factory design patten for building infustructure
 */
public class ConstructionFactory {

    /**
     * build the new building
     * @param type the type of the building
     * @param p the province to build in
     * @param player the palyer who want to build 
     * @return return the observer to attech
     */
    public Observer constructNewBuilding(String type, Province p, Player player) {
        switch(type) {
            case "TroopProduction":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    TroopProduction temp = new TroopProduction(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return temp;
                } else {
                    return null;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Farm":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Farm temp = new Farm(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return null;
                } else {
                    return null;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Market":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Market temp = new Market(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return temp;
                } else {
                    return null;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Port":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Port temp = new Port(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return null;
                } else {
                    return null;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Road":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Road temp = new Road(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return null;
                } else {
                    return null;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Smith":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Smith temp = new Smith(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return null;
                } else {
                    return null;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }

            case "Wall":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Wall temp = new Wall(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return null;
                } else {
                    return null;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }
            
            case "Mine":
                if(player.CheckIfGoldAvailable(p.getBuidingPrice()) && !p.checkIfBuildingExistsA(type) && !p.checkIfBuildingExistsB(type)) {
                    Mine temp = new Mine(p);
                    player.subGold(p.getBuidingPrice());
                    p.constructNBuilding(temp);
                    return temp;
                } else {
                    return null;
                    //System.out.println("not enough gold available or building exists");//JAVAFX+++++++++++++++++++++
                }
            
        }
        return null;
    }

    /**
     * upgrade a existing building 
     * @param type the type of the building
     * @param p the province
     * @param player the player who want to upgrade
     * @return false for can not upgrade or buiding is not built yet
     */
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
