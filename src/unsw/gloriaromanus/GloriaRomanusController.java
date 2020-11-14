package unsw.gloriaromanus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.FeatureTable;
import com.esri.arcgisruntime.data.GeoPackage;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyLayerResult;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol.HorizontalAlignment;
import com.esri.arcgisruntime.symbology.TextSymbol.VerticalAlignment;
import com.esri.arcgisruntime.data.Feature;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.geojson.FeatureCollection;
import org.geojson.LngLatAlt;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.util.Pair;
import unsw.backend.*;

public class GloriaRomanusController{

  @FXML
  private MapView mapView;

  @FXML
  private StackPane stackPaneMain;

  // could use ControllerFactory?
  private ArrayList<Pair<MenuController, VBox>> controllerParentPairs;

  private ArcGISMap map;

  private Map<String, String> provinceToOwningFactionMap;

  private Map<String, Integer> provinceToNumberTroopsMap;

  private String humanFaction;

  private Feature currentlySelectedHumanProvince;
  private Feature currentlySelectedEnemyProvince;

  private FeatureLayer featureLayer_provinces;
  private GameController thegame;
  private Player romeplayer;
  private Player gaulplayer;

  @FXML
  private void initialize() throws JsonParseException, JsonMappingException, IOException, InterruptedException {
    // TODO = you should rely on an object oriented design to determine ownership
    thegame = new GameController();
    provinceToOwningFactionMap = getProvinceToOwningFactionMap();
    provinceToNumberTroopsMap = new HashMap<String, Integer>();
    for(String provinceName : provinceToOwningFactionMap.keySet()){
      provinceToNumberTroopsMap.put(provinceName, 0);
    }
    romeplayer = thegame.setPlayer("Rome");
    gaulplayer = thegame.setPlayer("Gaul");
    //shall we give units to them??


    // TODO = load this from a configuration file you create (user should be able to
    // select in loading screen)
    humanFaction = "Rome";

    currentlySelectedHumanProvince = null;
    currentlySelectedEnemyProvince = null;

    String []menus = {"GRCstartScreen.fxml","invasion_menu.fxml", "basic_menu.fxml", "TroopShop.fxml", "menuSelector.fxml", "TaxProv.fxml", "stats.fxml"};
    controllerParentPairs = new ArrayList<Pair<MenuController, VBox>>();
    for (String fxmlName: menus){
      System.out.println(fxmlName);
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
      VBox root = (VBox)loader.load();
      MenuController menuController = (MenuController)loader.getController();
      menuController.setParent(this);
      controllerParentPairs.add(new Pair<MenuController, VBox>(menuController, root));
    }
    stackPaneMain.getChildren().add(controllerParentPairs.get(0).getValue());
    initializeProvinceLayers();
  }

  public void clickedInvadeButton(ActionEvent e, int index, ArrayList<String> units) throws IOException {

    if(currentlySelectedHumanProvince == null || currentlySelectedEnemyProvince == null){
      printMessageToTerminal("Please select two provinces");
      return;
    }
    ArrayList<Unit> Units = new ArrayList<Unit>();
    for(String temppp : units){
      Unit tempunit = thegame.getUnitFromString(temppp);
      Units.add(tempunit);

    }
    String humanProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
    String enemyProvince = (String)currentlySelectedEnemyProvince.getAttributes().get("name");
    Province mine = thegame.getProvinceFromString(humanProvince);
    Province enemy = thegame.getProvinceFromString(enemyProvince);
    if(index == 1){
      int result = thegame.Battle(mine.getOwner(), Units, humanProvince, enemyProvince);
      if(result == 2){
        printMessageToTerminal("Lost battle!");
      }else if(result == 1){
        printMessageToTerminal("Won battle!");
      }else{
        printMessageToTerminal("Invalid Input");
      }
    }else{
      int result = thegame.Battle(enemy.getOwner(), Units, enemyProvince, humanProvince);
      if(result == 2){
        printMessageToTerminal("Lost battle!");
      }else if(result == 1){
        printMessageToTerminal("Won battle!");
      }else{
        printMessageToTerminal("Invalid Input");
      }
    }
    provinceToNumberTroopsMap.put(humanProvince, mine.totalSolider()); 
    provinceToNumberTroopsMap.put(enemyProvince, enemy.totalSolider());
    provinceToOwningFactionMap = getProvinceToOwningFactionMap();
    addAllPointGraphics();
    resetSelections();  // reset selections in UI
  }
  
