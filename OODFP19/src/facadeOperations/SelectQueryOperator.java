package facadeOperations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;

import models.Account;
import models.Transaction;

public class SelectQueryOperator {
	private JdbcConnectionSource conn = null;
	private Dao<Transaction,String> transactionDao = null;
	Dao<Account,String> accountDao = null;
	
	public SelectQueryOperator(JdbcConnectionSource con) throws SQLException{
		this.conn = con;
		transactionDao = DaoManager.createDao(conn, Transaction.class);
		accountDao = DaoManager.createDao(conn, Account.class);
	}
	
	public int returnCurrentBalance(String accountName) {
		//TODO Return a query to get the number value for currentBalance attribute for given accountName
		return -1;
	}
	
	
	//Returns an array list of transaction array list that hold the attributes of that transaction as object 
	//that can be used for tables in application
	//YYYY-MM-DD should be the pattern for start and end Date
	public ArrayList<Object> returnTransationsByDate(String startDate, String endDate) throws SQLException {
		QueryBuilder<Transaction, String> queryBuilder = transactionDao.queryBuilder();
		//queryBuilder.where()//Date
		queryBuilder.selectRaw(
				"select entryID "
				+ "from transaction "
				+ "where exists(transactionDate >= " + startDate + ") and exists(transactionDate <= " + endDate + ")");
//		boolean good = true;
//		while(good) {
//			queryBuilder.query();
//		}
		
		return null;
	}
	
	public ArrayList<Object> returnIncomesByDate(String startDate, String endDate){
		
		return null;
	}

	public int incomeByMonth(String MM) {
		// TODO Return a query of total income for one month
		return 0;
	}

	public int expenseByMonth(String MM) {
		// TODO Return a query of total expense for a month
		return 0;
	}

	public int expenseByCategoryByMonth(String MM, String categoryName) {
		// TODO Return a query of total expenses in a category in a certain month
		return 0;
	}

	public ArrayList<Object> returnExpenseObjectsByMonth(String mM) {
		// TODO Queries for expenses within a month and places them as objects in an array list
		return null;
	}

	public ArrayList<Object> getAccountObjects() {
		// TODO Queries for accounts in database and places them as objects in an array list
		return null;
	}

	public ArrayList<Object> returnExpenseObjectsByAccount(String accountName) {
		// TODO Queries for expenses from certain account and places them as objects in array list
		return null;
	}
}
