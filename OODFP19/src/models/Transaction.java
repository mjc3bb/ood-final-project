package models;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
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
}
