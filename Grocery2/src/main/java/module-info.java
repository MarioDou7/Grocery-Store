module com.example.grocery2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main to javafx.fxml;
    exports main;
    exports controller;
    opens controller to javafx.fxml;
}