package models;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="recurring")
public class Recurring {

	@DatabaseField(generatedId=true)
	private long recurringID;
	
	@DatabaseField(dataType=DataType.STRING_BYTES)
	private String recurringName;
	
	
	@DatabaseField(foreign=true)
	private Category categoryID;
	
//	@DatabaseField(dataType=DataType.LONG)
//	private long categoryID;
	
	//The day first recurring transaction occurs
	@DatabaseField(dataType=DataType.DATE)
	private Date recurringStartDate;
	
	//Time until next transaction
	//There may be a number of months or weeks instead.
	@DatabaseField(dataType=DataType.INTEGER)
	private int daysTillRepeat;
	
	public Recurring() {
	}

	public Recurring(String recurringName, Category categoryID, Date recurringStartDate,
			int daysTillRepeat) {
		this.recurringName = recurringName;
		this.categoryID = categoryID;
		this.recurringStartDate = recurringStartDate;
		this.daysTillRepeat = daysTillRepeat;
	}

	public long getRecurringID() {
		return recurringID;
	}

	public void setRecurringID(long recurringID) {
		this.recurringID = recurringID;
	}

	public String getRecurringName() {
		return recurringName;
	}

	public void setRecurringName(String recurringName) {
		this.recurringName = recurringName;
	}

	public Category getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Category categoryID) {
		this.categoryID = categoryID;
	}

	public Date getRecurringStartDate() {
		return recurringStartDate;
	}

	public void setRecurringStartDate(Date recurringStartDate) {
		this.recurringStartDate = recurringStartDate;
	}

	public int getDaysTillRepeat() {
		return daysTillRepeat;
	}

	public void setDaysTillRepeat(int daysTillRepeat) {
		this.daysTillRepeat = daysTillRepeat;
	}
	
	public ArrayList<Object> getAllAttributes(){
		ArrayList<Object> obj = new ArrayList<Object>();
		obj.add(recurringID);
		obj.add(recurringName);
		obj.add(categoryID);
		obj.add(recurringStartDate);
		obj.add(daysTillRepeat);
		return obj;
	}
	
	//TODO How do we include the recurringID here?
	public void setAllAttributes(String recurringName, JdbcConnectionSource connection, Date recurringStartDate,
			int daysTillRepeat) throws SQLException {
		this.recurringName = recurringName;
		this.categoryID = (Category) connection.getReadWriteConnection("category");
		this.recurringStartDate = recurringStartDate;
		this.daysTillRepeat = daysTillRepeat;
	}
	
}