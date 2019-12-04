package gui.overview;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import com.jfoenix.controls.JFXDatePicker;
import models.Account;
import models.Expense;
import facadeOperations.FacadeOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

public class OverviewController implements Initializable {

    @FXML
    private PieChart chart;
    
    @FXML
    private JFXDatePicker datePicker;
    
    @FXML
    private Label accountBalance, income, spending, percentage;
    
    @FXML
    private MenuButton selectCategory, selectAccount;
    
    FacadeOperator database = FacadeOperator.getNameFactory();
    ArrayList<Account> accounts = new ArrayList<Account>();
    ArrayList<Expense> expenses = new ArrayList<Expense>();
    DecimalFormat formatter = new DecimalFormat("#,###.00");
    double totalSpending = 0.00;
    double totalIncome = 0.00;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeAccountsAndExpenses();		
		setAccountSum();
		setIncome();
		setSpending();
		setPercentage();
		setDonutChart();
	}

	private void setPercentage() {
		percentage.setText(formatter.format((0 - (totalSpending/totalIncome))*100) + "%");
		
	}

	private void setAccountSum() {
		double result = 0.00;
		for (Account ac : accounts) {
			result += ac.getCurrentBalance();
		}
		
		accountBalance.setText(formatter.format(result));
	}
	
	private void setIncome() {
		double result = 0.00;
		for (Expense e : expenses) {
			if (e.getCategory() == null) continue;
			if (e.getCategory().equalsIgnoreCase("Income")) result += e.getTransaction(); 
		}
		
		income.setText(formatter.format(result));
		totalIncome = result;
	}
	
	private void setSpending() {
		double result = 0.00;
		for (Expense e : expenses) {
			if (e.getCategory() == null) continue;
			if (!(e.getCategory().equalsIgnoreCase("Income"))) result += e.getTransaction(); 
		}
		
		spending.setText(formatter.format(result));
		totalSpending = result;
	}
	
	private void setDonutChart() {
		
		ArrayList<Double> cats = new ArrayList<Double>();
		
		double grocerySpending = 0.00;
		double transportationSpending = 0.00;
		double diningSpending = 0.00;
		double shoppingSpending = 0.00;
		double utilitiesSpending = 0.00;
		double entertainmentSpending = 0.00;
		double seasonalSpending = 0.00;
		
		for (Expense e : expenses) {
			String cat = e.getCategory();
			if (cat == null) continue;
			switch (cat) {
			case "Grocery"			: 	grocerySpending += e.getTransaction(); break;
			case "Transportation"	: 	transportationSpending += e.getTransaction(); break;
			case "Bill/Utility"		: 	utilitiesSpending += e.getTransaction(); break;
			case "Dining"			: 	diningSpending += e.getTransaction(); break;
			case "Shopping"			: 	shoppingSpending += e.getTransaction(); break;
			case "Entertainment"	: 	entertainmentSpending += e.getTransaction(); break;
			case "Seasonal"			: 	seasonalSpending += e.getTransaction(); break;
			default					:	break;
			}
		}
		
		cats.add(grocerySpending);
		cats.add(transportationSpending);
		cats.add(diningSpending);
		cats.add(shoppingSpending);
		cats.add(utilitiesSpending);
		cats.add(entertainmentSpending);
		cats.add(seasonalSpending);
		
		ArrayList<PieChart.Data> percents = getPercentages(cats);
		if (percents.isEmpty()) {
			chart.getData().addAll(new PieChart.Data("Future Spending", 100));
		} else {
			chart.getData().setAll(percents);
		}
		
	}

	private ArrayList<PieChart.Data> getPercentages(ArrayList<Double> cats) {
		ArrayList<PieChart.Data> result = new ArrayList<PieChart.Data>();
		
		if (cats.get(0) != 0.00) {
			result.add(new PieChart.Data("Grocery", (cats.get(0)/totalSpending) * 100));
		}
		
		if (cats.get(1) != 0.00) {
			result.add(new PieChart.Data("Transportation", (cats.get(1)/totalSpending) * 100));
		}
		
		if (cats.get(2) != 0.00) {
			result.add(new PieChart.Data("Dining", (cats.get(2)/totalSpending) * 100));
		}
		
		if (cats.get(3) != 0.00) {
			result.add(new PieChart.Data("Shopping", (cats.get(3)/totalSpending) * 100));
		}
		
		if (cats.get(4) != 0.00) {
			result.add(new PieChart.Data("Utilities & Housing", (cats.get(4)/totalSpending) * 100));
		}
		
		if (cats.get(5) != 0.00) {
			result.add(new PieChart.Data("Entertainment", (cats.get(5)/totalSpending) * 100));
		}
		
		if (cats.get(6) != 0.00) {
			result.add(new PieChart.Data("Seasonal", (cats.get(6)/totalSpending) * 100));
		}
		
		return result;
		
	}

	private void initializeAccountsAndExpenses() {
		ArrayList<Account> ac = new ArrayList<Account>();
		ArrayList<Expense> ex = new ArrayList<Expense>();
		try {
			ac = database.returnAccountObjects();
			ex = database.returnAllExpenseObjects();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ParseException p) {
			p.printStackTrace();
		}
		
		accounts = ac;
		expenses = ex;
	}
}

