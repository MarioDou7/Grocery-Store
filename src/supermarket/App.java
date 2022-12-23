package supermarket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import supermarket.Controllers.DatabaseController;
import supermarket.Controllers.ProductController;

public class App extends Application {

    DatabaseController db = new DatabaseController();
    ProductController pd = new ProductController();
    public void start(Stage primaryStage)
    {
        try
        {
            db.create();
            System.out.println("Tables Created Successfully");
            db.insert();
            System.out.println("Data Inserted  Successfully");

            //pd.loadProducts();
            //System.out.println("Products Loaded  Successfully");

            Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/Login.fxml"));

            Scene scene = new Scene(root, GlobalConstants.SCENE_WIDTH, GlobalConstants.SCENE_HEIGHT);

            primaryStage.setTitle("Productsss");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
