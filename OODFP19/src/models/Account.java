package models;

import java.util.ArrayList;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="account")
public class Account {
	
	@DatabaseField(generatedId=true)
	private long accountID;
	
	@DatabaseField(dataType=DataType.STRING)
	private String accountName;
	
	@DatabaseField(dataType=DataType.DOUBLE)
	private double currentBalance;
	
	@DatabaseField(dataType=DataType.STRING)
	private String accountType;
	
	public Account() {
	}

	public Account(String accountName, String accountType, double balance) {
		this.accountName = accountName;
		this.accountType = accountType;
		this.currentBalance = balance;
	}
	
	public long getAccountID() {
		return accountID;
	}

	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public ArrayList<Object> getAllAttributes() {
		ArrayList<Object> obj = new ArrayList<Object>();
		obj.add(accountName);
		obj.add(accountType);
		obj.add(accountID);
		return obj;
	}
}