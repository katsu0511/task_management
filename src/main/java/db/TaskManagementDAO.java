package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskManagementDAO {
	private final String DSN = "jdbc:mysql://localhost:3306/task_management?useSSL=false";
	private final String USER = "katsuyaharada";
	private final String PASSWORD = "tkznemou19";
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースへ接続
			conn = DriverManager.getConnection(DSN, USER, PASSWORD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// Connection型変数が持つデータベースとJDBCリソースの解放
	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// PreparedStatement型変数が持つデータベースとJDBCリソースの解放
	public void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// ResultSet型変数が持つデータベースとJDBCリソースの解放
	public void close(ResultSet rset) {
		if (rset != null) {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
