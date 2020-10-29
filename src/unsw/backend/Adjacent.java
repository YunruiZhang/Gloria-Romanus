package unsw.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.json.*;

public class Adjacent {

    private JSONObject matrix;

    public Adjacent() {
        try {
            String content = Files.readString(Paths.get("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json"));
            JSONObject province = new JSONObject(content);
            this.matrix = province;

        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }

    public boolean Check_adj(Province a, Province b){
        String nameA = a.getName();
        String nameB = b.getName();
        JSONObject provinceA = matrix.getJSONObject(nameA);
        if(provinceA.getString(nameB).equals("true")){
            return true;
        }else{
            return false;
        }
        
    }

    public int ShortestPath(Province a, Province b, Player player, ArrayList<Province> All){
       String faction = player.getFaction();
       ArrayList<Province> visited = new ArrayList<Province>();
       return FindPath(faction, a, b, visited, All);
    }



    public int FindPath(String faction, Province curr, Province dest, ArrayList<Province> visited, ArrayList<Province> ALL){
        if(curr.equals(dest)){
            return 0;
        }
        visited.add(curr);
        int ans = 9999;
        // Recur for all the vertices adjacent to this vertex 

        JSONObject adj = matrix.getJSONObject(curr.getName());
        Iterator<String> keys = adj.keys();
        while (keys.hasNext()) { 
            String key = keys.next();
            if(adj.getString(key).equals("True") && convert(key, ALL).getFaction().equals(faction)){
                int currpath = FindPath(faction, convert(key, ALL), dest, visited, ALL);
                if(currpath != 9999){
                    ans = Math.min(ans, 1 + currpath);
                }
            }

           
        } 

        visited.remove(curr);
        return ans;

    }

    public Province convert(String pro, ArrayList<Province> allp){
        for(Province temp : allp){
            if(temp.getName().equals(pro)){
                return temp;
            }
        }
        return null;
    }

}
