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
                    Province temp = new Province(temparray.getString(i), key);
                    provinces.add(temp);
                    this.turn.attach(temp);
                } 
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayer(String faction){
        Player temp = new Player(faction);
        player.add(temp);
        this.turn.attach(temp);
        for(Province temp1: this.provinces){
            if(temp1.getFaction().equals(faction)){
                temp1.changeOwner(temp);
                temp.addProvince(temp1);
            }
        }

    }

    public void nextTurn(){
        this.turn.Notify();
    }

    public void MoveUnit(Player py, ArrayList<Unit> Army, Province src, Province dest){
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

    public void createUnit(Province pro, String type, String name){
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
                break;
            case "Trebuchet":
            case "MissileMan":
            case "Crossbowman":
            case "Cannon":
                Artillery art = new Artillery(name, pro, type);
                pro.addUnits(art);
                break;
            case "HorseArcher":
            case "Elephant":
            case "Chariot":
            case "Lancer":
                Cavalry cav = new Cavalry(name, pro, type);
                pro.addUnits(cav);
                break;
        }

    }

    public boolean addsolider(Player py, Province pro, Unit unit, int num){
        if(!pro.getOwner().equals(py)){
            return false;
        }
        if(!pro.generateTroop(unit.getType(), unit.getName())){
            return false;
        }else{
            return true;
        }
        
    }

    public boolean bulid(Player py, String type, Province pro){
        ConstructionFactory factory = new ConstructionFactory();
        if(!pro.getOwner().equals(py)){
            return false;
        }
        if (!factory.constructNewBuilding(type, pro, py)){
            return false;
        } else {
            return true;
        }
        // if(!player.constructNewBuilding(type, pro)){
        //     return false;
        // }else{
        //     return true;
        // }
    }

    public boolean upgrade(Player py, String type, Province pro){
        ConstructionFactory factory = new ConstructionFactory();
        if(!pro.getOwner().equals(py)){
            return false;
        }
        if(factory.upgradeBuilding(type, pro, py)){
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

    public boolean Battle(Player py, ArrayList<Unit>Army, Province src, Province dest){
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
    public boolean setTax(Player py, Province pro, int level){
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
}