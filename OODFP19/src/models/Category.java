package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

//TODO Get rid of and make it an attribute in the Transaction Table
@DatabaseTable(tableName="category")
public class Category {

	@DatabaseField(generatedId=true)
	private long categoryID;
	
	@DatabaseField(dataType=DataType.STRING_BYTES)
	private String categoryName;
	
	@DatabaseField(dataType=DataType.DATE)
	private Date categoryStartDate;
	
	@DatabaseField(foreign = true)
	private Account account;
	
	public Category(){
	}

	public Category(String categoryName, Date categoryStartDate, Account account) {
		this.categoryName = categoryName;
		this.categoryStartDate = categoryStartDate;
		this.account = account;
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

	public Account getBudget() {
		return account;
	}

	public void setBudget(Account account) {
		this.account = account;
	}
	
	public ArrayList<Object> getAllAttributes(){
		ArrayList<Object> obj = new ArrayList<Object>();
		obj.add(account);
		obj.add(categoryName);
		obj.add(categoryStartDate);
		obj.add(categoryID);
		return obj;
	}
	
	//TODO How do we fit the categoryID in to this?
	public void setAllAttributes(String categoryName, Date categoryStartDate, JdbcConnectionSource connection) throws SQLException {
		this.categoryName = categoryName;
		this.categoryStartDate = categoryStartDate;
		this.account = (Account) connection.getReadWriteConnection("budget");
	}
}