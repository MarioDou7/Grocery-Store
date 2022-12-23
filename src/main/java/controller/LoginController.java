package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public void Register() throws IOException {
        FXMLLoader fxmlLoaderLogin = new FXMLLoader();
        fxmlLoaderLogin.setLocation(this.getClass().getResource("/views/register-view.fxml"));
        Scene scene = new Scene(fxmlLoaderLogin.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void RegisterOwner() throws IOException {
        FXMLLoader fxmlLoaderLogin = new FXMLLoader();
        fxmlLoaderLogin.setLocation(this.getClass().getResource("/views/shop-register-view.fxml"));
        Scene scene = new Scene(fxmlLoaderLogin.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
