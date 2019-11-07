package facadeOperations;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class BudgetOperator {

	private JdbcConnectionSource conn = null;
	
	public BudgetOperator(JdbcConnectionSource con){
		this.conn = con;
	}
	
	public String DisplayHopefulBudget() {
		
		return null;
	}
	
	public String DisplayActualBudget() {
			
		return null;
	}
	
	public void CreateBudget() {
		//Creates a new budget
	}
	
	public void EditBudget() {
		//Edits an existing budget
	}
}
