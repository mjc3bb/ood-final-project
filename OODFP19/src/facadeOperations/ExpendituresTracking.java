package facadeOperations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

import models.*;


public class ExpendituresTracking {
	private static Connection connection;
	/*
	 *Tracks deductions from the users available money.
	 *Includes recurring deductions and any other expeditures. 
	 */
	public ExpendituresTracking(Connection conn) throws SQLException{
		connection = conn;
	}
	public static double calculateTotalExpenditures() {
		//Use Connection to execute query to calculate total expenditures.
		//These would be negative values from your balance
		return 0.0;
	}
	
	public static double calculateExpendituresDateRange() {
		return 0.0;
	}
}
