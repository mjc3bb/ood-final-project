package models;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="budget")
public class Budget {
	
	@DatabaseField(generatedId=true)
	private long budgetID;
	
	@DatabaseField(dataType=DataType.STRING_BYTES)
	private String budgetName;
	
	@DatabaseField(dataType=DataType.DATE)
	private Date budgetStart;
}
