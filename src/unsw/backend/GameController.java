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

    public void nextTurn(){
        this.turn.Notify();
    }

    public void MoveUnit(Player py, ArrayList<Unit> Army, String source, String destt){
        Province src = getProvinceFromString(source);
        if(src == null){
            System.out.println("province does not exist");
            return;
        }
        Province dest = getProvinceFromString(destt);
        if(dest == null){
            System.out.println("province does not exist");
            return;
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
            return;
        }
        removeFromProvince(Army);
        for(Unit tmp: Army){
            tmp.move(dest, point);
        }
    }

    public Unit createUnit(String province, String type, String name){
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
                return inf;
            case "Trebuchet":
            case "MissileMan":
            case "Crossbowman":
            case "Cannon":
                Artillery art = new Artillery(name, pro, type);
                pro.addUnits(art);
                return art;
            case "HorseArcher":
            case "Elephant":
            case "Chariot":
            case "Lancer":
                Cavalry cav = new Cavalry(name, pro, type);
                pro.addUnits(cav);
                return cav;
        }
        return null;

    }

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
        // if(player.upgradeBuilding(type, pro)){
        //     return false;
        // }else{
        //     return true;
        // }
    }

    public boolean Battle(Player py, ArrayList<Unit>Army, String source, String destt){
        Province src = getProvinceFromString(source);
        if(src == null){
            System.out.println("province source does not exist");
            return false;
        } 
        Province dest = getProvinceFromString(destt);
        if(dest == null){
            System.out.println("province destt does not exist");
            return false;
        }   
        if(!adj.Check_adj(src, dest)){
            return false;
        }
        Battle newbt = new Battle(Army, dest.getUnits());
        int result = newbt.StartBattle();
        if(result == 1){
            dest.getOwner().removeProvince(dest);
            py.addProvince(dest);
            return true;
        }
        return false;

    }
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
    
    public void removeFromProvince(ArrayList<Unit> Army){
        Province temp = Army.get(0).getLocation();
        for(Unit temp1: Army){
            temp.removeUnit(temp1);
        }
    }

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
}