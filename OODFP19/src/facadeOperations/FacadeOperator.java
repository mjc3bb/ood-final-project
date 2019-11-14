package facadeOperations;

import java.sql.SQLException;
import java.util.Date;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import models.Account;
import models.Category;
import models.Recurring;
import models.Transaction;

public class FacadeOperator {
	//Create several objects of the things that will be used in these methods. Most likely some type of DAO will be passed to each object. 
	//Or maybe the methods they have will require a DAO as an argument.
	
	//TODO: Create SelectQueryOperator and UpdateQueryOperator to condense the operator classes below.
	private AdditonOperator add;
	private BudgetOperator budget;
	private CategoryOperator category;
	private RecurringOperator recurring;
	private Transaction transaction;
	
	//All the methods will use this connection to work with the database.
	private String url = "jdbc:sqlite:sqlite/db/test.db";
	public JdbcConnectionSource con = null;
	
	public FacadeOperator(JdbcConnectionSource conn) throws SQLException {
		con = conn;
		this.add = new AdditonOperator(con);
		this.budget = new BudgetOperator(con);
		this.category = new CategoryOperator(con);
		this.recurring = new RecurringOperator(con);
		this.transaction = new Transaction();
		//Create DAOs for database tables (models)
		Dao<Account,String> accountDao = DaoManager.createDao(con, Account.class);
		Dao<Category, String> categoryDao = DaoManager.createDao(con, Category.class);
		Dao<Recurring, String> recurringDao = DaoManager.createDao(con, Recurring.class);
		Dao<Transaction,String> transactionDao = DaoManager.createDao(con, Transaction.class);
	}
	
	//Create several methods to do application operations (that's probably a good name for this class, "ApplicationOperator".
	
	//TODO:	Find best way to allow user to add new data to the database
	//TODO: Combine AddExpense and AddIncome into AddTransaction.
	//Add Expense
	public void AddExpense() throws SQLException {
		//This would be how the user would add new expenses they made
		//Create transaction to be added
		//TODO No idea if passing connection through will give access to original tables of database
		transaction = new Transaction(10, new Date(), con);
		add.addSingleExpense(transaction);
	}
	
	//Add Income
	public void AddIncome() throws SQLException {
		//This would be how the user would add new income they obtained.
		transaction = new Transaction(10, new Date(), con);
		add.addSingleIncome(transaction);
	}
	//Expenditure tracking
	public void ExpendituresTracking() throws SQLException {
		//Tracks total expenditures and provides table for recurring expenditures, expenditures by month, and total expenditures
		//Hopefully this returns a query result
		String TotalExpenditures = add.addAllExpenses();
		System.out.println(TotalExpenditures);
	}
	
	//Income Tracking
	public void IncomeTracking() throws SQLException{
		//Tracks total income and provides table for recurring income, income by month, and total income.
		System.out.println(add.addAllIncomes());
	}
	
	//Category Addition
	public void addCategory() {
		//Add new categories for your budget
	}
	
	//Add Recurring Expense
	public void recurringExpense() {
		//Add new recurring expenses like bills
	}
	
	//Add Recurring income
	//TODO Not Needed. This will be seen in addIncome and addExpense method
	public void recurringIncome() {
		//Add new recurring income like from a job
	}
	
	//Create Budget.
	//TODO Creating an Account that tracks a separate set of transactions.
	public void createBudget() {
		//User can add different categories for expenses that they allocate out to a budget.
		
	}
	
	//Convert currency
	//TODO Take this out. It's already done
	public void convertCurrency() {
		//Could make user select the currency here or could be passed through as argument.
	}
	
	//Edit budget
	//TODO Change to literally just removing an account
	public void editBudget() {
	}
	
	//Compare actual spending versus budget
	//TODO Won't be necessary since we are treating budgets as accounts
	public void compare() {
		//This will pull data from each category type of spending and compare it to your assigned budget
	}
	
	//Look at specific categorical spending
	//TODO Returns the sum of transactions spent in a certain category.
	public void viewCategory() {
		//returns the total amount spent in a category and optionally shows what was spent in the category you chose area
	}
	
	//Remove entry
	//TODO Worry about later. This is more of an admin thing. The user wouldn't be able to do this.
	public void removeEntry() {
		//Removes some type of entry to your balance. Say you made a return on an item, or some type of income was an accident.
		//This may not be needed at all really. This could just be seen in the changing expenses.
	}
	
	//Consider any other methods that would be key features of the application
	
}
