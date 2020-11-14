package unsw.gloriaromanus;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class TaxProvController extends MenuController {
    @FXML
    private ComboBox<String> getTax1;

    @FXML
    private ComboBox<String> getTax2;

    @FXML
    private TextArea setprovtaxArea;

    @FXML
    public void initialize() {
        ArrayList<String> taxes = new ArrayList<String>();
        taxes.add("10"); taxes.add("15"); taxes.add("20"); taxes.add("25");
        getTax1.getItems().addAll(taxes);
        getTax2.getItems().addAll(taxes);
    }

    public void appendToTerminal(String message) {
        setprovtaxArea.appendText(message + "\n");
    }

    @FXML
    public void setTax1(ActionEvent e) throws IOException {
        getParent().setTax(e, 1, getTax1.getValue());
    }

    @FXML
    public void setTax2(ActionEvent e) throws IOException {
        getParent().setTax(e, 2, getTax2.getValue());
    }

    @FXML
    public void inc5p1(ActionEvent e) throws IOException {
        getParent().changeTax(e, 1, 1);
    }

    @FXML
    public void inc5p2(ActionEvent e) throws IOException {
        getParent().changeTax(e, 2, 1);
    }

    @FXML
    public void dec5p1(ActionEvent e) throws IOException {
        getParent().changeTax(e, 1, 2);
    }

    @FXML
    public void dec5p2(ActionEvent e) throws IOException {
        getParent().changeTax(e, 2, 2);
    }
    
}
