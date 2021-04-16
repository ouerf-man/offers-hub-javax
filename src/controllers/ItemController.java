package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Offer;

public class ItemController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label percentengeLabel;

    @FXML
    private ImageView image;

    private Offer offer;


    public void setData(Offer offer){
        this.offer = offer;
        nameLabel.setText(offer.getName());
        percentengeLabel.setText(offer.getPercentage());

        Image imageSrc = new Image(getClass().getResourceAsStream(offer.getImgSrc()));
        image.setImage(imageSrc);
    }

}
