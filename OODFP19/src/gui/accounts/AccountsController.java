package gui.accounts;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Account;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import facadeOperations.FacadeOperator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import observableObjects.ObservableAccount;
import javafx.scene.control.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountsController implements Initializable{

    @FXML
    private MenuButton selectAccountActivity;

//    @FXML
//    private TableView<?> activityTable;
//
//    @FXML
//    private TableColumn<?, ?> date;
//
//    @FXML
//    private TableColumn<?, ?> location;
//
//    @FXML
//    private TableColumn<?, ?> typeActivity;
//
//    @FXML
//    private TableColumn<?, ?> amount;
    
    @FXML
    private TableView<ObservableAccount> accountsTable;

     @FXML
    private TableColumn<ObservableAccount, String> name, type;

    @FXML
    private TableColumn<ObservableAccount, Double> balance;
    
    @FXML
    private Label accountActivity, createNewAccountHeader, addIncome, accountsHeader;

    @FXML
    private JFXTextField createAccountName, createAccountBalance, addIncomeAmount;

    @FXML
    private MenuButton createAccountType, addIncomeSelectAcount;

    @FXML
    private JFXButton createSubmit, addSubmit;
    
    @FXML
    private MenuItem typeChecking, typeSavings;
    
    FacadeOperator database = new FacadeOperator();
    
    private ObservableList<ObservableAccount> accountsTableList = FXCollections.observableArrayList();
    ArrayList<Account> dbAccounts = new ArrayList<Account>();
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
		refreshAccountsTable();
		addAccountsToIncomeAction();
		
	}

    
    private void addAccountsToIncomeAction() {
		List<MenuItem> items = new ArrayList<MenuItem>();
		for (Account a : dbAccounts) {
			MenuItem temp = new MenuItem();
			temp.setText(a.getAccountName());
			items.add(temp);
			temp.setOnAction( (e) -> {
        		addIncomeSelectAcount.setText(a.getAccountName());
    		});
		}
		addIncomeSelectAcount.getItems().addAll(items);
		
	}


	public void createAccount() {
    	if (!createAccountName.getText().isEmpty() && !createAccountBalance.getText().isEmpty() && !createAccountType.getText().equalsIgnoreCase("Account Type")) {
    		String accountName = createAccountName.getText();
    		double accountBalance = 0;
    		try {
    			accountBalance = Double.parseDouble(createAccountBalance.getText());
    		} catch (Exception ex) {
    			System.out.println("Error parsing double value. Can't create account.");
    		}
    		String accountType = createAccountType.getText();
    		
    		try {
				database.createAccount(accountName, accountType, accountBalance);
			} 
    		catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Cannot add account.");
			}
    		
    		refreshAccountsTable();
    		resetAddAccount();
    		
    	}
    }
    
    public void addIncome() {
    	
    	if (addIncomeSelectAcount.getText().equalsIgnoreCase("Select Account")) {
    		return;
    	}
    	
    	double amount = 0;
    	
    	try {
    		amount = Double.parseDouble(addIncomeAmount.getText());
    		database.addIncome(addIncomeSelectAcount.getText(), amount);
    		addIncomeSelectAcount.setText("Select Account");
    		addIncomeAmount.clear();
    	} 
    	catch (Exception ex) {
    		System.out.println("Can't add income.");
    		ex.printStackTrace();
    	}
    	
    	refreshAccountsTable();

    	
    }
    
    private void resetAddAccount() {
		createAccountName.clear();
		createAccountBalance.clear();
		createAccountType.setText("Account Type");
	}


	private void refreshAccountsTable() {
    	try {
			dbAccounts = database.returnAccountObjects();
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	ArrayList<ObservableAccount> conversion = new ArrayList<ObservableAccount>();
    	for (Account a : dbAccounts) {
    		ObservableAccount ac = new ObservableAccount(a);
    		conversion.add(ac);
    	}
    	accountsTableList.setAll(conversion);
    	accountsTable.setItems(accountsTableList);
	}

	public void selectChecking() {
    	createAccountType.setText("Checking");
    }
    
    public void selectSavings() {
    	createAccountType.setText("Savings");
    }
}

