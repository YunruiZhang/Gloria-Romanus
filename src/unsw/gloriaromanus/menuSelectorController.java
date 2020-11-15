package unsw.gloriaromanus;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class menuSelectorController extends MenuController{
    @FXML
    public void menumenunecxtturn(ActionEvent e) throws IOException {
        getParent().nextTurnClick(e);
    }
}