  public void buyInfraButton(ActionEvent e, String building, int index) throws IOException {
    if(currentlySelectedEnemyProvince != null && index == 2){
      String enemyProvince = (String)currentlySelectedEnemyProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(enemyProvince);
      if(!thegame.bulid(temp.getOwner(), building, enemyProvince)){
        printMessageToTerminal("Fail to start construction on the building");
      }else{
        printMessageToTerminal(building + " is under construction in " + enemyProvince);
      }
    }else if(currentlySelectedHumanProvince != null && index == 1){
      String myProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(myProvince);
      if(!thegame.bulid(temp.getOwner(), building, myProvince)){
        printMessageToTerminal("Fail to start construction on the building");
      }else{
        printMessageToTerminal(building + " is under construction in " + myProvince);
      }
    }else{
      printMessageToTerminal("No province selected");
    }
  }

  public void nextTurnClick(ActionEvent e){
    thegame.nextTurn();
    printMessageToTerminal("Next turn !!");
  }

  public void moveArmy(ActionEvent e, ArrayList<String> unilist, int index, String province)throws IOException{
    if(unilist.size() == 0){
      printMessageToTerminal("No unit selected");
      return;
    }
    ArrayList<Unit> units = new ArrayList<Unit>();
    for(String temp: unilist){
      units.add(thegame.getUnitFromString(temp));
    }

    if(index == 1){
      if(currentlySelectedHumanProvince != null){
        String myProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
        Province mypp = thegame.getProvinceFromString(myProvince);
        if(!thegame.MoveUnit(mypp.getOwner(), units, myProvince, province)){
          printMessageToTerminal("Cannot move the units");
        }else{
          Province mypp1 = thegame.getProvinceFromString(province);
          provinceToNumberTroopsMap.put(myProvince, mypp.totalSolider()); 
          provinceToNumberTroopsMap.put(province, mypp1.totalSolider());
          printMessageToTerminal("Units have been moved !!");
          addAllPointGraphics();
        }
      }
    }else{
      if(currentlySelectedEnemyProvince != null){
        String EnemyProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
        Province Enemypp = thegame.getProvinceFromString(EnemyProvince);
        if(!thegame.MoveUnit(Enemypp.getOwner(), units, EnemyProvince, province)){
          printMessageToTerminal("Cannot move the units");
        }else{
          Province Enemypp1 = thegame.getProvinceFromString(province);
          provinceToNumberTroopsMap.put(EnemyProvince, Enemypp.totalSolider()); 
          provinceToNumberTroopsMap.put(province, Enemypp1.totalSolider());
          printMessageToTerminal("Units have been moved !!");
          addAllPointGraphics();
        }
      }
    }
  }

  public ArrayList<String> retriveOwnedProvinces(int index){
    if(index == 1){
      if(currentlySelectedHumanProvince == null){
        return null;
      }
      String myProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
      ArrayList<Province> romepp = romeplayer.getProvinces();
      ArrayList<String> romeppstring = new ArrayList<String>();
      for(Province temppp : romepp){
        if(!myProvince.equals(temppp.getName())){
          romeppstring.add(temppp.getName());
        } 
      }
      return romeppstring;
    }else{
      if(currentlySelectedEnemyProvince == null){
        return null;
      }
      String EnemyProvince = (String)currentlySelectedEnemyProvince.getAttributes().get("name");
      ArrayList<Province> gaulpp = gaulplayer.getProvinces();
      ArrayList<String> gaulppstring = new ArrayList<String>();
      for(Province temppp : gaulpp){
        if(!EnemyProvince.equals(temppp.getName())){
          gaulppstring.add(temppp.getName());
        } 
      }
      return gaulppstring;
    }
  }
  
  public int getGoldAmount(ActionEvent e, int index){
    if(index == 1){
      return (int)romeplayer.getGold();
    }else{
      return (int)gaulplayer.getGold();
    }
  }

  public String getGoal(int index){
    if(index == 1){
      return romeplayer.getGoal();
    }else{
      return gaulplayer.getGoal();
    }
  }

