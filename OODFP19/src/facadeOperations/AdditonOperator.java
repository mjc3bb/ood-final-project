package facadeOperations;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;

import models.Transaction;

//TODO This class will not be used for application,
//BUT it is being used for REFERENCE for building the Select and Update QueryOperators.
public class AdditonOperator {

	private JdbcConnectionSource conn = null;
	private Dao<Transaction,String> transactionDao = null;
	public AdditonOperator(JdbcConnectionSource con) throws SQLException{
		this.conn = con;
		transactionDao = DaoManager.createDao(conn, Transaction.class);
	}

	public void addSingleExpense(Transaction transaction) throws SQLException {
//		QueryBuilder<Transaction, String> queryBuilder = transactionDao.queryBuilder();
//		queryBuilder.
		if(transaction!=null) {
		if(transaction.getTransaction()<0)
		transactionDao.update(transaction);
		else
			System.out.println("Transaction attempting to be added is not an expense.");
		} else {
			System.out.println("This transaction is invalid.");
		}
	}
	
	public void addSingleIncome(Transaction transaction) throws SQLException {
		if(transaction!=null) {
			if(transaction.getTransaction()>0)
			transactionDao.update(transaction);
			else
				System.out.println("Transaction attempting to be added is not an expense.");
			} else {
				System.out.println("This transaction is invalid.");
			}
	}
	public String addAllExpenses() throws SQLException {
		//QueryBuilder<Transaction , String> queryBuilder = (QueryBuilder<Transaction, String>) transactionDao.queryBuilder();
		GenericRawResults<String[]> rawResults = transactionDao.queryRaw(
				"select sum(t.transaction) as 'Total of Transactions' "
						+ "from transaction t "
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
