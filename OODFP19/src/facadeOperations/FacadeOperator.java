package facadeOperations;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import models.Account;
import models.Transaction;

public class FacadeOperator {
	//Create several objects of the things that will be used in these methods. Most likely some type of DAO will be passed to each object. 
	//Or maybe the methods they have will require a DAO as an argument.

	//TODO: Create SelectQueryOperator and UpdateQueryOperator to condense the operator classes below.
	private UpdateQueryOperator update;
	private SelectQueryOperator select;
//	private AdditonOperator add;
	private Transaction transaction;

	//All the methods will use this connection to work with the database.
	private String url = "jdbc:sqlite:sqlite/db/test.db";
	public JdbcConnectionSource con = null;

	public FacadeOperator(JdbcConnectionSource conn) throws SQLException {
		con = conn;
		this.update = new UpdateQueryOperator(con);
		this.select = new SelectQueryOperator(con);
//		this.add = new AdditonOperator(con);
		this.transaction = new Transaction();
		//Create DAOs for database tables (models)
		Dao<Account,String> accountDao = DaoManager.createDao(con, Account.class);
		Dao<Transaction,String> transactionDao = DaoManager.createDao(con, Transaction.class);
	}

	//Create several methods to do application operations (that's probably a good name for this class, "ApplicationOperator".

	//TODO: Make sure to include instantiation of transaction object

	//TODO BELONGS TO: UpdateQueryOperator Class
	public void addTransaction(int amount, String dateOfTransaction, String location, String accountName, String category, boolean recurring) throws SQLException, ParseException {
		//TODO Put this functionality in the UpdateQueryOperator
		update.addTransaction(amount, dateOfTransaction, location, accountName, category, recurring);
		update.updateBalance(accountName);
	}

	public void addIncome(String accountName, int amount) throws SQLException {
		update.addIncome(accountName, amount);
		update.updateBalance(accountName);
	}

	public void addExpense(int amount, String location, String date, String accountName, String category, boolean recurring) throws SQLException, ParseException {
		update.addTransaction(amount, date, location, accountName, category, recurring);
		update.updateBalance(accountName);
	}
	//THE ABOVE Methods should also update the account balance for the given accountName. Will be done in separateupdate.method().////////////////////////////////////

	//Create Account.
	//TODO Creating an Account that tracks a separate set of transactions.
	public void createAccount(String accountName, Date AccountStartDate) throws SQLException {
		//TODO Put this functionality in the UpdateQueryOperator
		update.addAccount(accountName, AccountStartDate);
		update.updateBalance(accountName);
	}

	//TODO BELONGS TO: SelectQueryOperator Class
	//Returns total account balances. All account balances, or specific one's?
	public int returnBalance(String accountName) {
		return select.returnCurrentBalance(accountName);
	}

	//Returns all income for a particular month
	public int returnIncomeByMonth(String MM) {
		return select.incomeByMonth(MM);
	}

	//Returns all expenses for a particular month
	public int returnSpendingByMonth(String MM) {
		return select.expenseByMonth(MM);
	}

	//Returns the sum of transactions spent in a certain category from certain dates.
	public int returnSpendingInCategoryByMonth(String MM, String categoryName) {
		return select.expenseByCategoryByMonth(MM, categoryName);
	}

	//Returns an array list of expense objects that contain the attributes dealing with that expense
	public ArrayList<Object> returnExpenseObjects(String MM){
		//Make sure whatever the month is it can be understood by SQLite
		return select.returnExpenseObjectsByMonth(MM);
	}

	//Returns array list of account objects that contain the attributes of an account
	public ArrayList<Object> returnAccountObjects(){
		return select.getAccountObjects();
	}

	//Returns array list of expense objects from specific account
	public ArrayList<Object> returnExpensesBasedOnAccount(String accountName){
		return select.returnExpenseObjectsByAccount(accountName);
	}


	
	
	
	//BEING CONSIDERED FOR REMOVAL//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	//TODO May be able to take out
//	//Category Addition
//	public void addCategory() {
//		//Add new categories for your budget
//	}
//	//Remove entry
//	//TODO Worry about later. This is more of an admin thing. The user wouldn't be able to do this.
//	public void removeEntry() {
//		//Removes some type of entry to your balance. Say you made a return on an item, or some type of income was an accident.
//		//This may not be needed at all really. This could just be seen in the changing expenses.
//	}
//	//Add Expense
//	public void AddExpense() throws SQLException {
//		//This would be how the user would add new expenses they made
//		//Create transaction to be added
//		//TODO No idea if passing connection through will give access to original tables of database
//		//transaction = new Transaction(10, new Date(), con);
//		add.addSingleExpense(transaction);
//	}
//
//	//Add Income
//	public void AddIncome() throws SQLException {
//		//This would be how the user would add new income they obtained.
//		//transaction = new Transaction(10, new Date(), con);
//		add.addSingleIncome(transaction);
//	}
//	//Expenditure tracking
//	public void ExpendituresTracking() throws SQLException {
//		//Tracks total expenditures and provides table for recurring expenditures, expenditures by month, and total expenditures
//		//Hopefully this returns a query result
//		String TotalExpenditures = add.addAllExpenses();
//		System.out.println(TotalExpenditures);
//	}
//
//	//Income Tracking
//	public void IncomeTracking() throws SQLException{
//		//Tracks total income and provides table for recurring income, income by month, and total income.
//		System.out.println(add.addAllIncomes());
//	}
////	TODO Change to literally just removing an account
//		public void removeAccount() {
//			//TODO Put in UpdateQueryOperator
//		}
	//Consider any other methods that would be key features of the application

}