  public ArrayList<String> retriveUnitName(int index){
    if(index == 1 && currentlySelectedHumanProvince != null){
      String myProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(myProvince);
      ArrayList<Unit> allUnits = temp.getUnits();
      ArrayList<String> names = new ArrayList<String>();
      for(Unit tmp: allUnits){
        names.add(tmp.getName());
      }
      return names;
    }else if(index == 2 && currentlySelectedEnemyProvince != null){
      String EnemyProvince = (String)currentlySelectedEnemyProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(EnemyProvince);
      ArrayList<Unit> allUnits = temp.getUnits();
      ArrayList<String> names = new ArrayList<String>();
      for(Unit tmp: allUnits){
        names.add(tmp.getName());
      }
      return names;
    }else{
      printMessageToTerminal("No province has been selected.");
      return null;
    }
  }

  public void upgradeInfraButton(ActionEvent e, String building, int index) throws IOException {
    if(currentlySelectedEnemyProvince != null && index == 2){
      String enemyProvince = (String)currentlySelectedEnemyProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(enemyProvince);
      if(!thegame.upgrade(temp.getOwner(), building, enemyProvince)){
        printMessageToTerminal("Failed to upgrade the building");
      }else{
        printMessageToTerminal(building + " has been upgraded in "  + enemyProvince);
      }
    }else if(currentlySelectedHumanProvince != null && index == 1){
      String myProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(myProvince);
      if(!thegame.upgrade(temp.getOwner(), building, myProvince)){
        printMessageToTerminal("Failed to upgrade the building");
      }else{
        printMessageToTerminal(building + " has been upgraded in " + myProvince);
      }
    }else{
      printMessageToTerminal("No province has been selected.");
    }
  }

  public void setTax(ActionEvent e, int index, String tax){
    int level;
    if(tax.equals("10")){
      level = 1;
    }else if(tax.equals("15")){
      level = 2;
    }else if(tax.equals("20")){
      level = 3;
    }else{
      level = 4;
    }
    if(index == 1){
      if(currentlySelectedHumanProvince == null){
        printMessageToTerminal("Please select the province");
      }
      String myProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(myProvince);
      temp.setTax(level);
    }else{
      if(currentlySelectedEnemyProvince == null){
        printMessageToTerminal("Please select the province");
      }
      String myProvince = (String)currentlySelectedEnemyProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(myProvince);
      temp.setTax(level);
    }
  }
//1 for increase 2 for decrease
  public void changeTax(ActionEvent e, int index, int flag){
    if(index == 1){
      if(currentlySelectedHumanProvince == null){
        printMessageToTerminal("Please select the province");
      }
      String myProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(myProvince);
      if(flag == 1){
        temp.increaseTaxRate();
      }else{
        temp.decreaseTaxRate();
      }
    }else{
      if(currentlySelectedEnemyProvince == null){
        printMessageToTerminal("Please select the province");
      }
      String myProvince = (String)currentlySelectedEnemyProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(myProvince);
      if(flag == 1){
        temp.increaseTaxRate();
      }else{
        temp.decreaseTaxRate();
      }
    }
  }

  public void buyTroopButton(ActionEvent e, String unitname, int index, int qty) throws IOException {
    if(currentlySelectedEnemyProvince != null && index == 2){
      String enemyProvince = (String)currentlySelectedEnemyProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(enemyProvince);
      Unit tempUnit = thegame.getUnitFromString(unitname);
      if(!thegame.addsolider(temp.getOwner(), enemyProvince, tempUnit, qty)){
        printMessageToTerminal("Failed to add solider");
      }else{
        printMessageToTerminal(qty + " soliders added to " + enemyProvince);
        provinceToNumberTroopsMap.put(enemyProvince, provinceToNumberTroopsMap.get(enemyProvince) + qty);
        //initializeProvinceLayers();
        //resetSelections();  // reset selections in UI
        addAllPointGraphics(); // reset graphics
        
      }
    }else if(currentlySelectedHumanProvince != null && index == 1){
      String myProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");
      Province temp = thegame.getProvinceFromString(myProvince);
      Unit tempUnit = thegame.getUnitFromString(unitname);
      if(!thegame.addsolider(temp.getOwner(),myProvince, tempUnit, qty)){
        printMessageToTerminal("Failed to add solider");
      }else{
        printMessageToTerminal(qty + " soliders added to " + myProvince);
        provinceToNumberTroopsMap.put(myProvince, provinceToNumberTroopsMap.get(myProvince) + qty);
        //initializeProvinceLayers();
        //resetSelections();  // reset selections in UI
        addAllPointGraphics(); // reset graphics
      }
    }else{
      printMessageToTerminal("No province selected.");
    }
  }

