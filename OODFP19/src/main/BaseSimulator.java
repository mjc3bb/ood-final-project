package main;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import facadeOperations.FacadeOperator;
import models.Account;
import models.Transaction;

public class BaseSimulator {
	
	public static JdbcConnectionSource createNewDatabase(String fileName) {
		 
        String url = "jdbc:sqlite:sqlite/db/" + fileName;
 
        JdbcConnectionSource connectionSource = null;
		try {
			connectionSource = new JdbcConnectionSource(url);
	        //TODO: This should be a factory pattern: Row.class -> Dao object
			//TableUtils.createTableIfNotExists(connectionSource, Checkbook.class);
			//Could this create a database from a text file? DatabaseFieldConfigLoader. http://ormlite.com/javadoc/ormlite-core/com/j256/ormlite/field/DatabaseFieldConfigLoader.html
			TableUtils.createTableIfNotExists(connectionSource, Transaction.class);
			TableUtils.createTableIfNotExists(connectionSource, Account.class);
			
			//Create a budget and test some functionality
			//TODO Implement all the methods so they work. It's gonna take some time.
			FacadeOperator f = new FacadeOperator(connectionSource);
			//TODO User input from application will be needed to create things for database, unless we make up the data here for now.
			f.createAccount(null, null, 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connectionSource;
    }
	
	public static void main(String[] args) {
		JdbcConnectionSource connectionSource = createNewDatabase("test.db");
		try {
			connectionSource.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
