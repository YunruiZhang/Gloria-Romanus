package unsw.gloriaromanus;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TroopShopController extends BasicMenuController{
    @FXML
    private TextField troopqty;

    @FXML
    private ComboBox<String> trooptype;

    @FXML
    private ComboBox<String> trooptype1;

    @FXML
    private TextArea out_terminal1;


    public TroopShopController () {
        trooptype.getItems().clear();
        if (getParent().retriveUnitName(1) != null) trooptype.getItems().addAll(getParent().retriveUnitName(1));
        trooptype1.getItems().clear();
        if (getParent().retriveUnitName(2) != null) trooptype1.getItems().addAll(getParent().retriveUnitName(2));
    }

    @FXML
    public void buyTroopButtonp2(ActionEvent e) throws IOException {
        try {
            int qty = Integer.parseInt(troopqty.getText());
            getParent().buyTroopButton(e, trooptype1.getValue(), 2, qty);
        } catch (NumberFormatException g) {
            out_terminal1.appendText("Text entered should be a number");
        }
        
    }

    @FXML
    public void buyTroopButton(ActionEvent e) throws IOException {
        try {
            int qty = Integer.parseInt(troopqty.getText());
            getParent().buyTroopButton(e, trooptype.getValue(), 1, qty);
        } catch (NumberFormatException g) {
            out_terminal1.appendText("Text entered should be a number");
        }
    }

}