  public void createUnitButton(ActionEvent e, String unitType, int index, String name) throws IOException {
    if(currentlySelectedEnemyProvince != null && index == 2){
      String enemyProvince = (String)currentlySelectedEnemyProvince.getAttributes().get("name");
     
      if(thegame.createUnit(enemyProvince, unitType, name) == null){
        printMessageToTerminal("Failed to create the unit");
      }
    }else if(currentlySelectedHumanProvince != null && index == 1){
      String myProvince = (String)currentlySelectedHumanProvince.getAttributes().get("name");

      if(thegame.createUnit(myProvince, unitType, name) == null){
        printMessageToTerminal("Failed to create the unit");
      }
    }else{
      printMessageToTerminal("No province selected");
    }
  }


  /**
   * run this initially to update province owner, change feature in each
   * FeatureLayer to be visible/invisible depending on owner. Can also update
   * graphics initially
   */
  private void initializeProvinceLayers() throws JsonParseException, JsonMappingException, IOException {

    Basemap myBasemap = Basemap.createImagery();
    // myBasemap.getReferenceLayers().remove(0);
    map = new ArcGISMap(myBasemap);
    mapView.setMap(map);

    // note - tried having different FeatureLayers for AI and human provinces to
    // allow different selection colors, but deprecated setSelectionColor method
    // does nothing
    // so forced to only have 1 selection color (unless construct graphics overlays
    // to give color highlighting)
    GeoPackage gpkg_provinces = new GeoPackage("src/unsw/gloriaromanus/provinces_right_hand_fixed.gpkg");
    gpkg_provinces.loadAsync();
    gpkg_provinces.addDoneLoadingListener(() -> {
      if (gpkg_provinces.getLoadStatus() == LoadStatus.LOADED) {
        // create province border feature
        featureLayer_provinces = createFeatureLayer(gpkg_provinces);
        map.getOperationalLayers().add(featureLayer_provinces);

      } else {
        System.out.println("load failure");
      }
    });

    addAllPointGraphics();
  }

  private void addAllPointGraphics() throws JsonParseException, JsonMappingException, IOException {
    mapView.getGraphicsOverlays().clear();

    InputStream inputStream = new FileInputStream(new File("src/unsw/gloriaromanus/provinces_label.geojson"));
    FeatureCollection fc = new ObjectMapper().readValue(inputStream, FeatureCollection.class);

    GraphicsOverlay graphicsOverlay = new GraphicsOverlay();

    for (org.geojson.Feature f : fc.getFeatures()) {
      if (f.getGeometry() instanceof org.geojson.Point) {
        org.geojson.Point p = (org.geojson.Point) f.getGeometry();
        LngLatAlt coor = p.getCoordinates();
        Point curPoint = new Point(coor.getLongitude(), coor.getLatitude(), SpatialReferences.getWgs84());
        PictureMarkerSymbol s = null;
        String province = (String) f.getProperty("name");
        String faction = provinceToOwningFactionMap.get(province);

        TextSymbol t = new TextSymbol(10,
            faction + "\n" + province + "\n" + provinceToNumberTroopsMap.get(province), 0xFFFF0000,
            HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);

        switch (faction) {
          case "Gaul":
            // note can instantiate a PictureMarkerSymbol using the JavaFX Image class - so could
            // construct it with custom-produced BufferedImages stored in Ram
            // http://jens-na.github.io/2013/11/06/java-how-to-concat-buffered-images/
            // then you could convert it to JavaFX image https://stackoverflow.com/a/30970114

            // you can pass in a filename to create a PictureMarkerSymbol...
            s = new PictureMarkerSymbol(new Image((new File("images/Celtic_Druid.png")).toURI().toString()));
            break;
          case "Rome":
            // you can also pass in a javafx Image to create a PictureMarkerSymbol (different to BufferedImage)
            s = new PictureMarkerSymbol("images/legionary.png");
            break;
          // TODO = handle all faction names, and find a better structure...
        }
        t.setHaloColor(0xFFFFFFFF);
        t.setHaloWidth(2);
        Graphic gPic = new Graphic(curPoint, s);
        Graphic gText = new Graphic(curPoint, t);
        graphicsOverlay.getGraphics().add(gPic);
        graphicsOverlay.getGraphics().add(gText);
      } else {
        System.out.println("Non-point geo json object in file");
      }

    }

    inputStream.close();
    mapView.getGraphicsOverlays().add(graphicsOverlay);
  }

