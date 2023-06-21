package atm1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dataBasecon {
	private final String url = "jdbc:mysql://127.0.0.1:3306/java?user=root";
	private final String user = "root";
	private final String password = "Smhv543210@";

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
