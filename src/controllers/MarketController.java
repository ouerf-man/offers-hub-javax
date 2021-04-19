package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Offer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private VBox selectedProduct;

    @FXML
    private Label nameLabel;

    @FXML
    private Label percentageLabel;

    @FXML
    private ImageView image;

    @FXML
    private Label place;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Offer> offers = new ArrayList<>();

    private List<Offer> getData(){
        List<Offer> offers = new ArrayList<>();
        Offer offer;

        for(int i=0;i<20;i++){
            offer = new Offer();
            offer.setCategory("restaurant");
            offer.setImgSrc("/imgs/lablebi.png");
            offer.setName("Lableby");
            offer.setPercentage("-35");
            offer.setPlace("Chez Hattab");
            offers.add(offer);
        }
        return offers;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ADDING CATEGORIES
        comboBox.getItems().addAll(
                "Electronique",
                "Alimentation",
                "Réstauration",
                "Prêt à porter",
                "Bricolage Jardinage",
                "Meuble-decoration");
        comboBox.getSelectionModel().select("Réstauration");


        offers.addAll(getData());
        int column =0;
        int row = 1;

        try {
            for(int i=0; i<offers.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../views/item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(offers.get(i));

                if(column == 3){
                    column =0;
                    row++;
                }

                grid.add(anchorPane,column++,row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
