package unsw.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.json.*;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * the class control the whole game
 */
public class GameController{
    private ArrayList<Player> player;
    private ArrayList<Province> provinces;
    private Turn turn = new Turn();
    Adjacent adj = new Adjacent();
    public GameController(){
        provinces = new ArrayList<Province>();
        player = new ArrayList<Player>();
        try{
            String content = Files.readString(Paths.get("src/unsw/gloriaromanus/initial_province_ownership.json"));
            JSONObject jsonprovince = new JSONObject(content);
            Iterator<String> keys = jsonprovince.keys();

            while(keys.hasNext()){
                String key = keys.next();
                JSONArray temparray = jsonprovince.getJSONArray(key);
                for(int i = 0; i < temparray.length(); i++){
                    Province temp = new Province(temparray.getString(i), key, null);
                    provinces.add(temp);
                   
                } 
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the player according to faction
     * @param faction the faction
     * @return
     */
    public Player setPlayer(String faction){
        GoalSystem newsystem = new GoalSystem();
        Player temp = new Player(faction, newsystem);
        player.add(temp);
        this.turn.attach(temp);
        for(Province temp1: this.provinces){
            if(temp1.getFaction().equals(faction)){
                temp1.changeOwner(temp);
                temp.addProvince(temp1);
                this.turn.attach(temp1);
            }
        }
        return temp;

    }
    /**
     * next turn call observer
     */
    public void nextTurn(){
        this.turn.incTurn();
    }

    /**
     * Move the unit in the shortest path, if no path of move point not enough print error
     * @param py The player
     * @param Army the list of Units
     * @param source the start province
     * @param destt the dest province
     */
    public boolean MoveUnit(Player py, ArrayList<Unit> Army, String source, String destt){
        Province src = getProvinceFromString(source);
        if(src == null){
            System.out.println("province does not exist");
            return false;
        }
        Province dest = getProvinceFromString(destt);
        if(dest == null){
            System.out.println("province does not exist");
            return false;
        }    
        int avaiablePoint = 9999;
        for(Unit temp : Army){
            if(avaiablePoint > temp.getPoint()){
                avaiablePoint = temp.getPoint();
            }
        }
        int point = adj.ShortestPath(src, dest, py, provinces);
        if(point > avaiablePoint){
            System.out.println("Can't move the army");
            return false;
        }
        removeFromProvince(Army);
        for(Unit tmp: Army){
            tmp.move(dest, point);
        }
        //dest.getUnits().addAll(Army);
        return true;
    }

    /**
     * create a unit
     * @param province the province to put in the unit
     * @param type the type of the unit
     * @param name the name of the unit no puplicate
     * @return the new unit
     */
    public Unit createUnit(String province, String type, String name){
        for(Province tmp: provinces){
            for(Unit tmp1: tmp.getUnits()){
                if(tmp1.getName().equals(name)){
                    return null;
                }
            }
        }
        Province pro = getProvinceFromString(province);
        if(pro == null){
            System.out.println("province does not exist");
            return null;
        }   
        switch (type){
            case "Hopitle":
            case "NetFighter":
            case "Pikeman":
            case "Spearman":
            case "Javelin":
            case "Berserker":
            case "Swordsman":
            case "Druid":
            case "legionary":
                Infantry inf = new Infantry(name, pro, type);
                pro.addUnits(inf);
                turn.attach(inf);
                return inf;
            case "Trebuchet":
            case "MissileMan":
            case "Crossbowman":
            case "Cannon":
                Artillery art = new Artillery(name, pro, type);
                pro.addUnits(art);
                turn.attach(art);
                return art;
            case "HorseArcher":
            case "Elephant":
            case "Chariot":
            case "Lancer":
                Cavalry cav = new Cavalry(name, pro, type);
                pro.addUnits(cav);
                turn.attach(cav);
                return cav;
        }
        return null;

    }

    /**
     * add solider to the unit
     * @param py the player who want to add solider
     * @param province the province to add
     * @param unit the unit
     * @param num the number of unit to add
     * @return success or not
     */
    public boolean addsolider(Player py, String province, Unit unit, int num){
        Province pro = getProvinceFromString(province);
        if(pro == null){
            System.out.println("province does not exist");
            return false;
        }  
        if(!pro.getOwner().equals(py)){
            return false;
        }
        if(!pro.generateTroops(unit.getType(), unit.getName(), num)){
            return false;
        }else{
            return true;
        }
        
    }

    /**
     * build a new building
     * @param py the player who want to build
     * @param type the type of the building
     * @param province the province want to build in
     * @return success or not
     */
    public boolean bulid(Player py, String type, String province){
        //get num in constructing
        Province pro = getProvinceFromString(province);
        if(pro == null){
            System.out.println("province does not exist");
            return false;
        }  
        int num = pro.getBuildinCons().size();
        ConstructionFactory factory = new ConstructionFactory();
        if(!pro.getOwner().equals(py)){
            return false;
        }
        Observer newbuilding = factory.constructNewBuilding(type, pro, py);
        if (newbuilding != null){
            turn.attach(newbuilding);
            return true;
        } else {
            if(pro.getBuildinCons().size() != num){
                return true;
            }else{
                return false;
            }
        }
        // check if num exist
    }

    /**
     * upgrade a existing building
     * @param py the player who want to add
     * @param type the type of the building
     * @param province the province to add
     * @return success or not
     */
    public boolean upgrade(Player py, String type, String province){
        Province pro = getProvinceFromString(province);
        if(pro == null){
            System.out.println("province does not exist");
            return false;
        }  
        ConstructionFactory factory = new ConstructionFactory();
        if(!pro.getOwner().equals(py)){
            return false;
        }
        if(!factory.upgradeBuilding(type, pro, py)){
            return false;
        }else{
            return true;
        }
        
    }

    /**
     * Start a battle all the units in destt are in enemy army
     * @param py The player who want to start the battle
     * @param Army the player's army
     * @param source atteck from here
     * @param destt atteck here
     * @return 0 is draw 1 is win 2 is lose 3 is error
     */
    public int Battle(Player py, ArrayList<Unit>Army, String source, String destt){
        Province src = getProvinceFromString(source);
        if(src == null){
            System.out.println("province source does not exist");
            return 3;
        } 
        Province dest = getProvinceFromString(destt);
        if(dest == null){
            System.out.println("province destt does not exist");
            return 3;
        }   
        if(!adj.Check_adj(src, dest)){
            return 3;
        }
        
        BattleExtended newbt = new BattleExtended(Army, dest.getUnits(), src, dest);
        int result = newbt.StartBattle();
        if(result == 1){
            dest.getOwner().removeProvince(dest);
            py.addProvince(dest);
            for(Province tpp: provinces){
                if(tpp.getName().equals(destt)){
                    tpp.changeOwner(py);
                    tpp.setFaction(py.getFaction());
                }
            }
        }
        src.getUnits().removeAll(Army);
        return result;

    }

    /**
     * set the tax rate on a province
     * @param py the player who want to set the tax rate
     * @param province the province to set
     * @param level the level we want to set
     * @return success or not
     */
    public boolean setTax(Player py, String province, int level){
        Province pro = getProvinceFromString(province);
        if(pro == null){
            System.out.println("province does not exist");
            return false;
        }  
        if(!pro.getOwner().equals(py)){
            return false;
        }
        pro.setTax(level);
        return true;
    }  
    
    /**
     * helper to remove units from a province
     * @param Army the units
     */
    public void removeFromProvince(ArrayList<Unit> Army){
        Province temp = Army.get(0).getLocation();
        //String name = temp
        for(Unit temp1: Army){
            temp.removeUnit(temp1);
        }

    }

    /**
     * helper to convert string to province
     * @param province the string
     * @return the province
     */
    public Province getProvinceFromString(String province){
        Province pro = null;
        for(Province temp: provinces){
            if(temp.getName().equals(province)){
                pro = temp;
                break;
            }
        }
        return pro;
    }

    public Unit getUnitFromString(String name){
        for(Province temp: provinces){
            for(Unit temp1: temp.getUnits()){
                if(temp1.getName().equals(name)){
                    return temp1;
                }
            }
        }
        return null;
    }

    public ArrayList<Province> getAllPovinces(){
        return provinces;
    }
}