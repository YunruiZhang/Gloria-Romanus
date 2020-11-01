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
/**
 * calss to check adjacent and find shortest path
 */
public class Adjacent {

    private JSONObject matrix;
    /**
     * constructor to read matrix
     */
    public Adjacent() {
        try {
            String content = Files.readString(Paths.get("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json"));
            JSONObject province = new JSONObject(content);
            this.matrix = province;

        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }

    /**
     * check whether two provinces are adj
     * @param a province a
     * @param b province b
     * @return boolean
     */
    public boolean Check_adj(Province a, Province b){
        String nameA = a.getName();
        String nameB = b.getName();
        JSONObject provinceA = matrix.getJSONObject(nameA);
        if(provinceA.getBoolean(nameB) == true){
            return true;
        }else{
            return false;
        }
        
    }

    /**
     * Function to call dfs to find shortest path
     * @param a province a
     * @param b province b
     * @param player 
     * @param All
     * @return the point needed
     */
    public int ShortestPath(Province a, Province b, Player player, ArrayList<Province> All){
       String faction = player.getFaction();
       ArrayList<Province> visited = new ArrayList<Province>();
       return FindPath(faction, a, b, visited, All);
    }


    /**
     * dfs to find the shortest path
     * @param faction faction
     * @param curr the curr province
     * @param dest the dest province
     * @param visited for dfs
     * @param ALL all the provinces
     * @return the point
     */
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
            if(adj.getBoolean(key) == true && convert(key, ALL).getFaction().equals(faction) && !visited.contains(convert(key, ALL))){
                int currpath = FindPath(faction, convert(key, ALL), dest, visited, ALL);
                if(currpath < 9999){
                    Province temp = convert(key, ALL);
                    int cost = 4;
                    for(Infrastructure temp1 : temp.getBuildings()){
                        if(temp1 instanceof Road){
                            if(temp1.getLevel() == 1){
                                cost = 3;
                            }else if(temp1.getLevel() == 2){
                                cost = 2;
                            }else if(temp1.getLevel() == 3){
                                cost = 1;
                            }
                        }
                    }
                    ans = Math.min(ans, cost + currpath);
                }
            }

           
        } 

        visited.remove(curr);
        return ans;

    }

    /**
     * helper function convert string to province
     * @param pro
     * @param allp
     * @return the province
     */
    public Province convert(String pro, ArrayList<Province> allp){
        for(Province temp : allp){
            if(temp.getName().equals(pro)){
                return temp;
            }
        }
        return null;
    }

}
