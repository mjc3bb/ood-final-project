package gui.maincontainer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXDrawer;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.*;
import javafx.scene.layout.*;

public class BaseController implements Initializable {
	
    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXDrawer drawer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
		        	}});
	        	}
	        }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			System.out.println("lol");
		}
	}

	private void loadOverview() {
		drawerAction();
		
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

	public void drawerAction() {
		if (drawer.isOpened()) drawer.close();
		else drawer.open();
	}

}
