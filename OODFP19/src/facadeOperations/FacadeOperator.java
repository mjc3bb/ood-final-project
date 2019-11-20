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
	private Dao<Account,String> accountDao;
	private Dao<Transaction,String> transactionDao;
	
	//All the methods will use this connection to work with the database.
	private String url = "jdbc:sqlite:sqlite/db/test.db";
	public JdbcConnectionSource con = null;

	//TODO Used if we still wanted to implement Singleton, but ormlite has it built in.
//	static FacadeOperator fo;
	
	//Change visibility of constructor to private if you needed to make this Singleton, but that's probably not needed.
	public FacadeOperator(JdbcConnectionSource conn) throws SQLException {
		con = conn;
		this.update = new UpdateQueryOperator(con);
		this.select = new SelectQueryOperator(con);
		//Create DAOs for database tables (models)
		this.accountDao = DaoManager.createDao(con, Account.class);
		this.transactionDao = DaoManager.createDao(con, Transaction.class);
	}

	//TODO Hopefully this makes it Singleton if need be, but it is already static on its own.
//	public static synchronized FacadeOperator getFacadeOperator(JdbcConnectionSource conn) throws SQLException {
//		if(fo==null)
//			fo = new FacadeOperator(conn);
//		return fo;
//	}
	
	
	//Several methods to do application operations (that's probably a good name for this class, "ApplicationOperator".

	//****BELONGS TO: UpdateQueryOperator Class*****************************************************************************//
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
	//Create Account.
	//TODO Creating an Account that tracks a separate set of transactions.
	public void createAccount(String accountName, Date AccountStartDate, int startingBalance) throws SQLException {
		//TODO Put this functionality in the UpdateQueryOperator
		update.addAccount(accountName, AccountStartDate, startingBalance);
		
		//TODO To ensure startingBalance can be overwritten when transactions are filled in while creating an account.
		//There may be a better way to do this, like creating a new method.
		if(transactionDao.countOf()>0)
		update.updateBalanceWithStartingBalance(accountName, startingBalance);
	}
	//****^BELONGS TO: UpdateQueryOperator Class^*****************************************************************************//
	
	
	//********v BELONGS TO: SelectQueryOperator Class v*******************************************************//
	//Returns total account balances. All account balances, or specific one's?
	public int returnBalance(String accountName) throws SQLException {
		return select.returnCurrentBalance(accountName);
	}

	//Returns all income for a particular month
	public int returnIncomeByMonth(String MM, String accountName) throws ParseException, SQLException {
		return select.incomeByMonth(MM, accountName);
	}

	//Returns all expenses for a particular month
	public int returnSpendingByMonth(String MM, String accountName) throws ParseException, SQLException {
		return select.expenseByMonth(MM, accountName);
	}

	//Returns the sum of transactions spent in a certain category from certain dates.
	public int returnSpendingInCategoryByMonth(String MM, String categoryName, String accountName) throws ParseException, SQLException {
		return select.expenseByCategoryByMonth(MM, categoryName, accountName);
	}

	//Returns an array list of expense objects from a certain account that contain the attributes dealing with that expense
	public ArrayList<Transaction> returnExpenseObjects(String MM, String accountName) throws SQLException, ParseException{
		//Make sure whatever the month is it can be understood by SQLite
		return select.returnExpenseObjectsByMonth(MM, accountName);
	}

	//Returns array list of account objects that contain the attributes of an account
	public ArrayList<Account> returnAccountObjects() throws SQLException{
		return select.getAccountObjects();
	}

	//Returns array list of expense objects from specific account
	public ArrayList<Transaction> returnExpensesBasedOnAccount(String accountName) throws SQLException{
		return select.returnExpenseObjectsByAccount(accountName);
	}
	//********^BELONGS TO: SelectQueryOperator Class^*******************************************************//

	
	
	//Consider any other methods that would be key features of the application  
	//********** FOR REMOVAL//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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

}
