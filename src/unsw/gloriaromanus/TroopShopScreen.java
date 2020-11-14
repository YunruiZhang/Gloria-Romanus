package unsw.gloriaromanus;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class TroopShopScreen {

    //static private TroopShopController controller;
    //static private Scene scene;

    public static void displayTroopShop(String title, String message) throws IOException{
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        /*
        controller = new TroopShopController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TroopShop.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root, 800, 600);
        */
        
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        
        window.setScene(scene);
        window.showAndWait();
    }
		
}

