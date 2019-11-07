package gui.accounts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccountsController {

    @FXML
    private MenuButton selectAccountActivity;

//    @FXML
//    private TableView<?> activityTable;
//
//    @FXML
//    private TableColumn<?, ?> date;
//
//    @FXML
//    private TableColumn<?, ?> location;
//
//    @FXML
//    private TableColumn<?, ?> typeActivity;
//
//    @FXML
//    private TableColumn<?, ?> amount;

    @FXML
    private Label accountActivity;

    @FXML
    private Label accountsHeader;

//    @FXML
//    private TableView<?> accountsTable;
//
//    @FXML
//    private TableColumn<?, ?> name;
//
//    @FXML
//    private TableColumn<?, ?> type;
//
//    @FXML
//    private TableColumn<?, ?> balance;

    @FXML
    private Label createNewAccountHeader;

    @FXML
    private JFXTextField createAccountName;

    @FXML
    private MenuButton createAccountType;

    @FXML
    private JFXTextField createAccountBalance;

    @FXML
    private JFXButton createSubmit;

    @FXML
    private Label addIncome;

    @FXML
    private MenuButton addIncomeSelectAcount;

    @FXML
    private JFXTextField addIncomeAmount;

    @FXML
    private JFXButton addSubmit;

}

