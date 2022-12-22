package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.MyListener;
import model.Item;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {

    @FXML
    private ImageView ItemImg;

    @FXML
    private Label ItemNameLabel;

    @FXML
    private Label ItemPriceLabel;

    @FXML
    private Label amount;

    @FXML
    private AnchorPane anchor;

    @FXML
    private VBox chosenCard;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    public MarketController() {
    }

    private List<Item> ItemsList = new ArrayList<>();
    private Image image;
    private MyListener myListener;


    private List<Item> getData() {
        List<Item> ItemsList = new ArrayList<>();
        Item item = new Item();
        item.setName("Kiwi");
        item.setPrice(2.99D);
        item.setImgSrc("/images/kiwi.png");
        ItemsList.add(item);
        Item item1 = new Item();
        item1.setName("Banana");
        item1.setPrice(3.99D);
        item1.setImgSrc("/images/banana.png");
        ItemsList.add(item1);
        Item item2 = new Item();
        item2.setName("Grapes");
        item2.setPrice(4.50D);
        item2.setImgSrc("/images/grapes.png");
        ItemsList.add(item2);
        Item item3 = new Item();
        item3.setName("Mango");
        item3.setPrice(4.50D);
        item3.setImgSrc("/images/mango.png");
        ItemsList.add(item3);
        Item item4 = new Item();
        item4.setName("Orange");
        item4.setPrice(4.50D);
        item4.setImgSrc("/images/orange.png");
        ItemsList.add(item4);
        Item item5 = new Item();
        item5.setName("Coconut");
        item5.setPrice(4.50D);
        item5.setImgSrc("/images/coconut.png");
        ItemsList.add(item5);

        return ItemsList;
    }
    private void setChosenItem(Item item) {
        this.ItemNameLabel.setText(item.getName());
        this.ItemPriceLabel.setText("$" + item.getPrice());
        image = new Image(getClass().getResourceAsStream(item.getImgSrc()));
        ItemImg.setImage(image);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.ItemsList.addAll(this.getData());
        if (ItemsList.size() > 0)
        {
            setChosenItem(ItemsList.get(0));
            myListener = new MyListener() {
                @Override
                public void OnCLickListener(Item item) {
                    setChosenItem(item);
                }
            };
        }
        int column=0;
        int row=1;
        try {
            for (int i = 0; i < this.ItemsList.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();
                ItemController itemController = (ItemController) fxmlLoader.getController();
                itemController.setData((Item)this.ItemsList.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                this.grid.add(anchorPane, column++, row);
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void increase()
    {
        int current_amount = Integer.valueOf(amount.getText());

        //if current_amount = Product.quantity? return;
        this.amount.setText(String.valueOf(current_amount+1));
    }

    public void decrease()
    {
        int current_amount = Integer.valueOf(amount.getText());

        //if current_amount = Product.quantity? return;
        this.amount.setText(String.valueOf(current_amount-1));
    }


}