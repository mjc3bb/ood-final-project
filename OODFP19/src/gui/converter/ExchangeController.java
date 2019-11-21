package gui.converter;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ExchangeController implements Initializable {
    
    @FXML
    private JFXButton Euros;
    
    @FXML
    private JFXButton Pounds;
    
    @FXML
    private JFXButton Peso;

    @FXML
    private JFXButton Yen;

    @FXML
    private Label resultLabel;

    @FXML
    private JFXTextField currencyInput;
    
    public void euros() {
    	String input = currencyInput.getText();
    	//change string to double
    	double usMoney = Double.parseDouble(input);
    	//create us money 
    	US us = new US(usMoney);
    	//create foreign money
    	MoneyAdapter euro = new MoneyChange(us,"Euros");
    	//get changed amount then convert to string
    	double d = euro.getCash();
    	//prints in resultBox
    	updateResult(input, "euros", String.valueOf(d));
    	
    }
    
    public void peso() {
    	//get the input in the inputlabel txt box
    	String input = currencyInput.getText();
    	//change string to double
    	double usMoney = Double.parseDouble(input);
    	//create us money 
    	US us = new US(usMoney);
    	//create foreign money
    	MoneyAdapter peso = new MoneyChange(us,"Peso");
    	//get changed amount then convert to string
    	double d = peso.getCash();
     	//prints in resultBox
    	updateResult(input, "pesos", String.valueOf(d));
    }
    
    public void pounds() {
    	//get the input in the inputlabel txt box
    	String input = currencyInput.getText();
    	//change string to double
    	double usMoney = Double.parseDouble(input);
    	//create us money 
    	US us = new US(usMoney);
    	//create foreign money
     	MoneyAdapter pound = new MoneyChange(us,"Pounds");
     	//get changed amout then convert to string
    	double d = pound.getCash();
     	//prints in resultBox
    	updateResult(input, "pounds", String.valueOf(d));
    }
    
    public void yen() {
    	String input = currencyInput.getText();
    	//change string to double
    	double usMoney = Double.parseDouble(input);
    	//create us money 
    	US us = new US(usMoney);
    	//create foreign money
    	MoneyAdapter yen = new MoneyChange(us,"Yen");
    	double d = yen.getCash();
     	//prints in resultBox
    	updateResult(input, "yen", String.valueOf(d));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		resultLabel.setVisible(false);
	}
	
	public void updateResult(String input, String exchange, String result) {
		resultLabel.setVisible(true);
		resultLabel.setText("$" + input + " converted into " + exchange + " is " + result);
	}
}