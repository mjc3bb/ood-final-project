package models;

import java.util.Date;

import com.j256.ormlite.jdbc.*;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="transaction")
public class PossibleDatabase {
	String transactionTableQuery =
			"CREATE TABLE transaction("
			+ "entryID			INT		NOT NULL,"
			+ "transaction		INT		NOT NULL,"
			+ "transactionDAte	DATE	NOT NULL,"
			+ "budgetID			INT		NOT NULL,"
			+ "categoryID		INT		NOT NULL,"
			+ "recurringID		INT,"
			+ "PRIMARY KEY(entryID)";
	
	@DatabaseField(generatedId=true)
	private long entryID;
	
	@DatabaseField(dataType=DataType.INTEGER)
	private int transaction;
	
	@DatabaseField(dataType=DataType.DATE)
	private Date transactionDate;
	
	//How do I make these into foreign keys?
	@DatabaseField(dataType=DataType.LONG, foreign=true)
	private long budgetID;
	@DatabaseField(dataType=DataType.LONG, foreign=true)
	private long categoryID;
	@DatabaseField(dataType=DataType.LONG, canBeNull=true, foreign=true)
	private long recurringID;
	public PossibleDatabase() {
	}
}
