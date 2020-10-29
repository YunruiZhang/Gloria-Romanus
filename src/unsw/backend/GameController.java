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
                player.addProvince(temp);
            }
        }

    }

    public void nextTurn(){
        this.turn.Notify();
    }


}