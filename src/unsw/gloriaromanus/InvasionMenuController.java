package unsw.gloriaromanus;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SelectionMode;
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
    private ListView<String> attackCombo1fxid;
    @FXML
    private ListView<String> attackCombo2fxid;
    @FXML
    private ListView<String> moveUnitsel1;
    @FXML
    private ListView<String> moveUnitsel12;
    @FXML
    private ComboBox<String> movecombo1fxid;
    @FXML
    private ComboBox<String> movecombo2fxid;


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
    public void clickedInvadeButton(ActionEvent e) throws Exception {
        ObservableList<Integer> selectedIndices = attackCombo1fxid.getSelectionModel().getSelectedIndices();
        ObservableList<String> selectedStuff = attackCombo1fxid.getItems();
        ArrayList<String> toOut = new ArrayList<String>();
        for (int i : selectedIndices) {
            toOut.add(selectedStuff.get(i));
        }
        getParent().clickedInvadeButton(e, 1, toOut);
        refresherCall();
        getParent().updateGoldd(e);
    }

    @FXML
    public void clickedInvadeButton2(ActionEvent e) throws Exception {
        ObservableList<Integer> selectedIndices = attackCombo2fxid.getSelectionModel().getSelectedIndices();
        ObservableList<String> selectedStuff = attackCombo2fxid.getItems();
        ArrayList<String> toOut = new ArrayList<String>();
        for (int i : selectedIndices) {
            toOut.add(selectedStuff.get(i));
        }
        getParent().clickedInvadeButton(e, 2, toOut);
        refresherCall();
        getParent().updateGoldd(e);
    }

    @FXML
    public void movep1(ActionEvent e) throws IOException {
        ObservableList<Integer> selectedIndices = moveUnitsel1.getSelectionModel().getSelectedIndices();
        ObservableList<String> selectedStuff = moveUnitsel1.getItems();
        ArrayList<String> toOut = new ArrayList<String>();
        for (int i : selectedIndices) {
            toOut.add(selectedStuff.get(i));
        }
        getParent().moveArmy(e, toOut, 1, movecombo1fxid.getValue());
        refresherCall();
    }

    @FXML
    public void movep2(ActionEvent e) throws IOException {
        ObservableList<Integer> selectedIndices = moveUnitsel12.getSelectionModel().getSelectedIndices();
        ObservableList<String> selectedStuff = moveUnitsel12.getItems();
        ArrayList<String> toOutt = new ArrayList<String>();
        for (int i : selectedIndices) {
            toOutt.add(selectedStuff.get(i));
        }
        getParent().moveArmy(e, toOutt, 2, movecombo2fxid.getValue());
        refresherCall();
    }

    @FXML
    public void nextTurnButton(ActionEvent e) throws Exception {
        getParent().nextTurnClick(e);
        getParent().updateGoldd(e);
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
    public void refresh_invade(ActionEvent e) throws Exception {
        refresherCall();
        getParent().updateGoldd(e);
    }

    public void refresherCall() {
        attackCombo1fxid.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        attackCombo2fxid.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        moveUnitsel1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        moveUnitsel12.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        attackCombo1fxid.getItems().clear();
        if (getParent().retriveUnitName(1) != null) {
            for (String s : getParent().retriveUnitName(1)) {
                attackCombo1fxid.getItems().add(s);
            }
        }
        attackCombo2fxid.getItems().clear();
        if (getParent().retriveUnitName(2) != null) {
            for (String s : getParent().retriveUnitName(2)) {
                attackCombo2fxid.getItems().add(s);
            }
        }

        moveUnitsel1.getItems().clear();
        if (getParent().retriveUnitName(1) != null) {
            for (String s : getParent().retriveUnitName(1)) {
                moveUnitsel1.getItems().add(s);
            }
        }

        moveUnitsel12.getItems().clear();
        if (getParent().retriveUnitName(2) != null) {
            for (String s : getParent().retriveUnitName(2)) {
                moveUnitsel12.getItems().add(s);
            }
        }

        movecombo1fxid.getItems().clear();
        if (getParent().retriveOwnedProvinces(1) != null) {
            movecombo1fxid.getItems().addAll(getParent().retriveOwnedProvinces(1));
        }

        movecombo2fxid.getItems().clear();
        if (getParent().retriveOwnedProvinces(2) != null) {
            movecombo2fxid.getItems().addAll(getParent().retriveOwnedProvinces(2));
        }

    }
}
