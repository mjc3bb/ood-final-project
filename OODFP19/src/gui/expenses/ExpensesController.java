package gui.expenses;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExpensesController {

//    @FXML
//    private TableView<Object> expensesTable;
//
//    @FXML
//    private TableColumn<Object, String> location;
//
//    @FXML
//    private TableColumn<Object, String> amount;
//
//    @FXML
//    private TableColumn<Object, String> date;
//
//    @FXML
//    private TableColumn<Object, String> account;
//
//    @FXML
//    private TableColumn<Object, String> recurring;

    @FXML
    private Label monthHeader;

    @FXML
    private Label addExpenseLabel;

    @FXML
    private JFXTextField expenseAmount;

    @FXML
    private JFXTextField expenseLocation;

    @FXML
    private MenuButton expenseAccount;

    @FXML
    private JFXDatePicker expenseDate;

    @FXML
    private MenuButton expenseCategory;

    @FXML
    private JFXButton submitExpense;

    @FXML
    private JFXCheckBox reccuringCheckbox;

    @FXML
    private JFXButton changeMonth;

    @FXML
    private MenuButton selectMonth;

    @FXML
    private MenuButton selectYear;

    @FXML
    private Label categoryHeader;

    @FXML
    private Label groceryCategory;

    @FXML
    private Label transportationCategory;

    @FXML
    private Label diningCategory;

    @FXML
    private Label shoppingCategory;

    @FXML
    private Label utilitiesCategory;

    @FXML
    private Label entertainmentCategory;

    @FXML
    private Label groceryAmount;

    @FXML
    private Label transportationAmount;

    @FXML
    private Label diningAmount;

    @FXML
    private Label shoppingAmount;

    @FXML
    private Label utilitiesAmount;

    @FXML
    private Label entertainmentAmount;

}
