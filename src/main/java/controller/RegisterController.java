package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.DatabaseHelper;
import java.io.IOException;

public class RegisterController {
    @FXML
    private ChoiceBox<String> locChoice;

    ObservableList<String> countryList = FXCollections.observableArrayList("Egypt", "USA", "United Kingdom", "Italy");
    DatabaseHelper db = new DatabaseHelper();
    @FXML
    String Name;
    String Email;
    String PhoneNo;
    String Country;
    String Password;

    @FXML
    private void initialize()
    {
        locChoice.setValue("Egypt");
        locChoice.setItems(countryList);
    }

    public void register() throws IOException {
        FXMLLoader fxmlLoaderRegister = new FXMLLoader();
        fxmlLoaderRegister.setLocation(this.getClass().getResource("/views/register-view.fxml"));
        Scene scene = new Scene(fxmlLoaderRegister.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        db.ConnectDB();
        db.Add_User(Name, Email, PhoneNo, Country, Password);
        db.Close_Connection();
    }
    public void login() throws IOException {
        FXMLLoader fxmlLoaderlogin = new FXMLLoader();
        fxmlLoaderlogin.setLocation(this.getClass().getResource("/views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoaderlogin.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
