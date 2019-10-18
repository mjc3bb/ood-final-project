package gui.draweritems;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DrawerController implements Initializable {

	@FXML
    private Label welcomeLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Button overviewButton;

    @FXML
    private Button expensesButton;

    @FXML
    private Button incomeButton;

    @FXML
    private Button utilitiesButton;

    @FXML
    private Button manageButton;
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
    
}
