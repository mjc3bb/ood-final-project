package facadeOperations;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import models.Account;
import models.Expense;

public class UpdateQueryOperator {
	private JdbcConnectionSource conn = null;
	private Dao<Expense,String> transactionDao = null;
	private Dao<Account,String> accountDao = null;

	public UpdateQueryOperator(JdbcConnectionSource con) throws SQLException{
		this.conn = con;
		transactionDao = DaoManager.createDao(conn, Expense.class);
		accountDao = DaoManager.createDao(conn, Account.class);
	}

	public void addTransaction(double amount, Date date, String location, String accountName, String category, boolean recurring) throws SQLException, ParseException {
		List<Account> acc = accountDao.queryBuilder().where().eq("accountName", accountName).query();
		Expense t = new Expense(0.00-amount, date, location, acc.get(0), category, recurring);
		transactionDao.createOrUpdate(t);
		long balance = transactionDao.queryRawValue("select a.currentBalance from account a where a.accountName = \"" + accountName + "\"");
		UpdateBuilder<Account, String> updateBuilder = accountDao.updateBuilder();
		updateBuilder.updateColumnValue("currentBalance", balance - amount).where().eq("accountName", accountName);
		updateBuilder.update();
	}

	public void addAccount(String AccountName, String accountType, double accountBalance) throws SQLException {
		Account a = new Account(AccountName, accountType, accountBalance);
		accountDao.createOrUpdate(a);
	}

	public void removeAccount(Account account) throws SQLException{
		accountDao.delete(account);
	}

	public void addIncome(String accountName, double amount, Account account) throws SQLException {
		// TODO Should do the same as addTransaction, but will have null value for location and have some defaults for other attributes
		long balance = transactionDao.queryRawValue("select a.currentBalance from account a where a.accountName = \"" + accountName + "\"");
		UpdateBuilder<Account, String> updateBuilder = accountDao.updateBuilder();
		updateBuilder.updateColumnValue("currentBalance", balance + amount).where().eq("accountName", accountName);
		updateBuilder.update();
		Date date = java.sql.Date.valueOf(LocalDate.now());
		transactionDao.createOrUpdate(new Expense(amount, date, "N/A", account, "Income", false));
	}
	
	
}
