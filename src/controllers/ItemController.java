package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.MyListener;
import model.Offer;

public class ItemController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label percentengeLabel;

    @FXML
    private ImageView image;

    @FXML
    private void click(MouseEvent actionEvent){
        myListener.onClickListener(offer);
    }
    private Offer offer;
    private MyListener myListener;

    public void setData(Offer offer,MyListener myListener){
        this.offer = offer;
        this.myListener=myListener;
        nameLabel.setText(offer.getName());
        percentengeLabel.setText(offer.getPercentage());

        Image imageSrc = new Image(getClass().getResourceAsStream(offer.getImgSrc()));
        image.setImage(imageSrc);
    }

}
