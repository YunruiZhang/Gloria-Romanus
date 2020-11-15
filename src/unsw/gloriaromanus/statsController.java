package unsw.gloriaromanus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class statsController extends MenuController{
    @FXML
    private Label P1gold;
    @FXML
    private Label P2gold;

    public void updateGoldAmt(ActionEvent e) throws Exception {
        int goldA = getParent().getGoldAmount(e, 1);
        int goldB = getParent().getGoldAmount(e, 2);
        P1gold.setText(Integer.toString(goldA));
        P2gold.setText(Integer.toString(goldB));
    }
}
