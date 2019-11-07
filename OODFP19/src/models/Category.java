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
	
	public Category(){
	}

	public Category(String categoryName, Date categoryStartDate, Budget budget) {
		this.categoryName = categoryName;
		this.categoryStartDate = categoryStartDate;
		this.budget = budget;
	}

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getCategoryStartDate() {
		return categoryStartDate;
	}

	public void setCategoryStartDate(Date categoryStartDate) {
		this.categoryStartDate = categoryStartDate;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	
}
