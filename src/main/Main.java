package main;

import controllers.MarketController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("../views/market.fxml"));
        Parent root = firstPaneLoader.load();

        Parent payment = FXMLLoader.load(getClass().getResource("../views/banque.fxml"));
        Scene secondScene = new Scene(payment, 750, 500);

        MarketController marketController = (MarketController) firstPaneLoader.getController();
        marketController.setSecondScene(secondScene);
        primaryStage.setTitle("Offers hub");
        primaryStage.setScene(new Scene(root, 750,500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
