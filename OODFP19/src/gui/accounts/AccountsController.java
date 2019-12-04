package gui.accounts;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Account;
import models.Expense;

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
import observableObjects.ObservableTransaction;
import javafx.scene.control.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountsController implements Initializable{

    @FXML
    private MenuButton selectAccountActivity;

    @FXML
    private TableView<ObservableTransaction> activityTable;

    @FXML
    private TableColumn<ObservableTransaction, String> date, location, typeActivity;

    @FXML
    private TableColumn<ObservableTransaction, Double> amount;
    
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
    private JFXButton createSubmit, addSubmit, viewAccount;
    
    @FXML
    private MenuItem typeChecking, typeSavings;
    
    FacadeOperator database = FacadeOperator.getNameFactory();
    
    private ObservableList<ObservableAccount> accountsTableList = FXCollections.observableArrayList();
    private ObservableList<ObservableTransaction> activity = FXCollections.observableArrayList();
    ArrayList<Account> dbAccounts = new ArrayList<Account>();
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		this.location.setCellValueFactory(new PropertyValueFactory<>("location"));
		typeActivity.setCellValueFactory(new PropertyValueFactory<>("category"));
		amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		refreshAccountsTable();
		addAccountsToIncomeAction();
		
	}

    
    private void addAccountsToIncomeAction() {
		List<MenuItem> items = new ArrayList<MenuItem>();
		List<MenuItem> itemsTrans = new ArrayList<MenuItem>();
		for (Account a : dbAccounts) {
			MenuItem temp = new MenuItem();
			temp.setText(a.getAccountName());
			MenuItem temp2 = new MenuItem();
			temp2.setText(a.getAccountName());
			items.add(temp);
			itemsTrans.add(temp2);
			temp.setOnAction( (e) -> {
        		addIncomeSelectAcount.setText(a.getAccountName());
    		});
			temp2.setOnAction( (e) -> {
        		selectAccountActivity.setText(a.getAccountName());
    		});
		}
		addIncomeSelectAcount.getItems().addAll(items);
		selectAccountActivity.getItems().addAll(itemsTrans);
		
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
    		addAccountsToIncomeAction();
    		
    	}
    }
    
    public void addIncome() {
    	
    	if (addIncomeSelectAcount.getText().equalsIgnoreCase("Select Account")) {
    		return;
    	}
    	
    	double amount = 0;
    	
    	try {
    		amount = Double.parseDouble(addIncomeAmount.getText());
    		Account temp = getAccountByName(addIncomeSelectAcount.getText());
    		database.addIncome(addIncomeSelectAcount.getText(), amount, temp);
    		addIncomeSelectAcount.setText("Select Account");
    		addIncomeAmount.clear();
    	} 
    	catch (Exception ex) {
    		System.out.println("Can't add income.");
    		ex.printStackTrace();
    	}
    	
    	refreshAccountsTable();

    	
    }
    
    private Account getAccountByName(String text) {
		for (Account a : dbAccounts) {
			if (a.getAccountName().equalsIgnoreCase(text)) return a;
		}
		return null;
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
    
    public void addAccountActivity() {
    	if (selectAccountActivity.getText().equalsIgnoreCase("Select Account")) return;
    	
    	String acct = selectAccountActivity.getText();
    	ArrayList<Expense> expenses = new ArrayList<Expense>();
    	
    	try {
			expenses = database.returnAllExpenseObjects();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	ArrayList<ObservableTransaction> obs = new ArrayList<ObservableTransaction>();
    	for(Expense e : expenses) {
    		if (e.getAccountName().equalsIgnoreCase(acct)) {
    			obs.add(new ObservableTransaction(e));
    		}
    	}
    	activity.clear();
    	activity.addAll(obs);
    	activityTable.setItems(activity);
    	
    	
    }
}

