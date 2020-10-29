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
    private Player player;
    private ArrayList<Province> provinces;
    private Turn turn = new Turn();
    Adjacent adj = new Adjacent();
    public GameController(){
        provinces = new ArrayList<Province>();
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
        this.player = new Player(faction);
        this.turn.attach(player);
        for(Province temp: this.provinces){
            if(temp.getFaction().equals(faction)){
                temp.changeOwner(player);
                player.addProvince(temp);
            }
        }

    }

    public void nextTurn(){
        this.turn.Notify();
    }

    public void MoveUnit(ArrayList<Unit> Army, Province src, Province dest){
        int avaiablePoint = 9999;
        for(Unit temp : Army){
            if(avaiablePoint > temp.getPoint()){
                avaiablePoint = temp.getPoint();
            }
        }
        int point = adj.ShortestPath(src, dest, player, provinces);
        if(point > avaiablePoint){
            System.out.println("Can't move the army");
            return;
        }
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

    public boolean addsolider(Province pro, Unit unit, int num){
        if(!pro.getOwner().equals(player)){
            return false;
        }
        if(!pro.generateTroop(unit.getType(), unit.getName())){
            return false;
        }else{
            return true;
        }
        
    }

    public boolean bulid(String type, Province pro){
        if(!pro.getOwner().equals(player)){
            return false;
        }
        if(!player.constructNewBuilding(type, pro)){
            return false;
        }else{
            return true;
        }
    }

    public boolean upgrade(String type, Province pro){
        if(!pro.getOwner().equals(player)){
            return false;
        }if(player.upgradeBuilding(type, pro)){
            return false;
        }else{
            return true;
        }
    }

    public void war(){

    }
    public boolean setTax(Province pro, int level){
        if(!pro.getOwner().equals(player)){
            return false;
        }
        pro.setTax(level);
        return true;
    }   
}