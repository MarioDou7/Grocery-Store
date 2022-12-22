package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public void registration() throws IOException {
        FXMLLoader fxmlLoaderregister = new FXMLLoader();
        fxmlLoaderregister.setLocation(this.getClass().getResource("/views/register-view.fxml"));
        Scene scene = new Scene(fxmlLoaderregister.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
