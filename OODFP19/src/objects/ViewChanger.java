package objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ViewChanger {
	
	private final String OVERVIEW = "/gui/overview/Overview.fxml";
	private final String EXPENSES = "/gui/expenses/Expenses.fxml";
	private final String ACCOUNTS = "/gui/accounts/Accounts.fxml";

	public AnchorPane loadOverview() {
		AnchorPane result = null;
		
		try {
			 result = FXMLLoader.load(getClass().getResource(OVERVIEW));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public AnchorPane loadExpenses() {
		AnchorPane result = null;
		
		try {
			 result = FXMLLoader.load(getClass().getResource(EXPENSES));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public AnchorPane loadAccounts() {
		AnchorPane result = null;
		
		try {
			 result = FXMLLoader.load(getClass().getResource(ACCOUNTS));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
