package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

//TODO: Is this a table that holds all transactions or is each amount independnet
@DatabaseTable(tableName="expense")
public class Expense {

	@DatabaseField(generatedId=true)
	private long entryID;

	@DatabaseField(dataType=DataType.DOUBLE)
	private double amount;

	//The Application reads it as YYYY-MM-DD
	@DatabaseField(dataType=DataType.DATE)
	private Date transactionDate;

	//Location of the amount as a String
	@DatabaseField(dataType=DataType.STRING)
	private String location;
	
	//References the account the amount is attached to
	@DatabaseField(foreign = true)
	private Account account;
	
	//References the account the amount is attached to
	@DatabaseField(dataType=DataType.STRING)
	private String accountName;

	@DatabaseField(dataType=DataType.STRING)
	private String category; 

	//Boolean that determines whether a amount is recurring or not
	@DatabaseField(dataType=DataType.BOOLEAN)
	private boolean recurring;	

	//Shows whether the amount is negative or not
	@DatabaseField(dataType=DataType.BOOLEAN)
	private boolean negative;

	public Expense() {
	}

	public Expense(double transaction, Date transactionDate, String location, Account account, String category,
			boolean recurring) {
		this.amount = transaction;
		this.transactionDate = transactionDate;
		this.location = location;
		this.account = account;
		this.category = category;
		this.recurring = recurring;
		this.accountName = account.getAccountName();
	}

	public long getEntryID() {
		return entryID;
	}

	public void setEntryID(long entryID) {
		this.entryID = entryID;
	}

	public double getTransaction() {
		return amount;
	}

	public void setTransaction(double transaction) {
		this.amount = transaction;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getAccountName() {
		return this.accountName;
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
		obj.add(amount);
		obj.add(account);
		obj.add(category);
		obj.add(recurring);
		obj.add(transactionDate);
		obj.add(location);
		return obj;
	}
}
