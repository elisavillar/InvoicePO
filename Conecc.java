import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conecc {

	private static final String CONTROLLER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/invoicepo";
	private static final String USER = "root";
	private static final String PASS = "";

	static {
		try {
			Class.forName(CONTROLLER);
		} catch (ClassNotFoundException e) {
			System.out.println("Error while connecting the controller");
			e.printStackTrace();
		}
	}
	
	public Connection conect() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Connection OK");

		} catch (SQLException e) {
			System.out.println("Connection error");
			e.printStackTrace();
		}
		
		return con;
	}
}