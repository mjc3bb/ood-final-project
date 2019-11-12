


import java.io.FileInputStream;
import java.io.IOException;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class UIadapter extends Application {
	
    public static void main(String[] args)     {
        Application.launch(args);
     
    	}
     
    @Override
    public void start(Stage stage) throws IOException {
    	
    	Parent root = FXMLLoader.load(getClass().getResource("UIadapter.fxml"));
    	
        // Create the Scene
        Scene scene = new Scene(root);
        // Set the Scene to the Stage
        stage.setScene(scene);

        // Display the Stage
        stage.show();
    }
}