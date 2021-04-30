package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MyListener;
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

    @FXML
    private void handleButtonAction(MouseEvent event) {
        openSecondScene(event);
    }

    private MyListener myListener;
    private List<Offer> offers = new ArrayList<>();

    private Scene secondScene;

    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }
    public void openSecondScene(MouseEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
    }
    private List<Offer> getData(){
        List<Offer> offers = new ArrayList<>();
        Offer offer;

        offer = new Offer();
        offer.setCategory("Electronique");
        offer.setImgSrc("/imgs/gun.jpg");
        offer.setName("gun");
        offer.setPercentage("-40");
        offer.setPlace("Chez raed 2");
        offers.add(offer);

        offer = new Offer();
        offer.setCategory("Réstauration");
        offer.setImgSrc("/imgs/lablebi.png");
        offer.setName("Lableby");
        offer.setPercentage("-35");
        offer.setPlace("Chez Hattab");
        offers.add(offer);

        offer = new Offer();
        offer.setCategory("Réstauration");
        offer.setImgSrc("/imgs/Hamburger-maison.jpg");
        offer.setName("Hamburger");
        offer.setPercentage("-5");
        offer.setPlace("Chez Ahmed");
        offers.add(offer);

        offer = new Offer();
        offer.setCategory("Electronique");
        offer.setImgSrc("/imgs/gun.jpg");
        offer.setName("mousaddes");
        offer.setPercentage("-50");
        offer.setPlace("Chez raed");
        offers.add(offer);

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
        if(offers.size()>0){
            setChosenItem(offers.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Offer offer) {
                    setChosenItem(offer);
                }
            };
        }
        int column =0;
        int row = 1;

        try {
            for(int i=0; i<offers.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../views/item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(offers.get(i),myListener);

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

    private void setChosenItem(Offer offer){
        nameLabel.setText(offer.getName());
        percentageLabel.setText(offer.getPercentage());
        Image img = new Image(getClass().getResourceAsStream(offer.getImgSrc()));
        image.setImage(img);
        place.setText(offer.getPlace());
    }
}