  private FeatureLayer createFeatureLayer(GeoPackage gpkg_provinces) {
    FeatureTable geoPackageTable_provinces = gpkg_provinces.getGeoPackageFeatureTables().get(0);

    // Make sure a feature table was found in the package
    if (geoPackageTable_provinces == null) {
      System.out.println("no geoPackageTable found");
      return null;
    }

    // Create a layer to show the feature table
    FeatureLayer flp = new FeatureLayer(geoPackageTable_provinces);

    // https://developers.arcgis.com/java/latest/guide/identify-features.htm
    // listen to the mouse clicked event on the map view
    mapView.setOnMouseClicked(e -> {
      // was the main button pressed?
      if (e.getButton() == MouseButton.PRIMARY) {
        // get the screen point where the user clicked or tapped
        Point2D screenPoint = new Point2D(e.getX(), e.getY());

        // specifying the layer to identify, where to identify, tolerance around point,
        // to return pop-ups only, and
        // maximum results
        // note - if select right on border, even with 0 tolerance, can select multiple
        // features - so have to check length of result when handling it
        final ListenableFuture<IdentifyLayerResult> identifyFuture = mapView.identifyLayerAsync(flp,
            screenPoint, 0, false, 25);

        // add a listener to the future
        identifyFuture.addDoneListener(() -> {
          try {
            // get the identify results from the future - returns when the operation is
            // complete
            IdentifyLayerResult identifyLayerResult = identifyFuture.get();
            // a reference to the feature layer can be used, for example, to select
            // identified features
            if (identifyLayerResult.getLayerContent() instanceof FeatureLayer) {
              FeatureLayer featureLayer = (FeatureLayer) identifyLayerResult.getLayerContent();
              // select all features that were identified
              List<Feature> features = identifyLayerResult.getElements().stream().map(f -> (Feature) f).collect(Collectors.toList());

              if (features.size() > 1){
                printMessageToTerminal("Have more than 1 element - you might have clicked on boundary!");
              }
              else if (features.size() == 1){
                // note maybe best to track whether selected...
                Feature f = features.get(0);
                String province = (String)f.getAttributes().get("name");

                if (provinceToOwningFactionMap.get(province).equals(humanFaction)){
                  // province owned by human
                  if (currentlySelectedHumanProvince != null){
                    featureLayer.unselectFeature(currentlySelectedHumanProvince);
                  }
                  currentlySelectedHumanProvince = f;
                  if (controllerParentPairs.get(0).getKey() instanceof InvasionMenuController){
                    ((InvasionMenuController)controllerParentPairs.get(0).getKey()).setInvadingProvince(province);
                  }

                }
                else{
                  if (currentlySelectedEnemyProvince != null){
                    featureLayer.unselectFeature(currentlySelectedEnemyProvince);
                  }
                  currentlySelectedEnemyProvince = f;
                  if (controllerParentPairs.get(0).getKey() instanceof InvasionMenuController){
                    ((InvasionMenuController)controllerParentPairs.get(0).getKey()).setOpponentProvince(province);
                  }
                }

                featureLayer.selectFeature(f);                
              }

              
            }
          } catch (InterruptedException | ExecutionException ex) {
            // ... must deal with checked exceptions thrown from the async identify
            // operation
            System.out.println("InterruptedException occurred");
          }
        });
      }
    });
    return flp;
  }

