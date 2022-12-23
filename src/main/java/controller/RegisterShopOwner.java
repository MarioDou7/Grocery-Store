package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterShopOwner {
        public void Add_Owner() throws IOException {
            FXMLLoader fxmlLoaderLogin = new FXMLLoader();
            fxmlLoaderLogin.setLocation(this.getClass().getResource("/views/register-view.fxml"));
            Scene scene = new Scene(fxmlLoaderLogin.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
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
