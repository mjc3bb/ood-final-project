import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ExchangeController {
    @FXML
    private Label conversionTypeLabel;
    
    @FXML
    private Button Euros;
    
    @FXML
    private Button Pounds;
    
    @FXML
    private Button Peso;

    @FXML
    private Button Yen;

    @FXML
    private TextField resultBox;

    @FXML
    private Label inputLabel;

    @FXML
    private TextField currencyInput;
    
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
    	resultBox.setText(String.valueOf(d));
    	
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
    	resultBox.setText(String.valueOf(d));
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
    	resultBox.setText(String.valueOf(d));
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
    	resultBox.setText(String.valueOf(d));
    }
}