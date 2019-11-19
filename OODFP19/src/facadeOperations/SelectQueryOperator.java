package facadeOperations;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;

import models.Account;
import models.Transaction;

public class SelectQueryOperator {
	private JdbcConnectionSource conn = null;
	private Dao<Transaction,String> transactionDao = null;
	private Dao<Account,String> accountDao = null;
	
	public SelectQueryOperator(JdbcConnectionSource con) throws SQLException{
		this.conn = con;
		transactionDao = DaoManager.createDao(conn, Transaction.class);
		accountDao = DaoManager.createDao(conn, Account.class);
	}
	
	public int returnCurrentBalance(String accountName) throws SQLException {
		//TODO Return a query to get the number value for currentBalance attribute for given accountName
		List<Account> acc = accountDao.queryBuilder().where().eq("accountName", accountName).query();
		return acc.get(0).getCurrentBalance();
	}
	
	
	//Returns an array list of transaction array list that hold the attributes of that transaction as object 
	//that can be used for tables in application
	//YYYY-MM-DD should be the pattern for start and end Date
	public List<Transaction> returnTransationsByDate(String startDate, String endDate) throws SQLException, ParseException {
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(startDate);
		Date d2 = new SimpleDateFormat("YYYY/MM/DD").parse(endDate);
		
		List<Transaction> transactionsList = transactionDao.queryBuilder().where().between("transactionDate", d1, d2).query();
		
		return transactionsList;
	}
	
	//Return income objects by date range
	public List<Transaction> returnIncomesByDate(String startDate, String endDate) throws ParseException, SQLException{
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(startDate);
		Date d2 = new SimpleDateFormat("YYYY/MM/DD").parse(endDate);
		
		List<Transaction> transactionsList = transactionDao.queryBuilder().where().between("transactionDate", d1, d2).and().gt("transaction", 0).query();
		
		return transactionsList;
	}

	public int incomeByMonth(String MM, String accountName) throws ParseException, SQLException {
		// TODO Return a query of total income for one month
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(MM);
		
		long sum = transactionDao.queryRawValue("select sum(t.transaction) from transaction t, account a where a.accountName=\"" + accountName +"\" and exists(t.transaction>0)");
		
		
		return (int) sum;
	}

	public int expenseByMonth(String MM) throws SQLException, ParseException {
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(MM);
		return (int)transactionDao.queryRawValue("select sum(t.transaction) from transaction t where t.transaction<0 and STRFTIME('%m', t.`t.transactionDate`) like \"" + MM + "\"");
	}

	public int expenseByCategoryByMonth(String MM, String categoryName) throws ParseException, SQLException {
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(MM);
		return (int)transactionDao.queryRawValue("select sum(t.transaction) from transaction t "
				+ "where t.transaction<0 and STRFTIME('%m', t.`t.transactionDate`) like \"" + MM + "\" "
						+ "and category=\""+categoryName+"\"");
	}

	public ArrayList<Object> returnExpenseObjectsByMonth(String mM, String accountName) throws SQLException, ParseException {
		// TODO Queries for expenses within a month and places them as objects in an array list
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(mM);
		List<Transaction> transactions = transactionDao.queryBuilder().where().lt("transaction", 0).query();
		transactions.removeIf(t -> t.getTransactionDate().getMonth()!=d1.getMonth());
		ArrayList<Object> objs = new ArrayList<Object>(transactions);
		return objs;
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
