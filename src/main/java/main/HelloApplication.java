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
        URL url = new File("D:\\ASU FCIS 2023\\Year 4\\Semester 1\\3. Software Design Patterns\\Project\\GUI\\Grocery-Store-GUI\\Grocery2\\src\\main\\resources\\views\\market.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene((Parent) fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}