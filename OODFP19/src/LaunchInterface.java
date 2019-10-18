import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class LaunchInterface extends Application {

	public static void execute(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/maincontainer/base.fxml"));
		primaryStage.setTitle("Welcome");
	    primaryStage.setScene(new Scene(root));
	    //primaryStage.getIcons().add(new Image("application icon here"));
	    primaryStage.show();
	}


}
