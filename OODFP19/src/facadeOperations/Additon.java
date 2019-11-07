package facadeOperations;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
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
		GenericRawResults<String[]> rawResults = transactionDao.queryRaw(
				"select sum(t.transaction) as 'Total of Transactions' "
				+ "from transaction t"
				+ "where exists(t.transaction<0)");
		List<String[]> results = rawResults.getResults();
		String[] resultArray = results.get(0);
		//int sum = Integer.parseInt(resultArray[0]);
		return resultArray[0];
	}
	
	public String addAllIncomes() throws SQLException {
		GenericRawResults<String[]> rawResults = transactionDao.queryRaw(
				"select sum(t.transaction) as 'Total of Transactions' "
				+ "from transaction t "
				+ "where exists(t.transaction>0)");
		List<String[]> results = rawResults.getResults();
		String[] resultArray = results.get(0);
		return resultArray[0];
	}
	
	public String addAllIncomesByDate(Calendar date) throws SQLException {
		//How do we make sure that the date is read in the correct format in the query?
		String yyyy_mm_dd = "'"+String.valueOf(date.YEAR)+"-"+String.valueOf(date.MONTH)+"-"+String.valueOf(date.DATE)+"'";
		GenericRawResults<String[]> rawResults = transactionDao.queryRaw(
				"select sum(t.transaction) as 'Total of Transactions' "
				+ "from transaction t "
				+ "where exists(t.transaction>0) and t.transactionDate=" + yyyy_mm_dd);
		List<String[]> results = rawResults.getResults();
		String[] resultArray = results.get(0);
		return resultArray[0];
	}

	public String addAllExpensesByDate(Calendar date) throws SQLException {
		//How do we make sure that the date is read in the correct format in the query?
		String yyyy_mm_dd = String.valueOf(date.YEAR)+"-"+String.valueOf(date.MONTH)+"-"+String.valueOf(date.DATE);
				GenericRawResults<String[]> rawResults = transactionDao.queryRaw(
						"select sum(t.transaction) as 'Total of Transactions' "
						+ "from transaction t "
						+ "where exists(t.transaction<0) and t.transactionDate=" + yyyy_mm_dd);
				List<String[]> results = rawResults.getResults();
				String[] resultArray = results.get(0);
				return resultArray[0];
	}
}
