package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DatabaseHelper;
import java.io.IOException;

public class RegisterController {
    DatabaseHelper db = new DatabaseHelper();
    String Name;
    String Email;
    String PhoneNo;
    String Country;
    String Password;
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

}
