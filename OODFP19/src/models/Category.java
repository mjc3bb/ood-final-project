package models;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="category")
public class Category {

	@DatabaseField(generatedId=true)
	private long categoryID;
	
	@DatabaseField(dataType=DataType.STRING_BYTES)
	private String categoryName;
	
	@DatabaseField(dataType=DataType.DATE)
	private Date categoryStartDate;
	
	@DatabaseField(foreign = true)
	private Budget budget;
	
	Category(){
	}
}
