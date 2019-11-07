package gui.overview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.chart.PieChart;

public class OverviewController implements Initializable {

    @FXML
    private PieChart chart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chart.getData().addAll(
	            new PieChart.Data("Travel", 13),
	            new PieChart.Data("Shopping", 25),
	            new PieChart.Data("Grocery", 10),
	            new PieChart.Data("Fast Food", 22),
	            new PieChart.Data("Entertainment", 30));
		
	}

}

