package gui.maincontainer;

import java.io.IOException;
import javafx.stage.FileChooser;
import java.net.URL;
import java.util.ResourceBundle;
import objects.ViewChanger;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class BaseController implements Initializable {
	
    @FXML
    private AnchorPane drawerPane, viewPane;

    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private JFXButton minimize, fullscreen, close;
    
    @FXML 
    private Label windowName;
    
    private double x, y;
    
    private ViewChanger changeView = new ViewChanger();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeSideDrawer();
		viewPane.getChildren().setAll(changeView.loadOverview());
		windowName.setText("Overview");
		
	}

	/**
	 * Loads Overview view.
	 */
	@FXML
	private void loadOverview() {
		drawerAction(); 
		viewPane.getChildren().setAll(changeView.loadOverview());
		windowName.setText("Overview");
	}

	private void loadExpenses() {
		drawerAction();		
	}

	private void loadIncome() {
		drawerAction();		
	}

	private void loadUtilities() {
		drawerAction();
	}

	private void loadManage() {
		drawerAction();		
	}
 
	/**
	 * Open or close side drawer.
	 */
	public void drawerAction() {
		if (drawer.isOpened()) drawer.close();
		else drawer.open();
	}
	
	/**
	 * Update window coordinates upon dragging window.
	 * @param event
	 */
	@FXML
	private void dragged(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}

	/**
	 * Grabs coordinates of mouse on window.
	 * @param event
	 */
	@FXML
	private void pressed(MouseEvent event) {
		x = event.getSceneX();
		x = event.getSceneY();
	}
	
	/**
	 * Close the window.
	 */
	@FXML
	private void close() {
		System.exit(0);
	}
	
	/**
	 * Minimize Window
	 */
	@FXML
	private void minimize() {
		Stage stage = (Stage) minimize.getScene().getWindow();
		stage.setIconified(true);
		
	}
	
	/**
	 * Load side drawer upon application initialization.
	 */
	private void initializeSideDrawer() {
		
		try {
			VBox box = FXMLLoader.load(getClass().getResource("/gui/draweritems/drawer.fxml"));
	        drawer.setSidePane(box);
	        
	        // Allow drawer to be closed from drawer button.
	        AnchorPane ap = (AnchorPane) box.getChildren().get(0);
	       	ap.getChildren().get(1).addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> 
	       		{
	        		drawer.close();
	        	});
	       	
	        // Assign views to buttons.
	        for (Node node : box.getChildren()) {
	        	if (node.getId() != null) {
	        		node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
		        		switch (node.getId()) {
		        		
		        		case "overviewButton"	:
		        			loadOverview(); 
		        			break;
		        		
		        		case "expensesButton"	:
		        			loadExpenses();
		        			break;
		        			
		        		case "incomeButton"		:
		        			loadIncome();
		        			break;
		        			
		        		case "utilitiesButton"	:
		        			loadUtilities();
		        			break;
		        		
		        		case "manageButton"		:
		        			loadManage();
		        			break;
		        		}
	        		});
	        	}
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
