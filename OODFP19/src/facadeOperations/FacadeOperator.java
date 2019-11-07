package facadeOperations;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import models.Budget;
import models.Category;
import models.Recurring;
import models.Transaction;

public class FacadeOperator {
	//Create several objects of the things that will be used in these methods. Most likely some type of DAO will be passed to each object. 
	//Or maybe the methods they have will require a DAO as an argument.
	private Additon add;
	private Budget_ budget;
	private Category_ cat;
	private Recurring_ recurring;
	private Transaction transaction;
	
	//All the methods will use this connection to work with the database.
	private String url = "jdbc:sqlite:sqlite/db/test.db";
	public JdbcConnectionSource con = null;
	
	public FacadeOperator(JdbcConnectionSource conn) throws SQLException {
		con = conn;
		this.add = new Additon(con);
		this.budget = new Budget_(con);
		this.cat = new Category_(con);
		this.recurring = new Recurring_(con);
		this.transaction = new Transaction();
		//Create DAOs for database tables (models)
		Dao<Budget,String> budgetDao = DaoManager.createDao(con, Budget.class);
		Dao<Category, String> categoryDao = DaoManager.createDao(con, Category.class);
		Dao<Recurring, String> recurringDao = DaoManager.createDao(con, Recurring.class);
		Dao<Transaction,String> transactionDao = DaoManager.createDao(con, Transaction.class);
	}
	
	//Create several methods to do application operations (that's probably a good name for this class, "ApplicationOperator".
	
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
	public void recurringIncome() {
		//Add new recurring income like from a job
	}
	
	//Create Budget
	public void createBudget() {
		//User can add different categories for expenses that they allocate out to a budget.
	}
	
	//Convert currency
	public void convertCurrency() {
		//Could make user select the currency here or could be passed through as argument.
	}
	
	//Edit budget
	public void editBudget() {
		//Allows user to add or remove categories from their budget and reallocate their money
	}
	
	//Compare actual spending versus budget
	public void compare() {
		//This will pull data from each category type of spending and compare it to your assigned budget
	}
	
	//Look at specific categorical spending
	public void viewCategory() {
		//returns the total amount spent in a category and optionally shows what was spent in the category you chose area
	}
	
	//Remove entry
	public void removeEntry() {
		//Removes some type of entry to your balance. Say you made a return on an item, or some type of income was an accident.
		//This may not be needed at all really. This could just be seen in the changing expenses.
	}
	
	//Consider any other methods that would be key features of the application
	
}
