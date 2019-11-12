package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="transaction")
public class Transaction {
	
	@DatabaseField(generatedId=true)
	private long entryID;
	
	@DatabaseField(dataType=DataType.INTEGER)
	private int transaction;
	
	@DatabaseField(dataType=DataType.DATE)
	private Date transactionDate;
		
	@DatabaseField(foreign = true)
	private Budget budget;
	
	@DatabaseField(foreign = true)
	private Category category;
	
	@DatabaseField(foreign = true, canBeNull=true)
	private Recurring recurring;	
	
	public Transaction() {
	}

	public Transaction(int transaction, Date transactionDate, Budget budget, Category category,
			Recurring recurring) {
		this.transaction = transaction;
		this.transactionDate = transactionDate;
		this.budget = budget;
		this.category = category;
		this.recurring = recurring;
	}
	//TODO Have no idea if this actually works. Will passing connection give access to that database, or should an object of each thing be sent through the main?
	public Transaction(int transaction, Date transactionDate, JdbcConnectionSource connection) throws SQLException {
		this.transaction = transaction;
		this.transactionDate = transactionDate;
		this.budget = (Budget) connection.getReadOnlyConnection("budget");
		this.category = (Category) connection.getReadOnlyConnection("category");
		this.recurring = (Recurring) connection.getReadOnlyConnection("recurring");
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

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Recurring getRecurring() {
		return recurring;
	}

	public void setRecurring(Recurring recurring) {
		this.recurring = recurring;
	}
	
	public ArrayList<Object> getAllAttributes(){
		ArrayList<Object> obj = new ArrayList<Object>();
		obj.add(entryID);
		obj.add(transaction);
		obj.add(budget);
		obj.add(category);
		obj.add(recurring);
		obj.add(transactionDate);
		return obj;
	}
	
	public void setAllAttributes(int transaction, Date transactionDate, JdbcConnectionSource connection) throws SQLException {
		this.transaction = transaction;
		this.transactionDate = transactionDate;
		this.budget = (Budget) connection.getReadWriteConnection("budget");
		this.category = (Category) connection.getReadWriteConnection("category");
		this.recurring = (Recurring) connection.getReadWriteConnection("recurring");
	}
}
