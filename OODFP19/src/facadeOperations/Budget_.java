package facadeOperations;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class Budget_ {

	private JdbcConnectionSource conn = null;
	
	Budget_(JdbcConnectionSource con){
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
