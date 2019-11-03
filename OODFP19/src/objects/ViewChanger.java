package objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ViewChanger {
	
	private final String PATHTOOVERVIEW = "/gui/overview/Overview.fxml";

	public AnchorPane loadOverview() {
		AnchorPane result = null;
		
		try {
			 result = FXMLLoader.load(getClass().getResource(PATHTOOVERVIEW));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
