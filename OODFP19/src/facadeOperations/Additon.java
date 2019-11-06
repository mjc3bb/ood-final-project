package facadeOperations;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;

import models.Transaction;

public class Additon {

	private JdbcConnectionSource conn = null;
	private Dao<Transaction,String> transactionDao = null;
	Additon(JdbcConnectionSource con) throws SQLException{
		this.conn = con;
		transactionDao = DaoManager.createDao(conn, Transaction.class);
	}
	
	public String addAllExpenses() throws SQLException {
		//QueryBuilder<Transaction , String> queryBuilder = (QueryBuilder<Transaction, String>) transactionDao.queryBuilder();
		GenericRawResults<String[]> rawResults = transactionDao.queryRaw("select sum(t.transaction) as 'Total of Transactions' from transaction t");
		List<String[]> results = rawResults.getResults();
		String[] resultArray = results.get(0);
		//int sum = Integer.parseInt(resultArray[0]);
		return resultArray[0];
	}
	
	public int addAllIncomes() {
		
		return -1;
	}
	
	public int addAllIncomesByDate() {
		
		return -1;
	}

	public int addAllExpensesByDate() {
	
		return -1;
	}
}
