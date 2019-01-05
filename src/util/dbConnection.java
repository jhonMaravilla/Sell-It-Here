package util;
import java.sql.*;
public class dbConnection {
	private static Connection conn = null;
	private static String classConn = "com.mysql.jdbc.Driver";
	private static String dbPath = "jdbc:mysql://localhost:3306/sellithere";
	private static String user = "root";
	private static String pass = "sHONTELLEATALIA.1";
	
	public static Connection getConnection() {
		try {
			Class.forName(classConn);
			conn = DriverManager.getConnection(dbPath, user, pass);
		} catch(ClassNotFoundException e ) {
			e.getMessage();
		} catch(SQLException e) {
			e.getMessage();
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch(Exception e) {
			e.getMessage();
		}
	}
}
