package unsw.gloriaromanus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class MenuController {
    private GloriaRomanusController parent;


    public void setParent(GloriaRomanusController parent) {
        if (parent == null){
            System.out.println("GOT NULL");
        }
        this.parent = parent;
    }

    public GloriaRomanusController getParent(){
        return parent;
    }

    @FXML
    public void clickedSwitchMenu(ActionEvent e) throws Exception {
        //parent.switchMenu();
        TroopShopScreen.displayTroopShop("Title of Window", "Wow this alert box is awesome!");
    }

    @FXML
    public void TroopShopSwitch(ActionEvent e) throws Exception {
        parent.switchMenu("menuSelectortss");
    }

    @FXML
    public void Startgameb(ActionEvent e) throws Exception {
        parent.switchMenu("menuSelectorsgb");
    }

    @FXML
    public void attackmenucalledb(ActionEvent e) throws Exception {
        parent.switchMenu("invasion_menu");
    }

    @FXML
    public void buildmenucalledb(ActionEvent e) throws Exception {
        parent.switchMenu("basic_menu");
    }
}