  private Map<String, String> getProvinceToOwningFactionMap() throws IOException {
    //String content = Files.readString(Paths.get("src/unsw/gloriaromanus/initial_province_ownership.json"));
    ArrayList<Province> all = thegame.getAllPovinces();
    //JSONObject ownership = new JSONObject(content);
    Map<String, String> m = new HashMap<String, String>();
    for(Province temp: all){
      m.put(temp.getName(), temp.getFaction());
    }
    /*for (String key : ownership.keySet()) {
      // key will be the faction name
      JSONArray ja = ownership.getJSONArray(key);
      // value is province name
      for (int i = 0; i < ja.length(); i++) {
        String value = ja.getString(i);
        m.put(value, key);
      }
    }*/
    return m;
  }

  private ArrayList<String> getHumanProvincesList() throws IOException {
    // https://developers.arcgis.com/labs/java/query-a-feature-layer/

    String content = Files.readString(Paths.get("src/unsw/gloriaromanus/initial_province_ownership.json"));
    JSONObject ownership = new JSONObject(content);
    return ArrayUtil.convert(ownership.getJSONArray(humanFaction));
  }

  /**
   * returns query for arcgis to get features representing human provinces can
   * apply this to FeatureTable.queryFeaturesAsync() pass string to
   * QueryParameters.setWhereClause() as the query string
   */
  private String getHumanProvincesQuery() throws IOException {
    LinkedList<String> l = new LinkedList<String>();
    for (String hp : getHumanProvincesList()) {
      l.add("name='" + hp + "'");
    }
    return "(" + String.join(" OR ", l) + ")";
  }

  private boolean confirmIfProvincesConnected(String province1, String province2) throws IOException {
    String content = Files.readString(Paths.get("src/unsw/gloriaromanus/province_adjacency_matrix_fully_connected.json"));
    JSONObject provinceAdjacencyMatrix = new JSONObject(content);
    return provinceAdjacencyMatrix.getJSONObject(province1).getBoolean(province2);
  }

  private void resetSelections(){
    featureLayer_provinces.unselectFeatures(Arrays.asList(currentlySelectedEnemyProvince, currentlySelectedHumanProvince));
    currentlySelectedEnemyProvince = null;
    currentlySelectedHumanProvince = null;
    if (controllerParentPairs.get(0).getKey() instanceof InvasionMenuController){
      ((InvasionMenuController)controllerParentPairs.get(0).getKey()).setInvadingProvince("");
      ((InvasionMenuController)controllerParentPairs.get(0).getKey()).setOpponentProvince("");
    }
  }

  private void printMessageToTerminal(String message){
    if (controllerParentPairs.get(0).getKey() instanceof InvasionMenuController){
      ((InvasionMenuController)controllerParentPairs.get(0).getKey()).appendToTerminal(message);
    }else if(controllerParentPairs.get(0).getKey() instanceof BasicMenuController){
      ((BasicMenuController)controllerParentPairs.get(0).getKey()).appendToTerminal(message);
    }else if(controllerParentPairs.get(0).getKey() instanceof TaxProvController){
      ((TaxProvController)controllerParentPairs.get(0).getKey()).appendToTerminal(message);
    }
  }

  /**
   * Stops and releases all resources used in application.
   */
  void terminate() {

    if (mapView != null) {
      mapView.dispose();
    }
  }

  public void switchMenu(int a, int b) throws JsonParseException, JsonMappingException, IOException {
    /*
    System.out.println("trying to switch menu");
    stackPaneMain.getChildren().remove(controllerParentPairs.get(0).getValue());
    Collections.reverse(controllerParentPairs);
    stackPaneMain.getChildren().add(controllerParentPairs.get(0).getValue());
    */
    if (a == 0 && b == 4) {
      stackPaneMain.getChildren().add(controllerParentPairs.get(6).getValue());
    }
    if (controllerParentPairs.get(0).getKey() instanceof InvasionMenuController){
      ((InvasionMenuController)controllerParentPairs.get(0).getKey()).refresherCall();
    }else if (controllerParentPairs.get(1).getKey() instanceof InvasionMenuController){
        ((InvasionMenuController)controllerParentPairs.get(1).getKey()).refresherCall();
    }
    stackPaneMain.getChildren().remove(controllerParentPairs.get(0).getValue());
    Collections.swap(controllerParentPairs, 0, a);
    Collections.swap(controllerParentPairs, 0, b);
    stackPaneMain.getChildren().add(controllerParentPairs.get(0).getValue());
  }

}


