package facadeOperations;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class Category_ {

	private JdbcConnectionSource conn = null;
	
	Category_(JdbcConnectionSource con){
		this.conn = con;
	}
	
	public String DisplayTransactionsInCategory() {
		return null;
	}
	
	public String DisplayAllCategoryTransactions() {
		return null;
	}
	
	public String DisplayComparison() {
		return null;
	}
	
	//When adding or removing a category the money will be reallocated 
	//through a standard "other" category that is not assigned to users budget, but is just unallocted money.
	public void AddNewCategory() {
		//Adds a new category to the budget
		//Probably want to display some type of message of completion
		//or show new adjusted budget
	}
	
	public void removeCategory() {
		
	}
}
