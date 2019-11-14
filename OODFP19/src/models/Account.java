package models;

import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//TODO For now will not actually function as account, but rather just track spending in different categories.
@DatabaseTable(tableName="account")
public class Account {
	
	@DatabaseField(generatedId=true)
	private long accountID;
	
	@DatabaseField(dataType=DataType.STRING_BYTES)
	private String accountName;
	
	@DatabaseField(dataType=DataType.DATE)
	private Date accountStart;
	
	public Account() {
	}

	public Account(String accountName, Date accountStart) {
		this.accountName = accountName;
		this.accountStart = accountStart;
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

	public Date getAccountStart() {
		return accountStart;
	}

	public void setAccountStart(Date accountStart) {
		this.accountStart = accountStart;
	}
	
	public ArrayList<Object> getAllAttributes() {
		ArrayList<Object> obj = new ArrayList<Object>();
		obj.add(accountName);
		obj.add(accountStart);
		obj.add(accountID);
		return obj;
	}
	
	//TODO Can we fit the ID into this?
	public void setAllAttributes(String accountName, Date accountStart) {
		this.accountName = accountName;
		this.accountStart = accountStart;
	}
}