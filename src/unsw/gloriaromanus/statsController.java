package unsw.gloriaromanus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class statsController extends MenuController{
    @FXML
    private Label P1gold;
    @FXML
    private Label P2gold;
    @FXML
    private Label WEALTHBOX1;
    @FXML
    private Label WEALTHBOX2;
    @FXML
    private Label OWNEDPROVS1;
    @FXML
    private Label OWNEDPROVS2;

    public void updateGoldAmt(ActionEvent e) throws Exception {
        int goldA = getParent().getGoldAmount(e, 1);
        int goldB = getParent().getGoldAmount(e, 2);
        P1gold.setText(Integer.toString(goldA));
        P2gold.setText(Integer.toString(goldB));
        int wealthA = getParent().gettotalwealth(e, 1);
        int wealthB = getParent().gettotalwealth(e, 2);
        WEALTHBOX1.setText(Integer.toString(wealthA));
        WEALTHBOX2.setText(Integer.toString(wealthB));
        int provsA = getParent().getNumberProvinces(e, 1);
        int provsB = getParent().getNumberProvinces(e, 2);
        OWNEDPROVS1.setText(Integer.toString(provsA));
        OWNEDPROVS2.setText(Integer.toString(provsB));
    }
}
