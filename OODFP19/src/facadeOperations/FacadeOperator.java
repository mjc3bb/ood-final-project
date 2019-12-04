package facadeOperations;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Account;
import models.Expense;

public class FacadeOperator {
	//Create several objects of the things that will be used in these methods. Most likely some type of DAO will be passed to each object. 
	//Or maybe the methods they have will require a DAO as an argument.

	//TODO: Create SelectQueryOperator and UpdateQueryOperator to condense the operator classes below.
	private UpdateQueryOperator update;
	private SelectQueryOperator select;
	private Dao<Account,String> accountDao;
	private Dao<Expense,String> transactionDao;
	
	//All the methods will use this connection to work with the database.
	private String url = "jdbc:sqlite:sqlite/db/data.db";
	public JdbcConnectionSource con = null;

	static FacadeOperator fo;
	
	//Change visibility of constructor to private if you needed to make this Singleton, but that's probably not needed.
	private FacadeOperator() {
		con = getConnection();
		
		try {
			this.update = new UpdateQueryOperator(con);
			this.select = new SelectQueryOperator(con);
			//Create DAOs for database tables (models)
			this.accountDao = DaoManager.createDao(con, Account.class);
			this.transactionDao = DaoManager.createDao(con, Expense.class);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error creating FacadeOperator.");
		}
	}

	private JdbcConnectionSource getConnection() {
		JdbcConnectionSource connectionSource = null;
		try {
			connectionSource = new JdbcConnectionSource(url);
			TableUtils.createTableIfNotExists(connectionSource, Expense.class);
			TableUtils.createTableIfNotExists(connectionSource, Account.class);

		} 
		catch (Exception ex) {
			System.out.println("Cannot connect to sqlite data database.");
		}
		return connectionSource;
	}

    public static synchronized FacadeOperator getNameFactory() {
        if (fo == null) {
            fo = new FacadeOperator();
        }
        return fo;
    }
	
	
	//Several methods to do application operations (that's probably a good name for this class, "ApplicationOperator".

	//****BELONGS TO: UpdateQueryOperator Class*****************************************************************************//
	public void addTransaction(double amount, Date dateOfTransaction, String location, String accountName, String category, boolean recurring) throws SQLException, ParseException {
		//TODO Put this functionality in the UpdateQueryOperator
		update.addTransaction(amount, dateOfTransaction, location, accountName, category, recurring);
	}

	public void addIncome(String accountName, double amount, Account account) throws SQLException {
		update.addIncome(accountName, amount, account);
	}

	public void addExpense(double amount, String location, Date date, String accountName, String category, boolean recurring) throws SQLException, ParseException {
		update.addTransaction(amount, date, location, accountName, category, recurring);
	}
	//Create Account.
	//TODO Creating an Account that tracks a separate set of transactions.
	public void createAccount(String accountName, String accountType, double accountBalance) throws SQLException {
		//TODO Put this functionality in the UpdateQueryOperator
		update.addAccount(accountName, accountType, accountBalance);
		
		//TODO To ensure startingBalance can be overwritten when transactions are filled in while creating an account.
		//There may be a better way to do this, like creating a new method.
		//if(transactionDao.countOf()>0)
		//update.updateBalance(accountName);
	}
	//****^BELONGS TO: UpdateQueryOperator Class^*****************************************************************************//
	
	
	//********v BELONGS TO: SelectQueryOperator Class v*******************************************************//
	//Returns total account balances. All account balances, or specific one's?
	public double returnBalance(String accountName) throws SQLException {
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
	public ArrayList<Expense> returnExpenseObjects(String MM) throws SQLException, ParseException {
		//Make sure whatever the month is it can be understood by SQLite
		return select.returnExpenseObjectsByMonth(MM);
	}
	
	// Returns all expenses in db.
	public ArrayList<Expense> returnAllExpenseObjects() throws SQLException, ParseException {
		//Make sure whatever the month is it can be understood by SQLite
		return select.returnAllExpenseObjects();
	}

	//Returns array list of account objects that contain the attributes of an account
	public ArrayList<Account> returnAccountObjects() throws SQLException{
		return select.getAccountObjects();
	}

	//Returns array list of expense objects from specific account
	public ArrayList<Expense> returnExpensesBasedOnAccount(String accountName) throws SQLException{
		return select.returnExpenseObjectsByAccount(accountName);
	}

}
