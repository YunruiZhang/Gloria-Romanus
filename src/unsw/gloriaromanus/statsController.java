package unsw.gloriaromanus;

import javafx.event.ActionEvent;

public class statsController extends MenuController{
    public void updateGoldAmt(ActionEvent e) throws Exception {
        getParent().getGoldAmount(e, 1);
        getParent().getGoldAmount(e, 1);
    }
}
