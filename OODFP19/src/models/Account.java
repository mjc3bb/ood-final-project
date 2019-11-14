package models;

import java.util.ArrayList;
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
	
	public Budget() {
	}

	public Budget(String budgetName, Date budgetStart) {
		this.budgetName = budgetName;
		this.budgetStart = budgetStart;
	}
	
	public long getBudgetID() {
		return budgetID;
	}

	public void setBudgetID(long budgetID) {
		this.budgetID = budgetID;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public Date getBudgetStart() {
		return budgetStart;
	}

	public void setBudgetStart(Date budgetStart) {
		this.budgetStart = budgetStart;
	}
	
	public ArrayList<Object> getAllAttributes() {
		ArrayList<Object> obj = new ArrayList<Object>();
		obj.add(budgetName);
		obj.add(budgetStart);
		obj.add(budgetID);
		return obj;
	}
	
	//TODO Can we fit the ID into this?
	public void setAllAttributes(String budgetName, Date budgetStart) {
		this.budgetName = budgetName;
		this.budgetStart = budgetStart;
	}
}