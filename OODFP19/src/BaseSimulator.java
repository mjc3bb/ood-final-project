import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Checkbook;

public class BaseSimulator {

	public static JdbcConnectionSource createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:sqlite/db/" + fileName;

        JdbcConnectionSource connectionSource = null;
		try {
			connectionSource = new JdbcConnectionSource(url);
	        //TODO: This should be a factory pattern: Row.class -> Dao object
			TableUtils.createTableIfNotExists(connectionSource, Checkbook.class);
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

		LaunchInterface.execute(args);
	}

}
