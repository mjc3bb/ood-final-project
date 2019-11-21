package observableObjects;

import models.Account;
import javafx.beans.property.*;

public class ObservableAccount {
	
	private SimpleStringProperty name;
	private SimpleStringProperty type;
	private SimpleDoubleProperty balance;
	
	public ObservableAccount(Account account) {
		this.setName(new SimpleStringProperty(account.getAccountName()));
		this.setType(new SimpleStringProperty(account.getAccountType()));
		this.setBalance(new SimpleDoubleProperty(account.getCurrentBalance()));
	}

	public String getName() {
		return name.get();
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public String getType() {
		return type.get();
	}

	public void setType(SimpleStringProperty type) {
		this.type = type;
	}

	public Double getBalance() {
		return balance.get();
	}

	public void setBalance(SimpleDoubleProperty balance) {
		this.balance = balance;
	}

}
