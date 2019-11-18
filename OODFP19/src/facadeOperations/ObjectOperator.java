package facadeOperations;

import java.sql.SQLException;
import java.util.Date;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import models.Account;
import models.Transaction;

//TODO Meant to make it easier to use data with the GUI. Hopefully this is what is needed.
public class ObjectOperator {
	private JdbcConnectionSource conn = null;
	private Dao<Transaction,String> transactionDao = null;
	private Dao<Account,String> accountDao = null;
	
	//Attributes of Transaction Object
	private long entryID;
	private int transaction;
	//The Application reads it as YYYY-MM-DD
	private Date transactionDate;
	//Location of the transaction as a String
	private String location;
	//References the account the transaction is attached to
	private String accountName;
	private String category; 
	//Boolean that determines whether a transaction is recurring or not
	private boolean recurring;	
	//Shows whether the transaction is negative or not
	private boolean negative;

	//Attributes of Account Object
	private long accountID;
	private Date accountStart;
	private int currentBalance;
	
	public ObjectOperator(JdbcConnectionSource con) throws SQLException{
		this.conn = con;
		transactionDao = DaoManager.createDao(conn, Transaction.class);
		accountDao = DaoManager.createDao(conn, Account.class);
	}
	
	public ObjectOperator(Transaction t) {
		this.entryID = t.getEntryID();
		this.transaction = t.getTransaction();
		this.transactionDate = t.getTransactionDate();
		this.location = t.getLocation();
		this.accountName = t.getAccount().getAccountName();
		this.category = t.getCategory();
		this.recurring = t.getRecurring();
		this.negative = t.isNegative();
	}
	
	public ObjectOperator(Account a) {
		this.accountName = a.getAccountName();
		this.accountID = a.getAccountID();
		this.accountStart = a.getAccountStart();
		this.currentBalance = a.getCurrentBalance();
	}

	public JdbcConnectionSource getConn() {
		return conn;
	}

	public void setConn(JdbcConnectionSource conn) {
		this.conn = conn;
	}

	public Dao<Transaction, String> getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(Dao<Transaction, String> transactionDao) {
		this.transactionDao = transactionDao;
	}

	public Dao<Account, String> getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(Dao<Account, String> accountDao) {
		this.accountDao = accountDao;
	}

	public long getEntryID() {
		return entryID;
	}

	public void setEntryID(long entryID) {
		this.entryID = entryID;
	}

	public int getTransaction() {
		return transaction;
	}

	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public boolean isNegative() {
		return negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}

	public long getAccountID() {
		return accountID;
	}

	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}

	public Date getAccountStart() {
		return accountStart;
	}

	public void setAccountStart(Date accountStart) {
		this.accountStart = accountStart;
	}

	public int getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	public ObjectOperator getObject() {
		return this;
	}
}
