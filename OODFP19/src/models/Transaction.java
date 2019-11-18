package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

//TODO: Is this a table that holds all transactions or is each transaction independnet
@DatabaseTable(tableName="transaction")
public class Transaction {

	@DatabaseField(generatedId=true)
	private long entryID;

	@DatabaseField(dataType=DataType.INTEGER)
	private int transaction;

	//The Application reads it as YYYY-MM-DD
	@DatabaseField(dataType=DataType.DATE)
	private Date transactionDate;

	//Location of the transaction as a String
	@DatabaseField(dataType=DataType.STRING_BYTES)
	private String location;
	
	//References the account the transaction is attached to
	@DatabaseField(foreign = true)
	private Account account;

	@DatabaseField(dataType=DataType.STRING_BYTES)
	private String category; 

	//Boolean that determines whether a transaction is recurring or not
	@DatabaseField(dataType=DataType.BOOLEAN)
	private boolean recurring;	

	//Shows whether the transaction is negative or not
	@DatabaseField(dataType=DataType.BOOLEAN)
	private boolean negative;

	public Transaction() {
	}

	public Transaction(int transaction, Date transactionDate, String location, Account account, String category,
			boolean recurring) {
		this.transaction = transaction;
		this.transactionDate = transactionDate;
		this.location = location;
		this.account = account;
		this.category = category;
		this.recurring = recurring;
	}
	//TODO Have no idea if this actually works. Will passing connection give access to that database, or should an object of each thing be sent through the main?
//	public Transaction(int transaction, Date transactionDate, JdbcConnectionSource connection) throws SQLException {
//		this.transaction = transaction;
//		this.transactionDate = transactionDate;
//		this.account = (Account) connection.getReadOnlyConnection("budget");
//		this.category = connection.getReadOnlyConnection("category");
//		this.recurring = connection("recurring");
//	}

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

	public Account getBudget() {
		return account;
	}

	public void setBudget(Account account) {
		this.account = account;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean getRecurring() {
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

	public ArrayList<Object> getAllAttributes(){
		ArrayList<Object> obj = new ArrayList<Object>();
		obj.add(entryID);
		obj.add(transaction);
		obj.add(account);
		obj.add(category);
		obj.add(recurring);
		obj.add(transactionDate);
		obj.add(location);
		return obj;
	}

//	public void setAllAttributes(int transaction, Date transactionDate, JdbcConnectionSource connection) throws SQLException {
//		this.transaction = transaction;
//		this.transactionDate = transactionDate;
//		this.account = (Account) connection.getReadWriteConnection("budget");
//		this.category = (Category) connection.getReadWriteConnection("category");
//		this.recurring = (Recurring) connection.getReadWriteConnection("recurring");
//	}
}
