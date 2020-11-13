package unsw.gloriaromanus;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TroopShopScreen {

    private Stage stage;
    private String title;
    private TroopShopController controller;
    private Scene scene;


    public TroopShopScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Troop Shop";

        controller = new TroopShopController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TroopShop.fxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root, 800, 600);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
		
}

