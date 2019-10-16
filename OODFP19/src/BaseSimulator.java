import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Checkbook;

public class BaseSimulator {

//	JdbcConnectionSource connectionSource;
	
	public static JdbcConnectionSource createNewDatabase(String fileName) {
		 
        String url = "jdbc:sqlite:sqlite/db/" + fileName;
 
        JdbcConnectionSource connectionSource;
		try {
			connectionSource = new JdbcConnectionSource(url);
	        TableUtils.createTableIfNotExists(connectionSource, Checkbook.class);
	        return connectionSource;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return null;
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
