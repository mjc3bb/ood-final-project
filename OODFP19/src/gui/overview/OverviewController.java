package gui.overview;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXDatePicker;
import models.Account;
import facadeOperations.FacadeOperator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.chart.PieChart;

public class OverviewController implements Initializable {

    @FXML
    private PieChart chart;
    
    @FXML
    private JFXDatePicker datePicker;
    
    @FXML
    private Label accountBalance, income, spending;
    
    FacadeOperator database = FacadeOperator.getNameFactory();
    ArrayList<Account> accounts = new ArrayList<Account>();
    DecimalFormat formatter = new DecimalFormat("#,###.00");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chart.getData().addAll(
	            new PieChart.Data("Travel", 13),
	            new PieChart.Data("Shopping", 25),
	            new PieChart.Data("Grocery", 10),
	            new PieChart.Data("Fast Food", 22),
	            new PieChart.Data("Entertainment", 30)
	            );
		initializeAccounts();		
		accountBalance.setText(formatter.format(getAccountSum()));
	}

	private double getAccountSum() {
		double result = 0;
		for (Account ac : accounts) {
			result += ac.getCurrentBalance();
		}
		return result;
	}

	private void initializeAccounts() {
		ArrayList<Account> temp = new ArrayList<Account>();
		try {
			temp = database.returnAccountObjects();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		accounts = temp;
		
	}
	
	

}

