package models;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="recurring")
public class Recurring {

	@DatabaseField(generatedId=true)
	private long recurringID;
	
	@DatabaseField(dataType=DataType.STRING_BYTES)
	private String recurringName;
	
	@DatabaseField(dataType=DataType.LONG)
	private long categoryID;
	
	//The day first recurring transaction occurs
	@DatabaseField(dataType=DataType.DATE)
	private Date recurringStartDate;
	
	//Time until next transaction
	//There may be a number of months or weeks instead.
	@DatabaseField(dataType=DataType.INTEGER)
	private int daysTillRepeat;
	
	public Recurring() {
	}

	public Recurring(String recurringName, long categoryID, Date recurringStartDate,
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

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
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
	
}
