package facadeOperations;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class Additon {

	private JdbcConnectionSource conn = null;
	
	Additon(JdbcConnectionSource con){
		this.conn = con;
	}
	
	public int addAllExpenses() {
		
		return -1;
	}
	
	public int addAllIncomes() {
		
		return -1;
	}
	
	public int addAllIncomesByDate() {
		
		return -1;
	}

	public int addAllExpensesByDate() {
	
		return -1;
	}
}
