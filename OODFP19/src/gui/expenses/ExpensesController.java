package gui.expenses;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.MenuItem;
import facadeOperations.FacadeOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Account;
import models.Expense;
import observableObjects.ObservableTransaction;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

public class ExpensesController implements Initializable {

    @FXML
    private TableView<ObservableTransaction> expensesTable;

    @FXML
    private TableColumn<ObservableTransaction, String> location, category, recurring, account, date;

    @FXML
    private TableColumn<ObservableTransaction, Double> amount;

    @FXML
    private Label monthHeader, addExpenseLabel;

    @FXML
    private JFXTextField expenseAmount, expenseLocation;

    @FXML
    private MenuButton expenseAccount, expenseCategory;

    @FXML
    private JFXDatePicker expenseDate;

    @FXML
    private JFXButton submitExpense, changeMonth;

    @FXML
    private JFXCheckBox reccuringCheckbox;

    @FXML
    private Label categoryHeader, groceryCategory, transportationCategory, diningCategory, shoppingCategory,
    utilitiesCategory, entertainmentCategory, groceryAmount, transportationAmount, diningAmount, shoppingAmount,
    utilitiesAmount, entertainmentAmount, seasonalCategory, seasonalAmount;
    
    private double grocerySpending, transportationSpending, diningSpending, shoppingSpending, utilitiesSpending, entertainmentSpending, seasonalSpending;
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////// DATABASE OPERATION  //////////////////////////////////////////////////////////////
 
    private ObservableList<ObservableTransaction> transactionsTableList = FXCollections.observableArrayList();
    FacadeOperator database = FacadeOperator.getNameFactory();
    ArrayList<Expense> dbTransactions = new ArrayList<Expense>();

	private void refreshTransactionTable() {
		try {
			dbTransactions = database.returnAllExpenseObjects();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		ArrayList<ObservableTransaction> conversion = new ArrayList<ObservableTransaction>();
    	for (Expense t : dbTransactions) {
    		ObservableTransaction at = new ObservableTransaction(t);
    		Account ac = t.getAccount();
    		conversion.add(at);
    	}
    	transactionsTableList.setAll(conversion);
    	expensesTable.setItems(transactionsTableList);
		
	}

	public void addExpense() {
    	if (!checkFields()) return;
    	double amount = 0;
    	try {
    		amount = Double.parseDouble(expenseAmount.getText());
    	}
    	catch (Exception ex) {
    		System.out.println("Cannot parse provided double value.");
    	}
    	String location = expenseLocation.getText();
    	Date date = java.sql.Date.valueOf(expenseDate.getValue());
    	String account = expenseAccount.getText().toString();
    	String category = expenseCategory.getText().toString();
    	boolean recurring = reccuringCheckbox.isSelected();
    	try {
			database.addExpense(amount, location, date, account, category, recurring);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	refreshTransactionTable();
    	resetAddExpense();
    	updateExpenseCategoryLabels();
    	
    }	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////// HELPERS //////////////////////////////////////////////////////////////////
	
	/**
	 * Initializes view.
	 */
    @Override
	public void initialize(URL Ulocation, ResourceBundle resources) {
		location.setCellValueFactory(new PropertyValueFactory<>("location"));
		category.setCellValueFactory(new PropertyValueFactory<>("category"));
		amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		account.setCellValueFactory(new PropertyValueFactory<>("account"));
		recurring.setCellValueFactory(new PropertyValueFactory<>("recurring"));
		refreshTransactionTable();
		addAccountsToAccountSelectAction();
		updateExpenseCategoryLabels();
		
	}
	
    /**
     * Helper method to add accounts to menu button.
     */
    private void addAccountsToAccountSelectAction() {
    	List<MenuItem> items = new ArrayList<MenuItem>();
    	List<Account> dbAccounts = new ArrayList<Account>();
		try {
			dbAccounts = database.returnAccountObjects();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for (Account a : dbAccounts) {
			MenuItem temp = new MenuItem();
			temp.setText(a.getAccountName());
			items.add(temp);
			temp.setOnAction( (e) -> {
				expenseAccount.setText(a.getAccountName());
    		});
		}
		expenseAccount.getItems().addAll(items);
		
	}
    
    /**
     * Updates the category menu button.
     * @param e
     */
	public void updateSelectCategory(ActionEvent e) {
		MenuItem item = (MenuItem) e.getSource();
		expenseCategory.setText(item.getText());
	}
	
	/**
	 * Checks if all fields are filled before performing database update.
	 * @return boolean
	 */
	private boolean checkFields() {
		if (expenseAmount.getText().isEmpty() ||
			expenseLocation.getText().isEmpty() ||
			expenseDate.getValue().toString().isEmpty() ||
			expenseAccount.getText().equalsIgnoreCase("Account") ||
			expenseCategory.getText().contentEquals("Category")
			){
			return false;
		}
		return true;
	}
    
	/**
	 * Returns Account object from database based on Account name. May not be needed.
	 * @param account2
	 * @return
	 */
	private Account getAccount(String account2) {
		Account result = new Account();
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			accounts = database.returnAccountObjects();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Account ac : accounts) {
			if (ac.getAccountName().contentEquals(account2)) {
				result = ac;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Resets fields in view.
	 */
	private void resetAddExpense() {
		expenseAmount.clear();
		expenseLocation.clear();
		expenseDate.getEditor().clear();
		expenseAccount.setText("Account");
		expenseCategory.setText("Category");
		reccuringCheckbox.setSelected(false);
		
	}
	
	/**
	 * Adds expenses to labels.
	 */
	private void updateExpenseCategoryLabels() {
		ArrayList<Expense> expenses = new ArrayList<Expense>();
		try {
			expenses = database.returnAllExpenseObjects();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getExpenseCategoryTotals(expenses);
		
		
	}

	private void getExpenseCategoryTotals(ArrayList<Expense> expenses) {
		
		resetExpenseCategories();
		
		if (expenses.isEmpty()) return;
		
		for (Expense e : expenses) {
			String cat = e.getCategory();
			if (cat == null) continue;
			switch (cat) {
			case "Grocery"			: 	grocerySpending += e.getTransaction(); break;
			case "Transportation"	: 	transportationSpending += e.getTransaction(); break;
			case "Bill/Utility"		: 	utilitiesSpending += e.getTransaction(); break;
			case "Dining"			: 	diningSpending += e.getTransaction(); break;
			case "Shopping"			: 	shoppingSpending += e.getTransaction(); break;
			case "Entertainment"	: 	entertainmentSpending += e.getTransaction(); break;
			case "Seasonal"			: 	seasonalSpending += e.getTransaction(); break;
			default					:	break;
			}
		}
		
		groceryAmount.setText(Double.toString(grocerySpending));
		transportationAmount.setText(Double.toString(transportationSpending));
		diningAmount.setText(Double.toString(diningSpending));
		shoppingAmount.setText(Double.toString(shoppingSpending));
		utilitiesAmount.setText(Double.toString(utilitiesSpending));
		entertainmentAmount.setText(Double.toString(entertainmentSpending));
		seasonalAmount.setText(Double.toString(seasonalSpending));
		
	}

	private void resetExpenseCategories() {
	
		grocerySpending = 0.00;
		transportationSpending = 0.00;
		diningSpending = 0.00;
		shoppingSpending = 0.00;
		utilitiesSpending = 0.00;
		entertainmentSpending = 0.00;
		seasonalSpending = 0.00;
	}
	
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
