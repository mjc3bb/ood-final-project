package facadeOperations;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class Recurring {

	private JdbcConnectionSource conn = null;
	
	Recurring(JdbcConnectionSource con){
		this.conn = con;
	}
	
	public String DisplayRecurringExpenses() {
		return null;
	}
	
	public String DisplayRecurringIncome() {
		return null;
	}
	
	public String DisplayByCategory() {
		return null;
	}
	
	public String DisplayRecurringExpensesByCategory() {
		return null;
	}
	
	public String DisplayRecurringIncomesByCategory() {
		return null;
	}
}
