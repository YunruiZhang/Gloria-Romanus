package unsw.gloriaromanus;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InvasionMenuController extends MenuController{
    @FXML
    private TextField invading_province;
    @FXML
    private TextField opponent_province;
    @FXML
    private TextArea output_terminal;
    @FXML
    private MenuButton attackCombo1fxid;
    @FXML
    private MenuButton attackCombo2fxid;
    @FXML
    private MenuButton movecombo1fxid;
    @FXML
    private MenuButton movecombo2fxid;


    // https://stackoverflow.com/a/30171444
    @FXML
    private URL location; // has to be called location

    public void setInvadingProvince(String p) {
        invading_province.setText(p);
    }

    public void setOpponentProvince(String p) {
        opponent_province.setText(p);
    }

    public void appendToTerminal(String message) {
        output_terminal.appendText(message + "\n");
    }

    @FXML
    public void clickedInvadeButton(ActionEvent e) throws IOException {
        getParent().clickedInvadeButton(e, 1);
    }

    @FXML
    public void clickedInvadeButton2(ActionEvent e) throws IOException {
        getParent().clickedInvadeButton(e, 2);
    }

    @FXML
    public void movep1(ActionEvent e) throws IOException {
        getParent().moveArmy(e, ArrayList<String>, 1, dest_prov);
    }

    @FXML
    public void movep2(ActionEvent e) throws IOException {
        getParent().moveArmy(e, ArrayList<String>, 2, dest_prov);
    }

    @FXML
    public void nextTurnButton(ActionEvent e) throws IOException {
        getParent().nextTurnClick(e);
    }

    @FXML
    public void attackCombo1(ActionEvent e) throws IOException {
        //getParent().
    }

    @FXML
    public void attackCombo2(ActionEvent e) throws IOException {
        //getParent().
    }

    @FXML
    public void moveCombo1(ActionEvent e) throws IOException {
        //getParent().
    }

    @FXML
    public void moveCombo2(ActionEvent e) throws IOException {
        //getParent().
    }

    @FXML 
    public void refresh_invade(ActionEvent e) throws IOException {
        attackCombo1fxid.getItems().clear();
        if (getParent().retriveUnitName(1) != null) {
            for (String s : getParent().retriveUnitName(1)) {
                CheckBox newbox = new CheckBox(s);
                CustomMenuItem  item0 = new CustomMenuItem(newbox);
                attackCombo1fxid.getItems().add(item0);
            }
        }
        attackCombo2fxid.getItems().clear();
        if (getParent().retriveUnitName(2) != null) {
            for (String s : getParent().retriveUnitName(2)) {
                CheckBox newbox = new CheckBox(s);
                CustomMenuItem  item0 = new CustomMenuItem(newbox);
                attackCombo2fxid.getItems().add(item0);
            }
        }

        movecombo1fxid.getItems().clear();
        if (getParent().retriveUnitName(1) != null) {
            for (String s : getParent().retriveUnitName(1)) {
                CheckBox newbox = new CheckBox(s);
                CustomMenuItem  item0 = new CustomMenuItem(newbox);
                movecombo1fxid.getItems().add(item0);
            }
        }

        movecombo2fxid.getItems().clear();
        if (getParent().retriveUnitName(2) != null) {
            for (String s : getParent().retriveUnitName(2)) {
                CheckBox newbox = new CheckBox(s);
                CustomMenuItem  item0 = new CustomMenuItem(newbox);
                movecombo2fxid.getItems().add(item0);
            }
        }
        
    }
}
