package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.scene.Parent;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String path = "C:\\Users\\Philip\\Desktop\\Grocery-Store-Loka-Phipo-GUI\\src\\main\\resources\\views\\market.fxml";
        URL url = new File(path).toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene((Parent) fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}