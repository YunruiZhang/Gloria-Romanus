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
        parent.switchMenu(2, 4);
        //TroopShopScreen.displayTroopShop("Title of Window", "Wow this alert box is awesome!");
    }

    @FXML
    public void TroopShopSwitch(ActionEvent e) throws Exception {
        parent.switchMenu(3, 4);
    }

    @FXML
    public void Startgameb(ActionEvent e) throws Exception {
        parent.switchMenu(0, 4);
    }

    @FXML
    public void attackmenucalledb(ActionEvent e) throws Exception {
        parent.switchMenu(4, 1);
    }

    @FXML
    public void buildmenucalledb(ActionEvent e) throws Exception {
        parent.switchMenu(4, 2);
    }

    @FXML
    public void clickedSwitchMenuinvasive(ActionEvent e) throws Exception {
        parent.switchMenu(1, 4);
    }

    @FXML
    public void clickedSwitchMenufromTaxProv(ActionEvent e) throws Exception {
        parent.switchMenu(6, 4);
    }

    @FXML 
    public void taxretebuttionb(ActionEvent e) throws Exception {
        parent.switchMenu(4, 6);
    }
}
