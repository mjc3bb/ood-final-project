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
}
