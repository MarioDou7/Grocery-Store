package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.MyListener;
import model.Item;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent)
    {
        myListener.OnCLickListener(item);
    }

    private Item item;
    private MyListener myListener;

    public void setData(Item item, MyListener myListener) {
        this.item=item;
        this.myListener=myListener;
        nameLabel.setText(item.getName());
        priceLabel.setText("EGP" + item.getPrice());
        Image image = new Image(this.getClass().getResourceAsStream(item.getImgSrc()));        img.setImage(image);
    }
}
