package unsw.gloriaromanus;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InvasionMenuController extends MenuController{
    @FXML
    private TextField invading_province;
    @FXML
    private TextField opponent_province;
    @FXML
    private TextArea output_terminal;

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
        //getParent().clickedInvadeButton(e, 1);
    }

    @FXML
    public void clickedInvadeButton2(ActionEvent e) throws IOException {
        //getParent().clickedInvadeButton(e, 2);
    }

    @FXML
    public void movep1(ActionEvent e) throws IOException {
        //getParent().moveArmy(e, 1);
    }

    @FXML
    public void movep2(ActionEvent e) throws IOException {
        //getParent().moveArmy(e, 2);
    }

    @FXML
    public void nextTurnButton(ActionEvent e) throws IOException {
        //getParent().nextTurnClick(e);
    }

    @FXML
    public void attackCombo1(ActionEvent e) throws IOException {
        
    }

    @FXML
    public void attackCombo2(ActionEvent e) throws IOException {
        
    }

    @FXML
    public void moveCombo1(ActionEvent e) throws IOException {
        
    }

    @FXML
    public void moveCombo2(ActionEvent e) throws IOException {
        
    }
}
