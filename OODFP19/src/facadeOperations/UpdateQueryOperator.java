package facadeOperations;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import models.Account;
import models.Transaction;

public class UpdateQueryOperator {
	private JdbcConnectionSource conn = null;
	private Dao<Transaction,String> transactionDao = null;
	private Dao<Account,String> accountDao = null;

	public UpdateQueryOperator(JdbcConnectionSource con) throws SQLException{
		this.conn = con;
		transactionDao = DaoManager.createDao(conn, Transaction.class);
		accountDao = DaoManager.createDao(conn, Account.class);
	}

	public void addTransaction(int amount, String date, String location, String accountName, String category,
			boolean recurring) throws SQLException, ParseException {
		//TODO Query the account name and return it to add to the transaction object.
		Date d = new SimpleDateFormat("YYYY/MM/DD").parse(date);
		List<Account> acc = accountDao.queryBuilder().where().eq("accountName", accountName).query();
		Transaction t = new Transaction(amount, d, location, acc.get(0), category, recurring);
		transactionDao.update(t);
	}

	public void addAccount(String AccountName, String accountType, double accountBalance) throws SQLException {
		Account a = new Account(AccountName, accountType, accountBalance);
		accountDao.createOrUpdate(a);
	}

	public void removeAccount(Account account) throws SQLException{
		accountDao.delete(account);
	}

	public void updateBalance(String accountName) throws SQLException {
		// TODO Update the value for currentBalance of the given account with the sum of transactions for that account
		long sum = transactionDao.queryRawValue("select sum(t.transaction) from transaction t, account a where a.accountName=\"" + accountName +"\"");
		UpdateBuilder<Account, String> updateBuilder = accountDao.updateBuilder();
		updateBuilder.updateColumnValue("currentBalance", sum).where().eq("accountName", accountName);
		updateBuilder.update();
	}

	public void updateBalanceWithStartingBalance(String accountName, double accountBalance) throws SQLException {
		long sum = (long)accountBalance + transactionDao.queryRawValue("select sum(t.transaction) from transaction t, account a where a.accountName=\"" + accountName +"\"");
		UpdateBuilder<Account, String> updateBuilder = accountDao.updateBuilder();
		updateBuilder.updateColumnValue("currentBalance", sum).where().eq("accountName", accountName);
		updateBuilder.update();
	}
	public void addIncome(String accountName, double amount) throws SQLException {
		// TODO Should do the same as addTransaction, but will have null value for location and have some defaults for other attributes
		long sum = transactionDao.queryRawValue("select sum(t.transaction) from transaction t, account a where a.accountName=\"" + accountName +"\" and t.negative IS FALSE");
		UpdateBuilder<Account, String> updateBuilder = accountDao.updateBuilder();
		updateBuilder.updateColumnValue("currentBalance", sum).where().eq("accountName", accountName);
		updateBuilder.update();
	}
	
	
}
