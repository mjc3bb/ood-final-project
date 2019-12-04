package observableObjects;

import models.Expense;

import java.text.SimpleDateFormat;

import javafx.beans.property.*;

public class ObservableTransaction {
	
	private SimpleDoubleProperty amount;
	private SimpleStringProperty date;
	private SimpleStringProperty location;
	private SimpleStringProperty account;
	private SimpleStringProperty category;
	private SimpleStringProperty recurring;
	SimpleDateFormat df = new SimpleDateFormat("MM/dd/YY");
	
	public ObservableTransaction(Expense t) {
		this.amount = new SimpleDoubleProperty(t.getTransaction());
		this.date = new SimpleStringProperty(df.format(t.getTransactionDate()));
		this.location = new SimpleStringProperty(t.getLocation());
		this.account = new SimpleStringProperty(t.getAccountName());
		this.category = new SimpleStringProperty(t.getCategory());
		this.recurring = new SimpleStringProperty(booleanToString(t.getRecurring()));
	}

	public double getAmount() {
		return amount.get();
	}

	public String getDate() {
		return date.get();
	}

	public String getLocation() {
		return location.get();
	}

	public String getAccount() {
		return account.get();
	}

	public String getCategory() {
		return category.get();
	}

	public String getRecurring() {
		return recurring.get();
	}

	private String booleanToString(boolean value) {
		if (value) return "Yes";
		else return "No";
	}

}
