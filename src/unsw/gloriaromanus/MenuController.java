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
        parent.updateGoldd(e);
        parent.switchMenu(2, 4);
        //TroopShopScreen.displayTroopShop("Title of Window", "Wow this alert box is awesome!");
    }

    @FXML
    public void TroopShopSwitch(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(3, 4);
    }

    @FXML
    public void Startgameb(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(0, 7);
    }

    @FXML
    public void attackmenucalledb(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(4, 1);
    }

    @FXML
    public void buildmenucalledb(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(4, 2);
    }

    @FXML
    public void clickedSwitchMenuinvasive(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(1, 4);
    }

    @FXML
    public void clickedSwitchMenufromTaxProv(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(5, 4);
    }

    @FXML 
    public void taxretebuttionb(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(4, 5);
    }

    @FXML 
    public void darthvadar123(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(5, 2);
    }

    @FXML 
    public void visshop123(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(1, 2);
    }

    @FXML 
    public void visATO(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(1, 5);
    }

    @FXML 
    public void bmamam(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(2, 1);
    }

    @FXML 
    public void bmvto(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(2, 5);
    }

    @FXML 
    public void stAMAMtax(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(5, 1);
    }

    @FXML 
    public void INSTRUCTSCREENSW(ActionEvent e) throws Exception {
        parent.updateGoldd(e);
        parent.switchMenu(7, 4);
    }
}
