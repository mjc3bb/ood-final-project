package models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="checkbook")
public class Checkbook {
	@DatabaseField(generatedId=true)
	private long entryID;
	
	@DatabaseField(dataType = DataType.INTEGER)
	private int delta;
	
	public Checkbook() {
	}	
}