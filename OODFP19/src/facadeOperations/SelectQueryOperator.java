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
	//YYYY/MM/DD should be the pattern for start and end Date
	public ArrayList<Transaction> returnTransationsByDate(String startDate, String endDate) throws SQLException, ParseException {
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(startDate);
		Date d2 = new SimpleDateFormat("YYYY/MM/DD").parse(endDate);
		List<Transaction> transactions = transactionDao.queryBuilder().where().between("transactionDate", d1, d2).query();

		return new ArrayList<Transaction>(transactions);
	}

	//Return income objects by date range
	public ArrayList<Object> returnIncomesByDate(String startDate, String endDate) throws ParseException, SQLException{
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(startDate);
		Date d2 = new SimpleDateFormat("YYYY/MM/DD").parse(endDate);
		List<Transaction> transactionsList = transactionDao.queryBuilder().where().between("transactionDate", d1, d2).and().gt("transaction", 0).query();

		return new ArrayList<Object>(transactionsList);
	}

	public int incomeByMonth(String MM, String accountName) throws ParseException, SQLException {
		// TODO Return a query of total income for one month
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(MM);
		long sum = transactionDao.queryRawValue("select sum(t.transaction) from transaction t, account a where a.accountName=\"" + accountName +"\" and t.transaction IS FALSE");
		
		return (int) sum;
	}

	public int expenseByMonth(String MM, String accountName) throws ParseException, SQLException {
		// TODO Return a query of total expense for a month
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(MM);
		long sum = transactionDao.queryRawValue("select sum(t.transaction) from transaction t, account a where a.accountName=\"" + accountName +"\" and t.negative IS TRUE");
		
		return (int) sum;
	}

	public int expenseByCategoryByMonth(String MM, String categoryName, String accountName) throws ParseException, SQLException {
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(MM);
		//TODO Check for correct SQL syntax for checking booleans (t.negative=true or t.negative IS TRUE)
		return (int)transactionDao.queryRawValue("select sum(t.transaction) from transaction t "
				+ "where t.negative IS TRUE and STRFTIME('%m', t.`t.transactionDate`) like \"" + MM + "\" "
						+ "and t.category=\""+categoryName+"\" and t.account.accountName=\""+accountName+"\"");
	}

	public ArrayList<Transaction> returnExpenseObjectsByMonth(String MM, String accountName) throws SQLException, ParseException {
		// TODO Queries for expenses within a month and places them as objects in an array list
		Date d1 = new SimpleDateFormat("YYYY/MM/DD").parse(MM);
		List<Transaction> transactions = transactionDao.queryBuilder().where().eq("negative", true).query();
		transactions.removeIf(t -> t.getTransactionDate().getMonth()!=d1.getMonth());
		return new ArrayList<Transaction>(transactions);
	}

	public ArrayList<Account> getAccountObjects() throws SQLException {
		// TODO Queries for accounts in database and places them as objects in an array list
		List<Account> accounts = accountDao.queryForAll();
		return new ArrayList<Account>(accounts);
	}

	public ArrayList<Transaction> returnExpenseObjectsByAccount(String accountName) throws SQLException {
		// TODO Queries for expenses from certain account and places them as objects in array list
		List<Transaction> transactions = transactionDao.queryBuilder().where().eq("negative", true).and().eq("accountName", accountName).query();
		return new ArrayList<Transaction>(transactions);
	}
}
