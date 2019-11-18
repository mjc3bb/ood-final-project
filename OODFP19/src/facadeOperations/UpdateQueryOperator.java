package facadeOperations;

import java.sql.SQLException;
import java.util.Date;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

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
			boolean recurring) {
		//TODO Query the account name and return it to add to the transaction object.
		//Transaction t = new Transaction(amount, dateOfTransaction, location, account, category, recurring);
		//transactionDao.update(t);
	}

	public void addAccount(String AccountName, Date AccountStartDate) throws SQLException {
		Account a = new Account(AccountName, AccountStartDate);
		accountDao.update(a);
	}

	public void removeAccount(Account account) throws SQLException{
		accountDao.delete(account);
	}

	public void updateBalance(String accountName) {
		// TODO Update the value for currentBalance of the given account with the sum of transactions for that account
		
	}

	public void addIncome(String accountName, int amount) {
		// TODO Should do the same as addTransaction, but will have null value for location and have some defaults for other attributes
		
	}
	
	
}
