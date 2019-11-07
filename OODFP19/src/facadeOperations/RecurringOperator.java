package facadeOperations;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import models.Transaction;

public class RecurringOperator {

	private JdbcConnectionSource conn = null;
	private Dao<Transaction,String> transactionDao = null;
	RecurringOperator(JdbcConnectionSource con){
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
