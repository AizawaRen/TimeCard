package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import model.LoginInfo;

public class LoginDAO {
	
	private Connection conn;
	private PreparedStatement pStmt;
	
	
	public void dbConnect() throws SQLException {
		ConnectDB cdb = new ConnectDB();
		conn = cdb.connect();
	}
	
	public Employee findByLogin(LoginInfo li){
	
		Employee emp = null;
		/*
		 * Connection conn = null;
		 * 
		 * try{ Class.forName("org.postgresql.Driver"); // } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); }
		 */
	
		try{
//			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			dbConnect();
			String sql = "SELECT * FROM employee WHERE enumber = ? AND pass = ?;";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, li.getNumber());
			pStmt.setString(2, li.getPass());
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				String number = rs.getString("enumber");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int yph = rs.getInt("yph");
				emp = new Employee(number, pass, name, yph);
			}
			
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		 return emp;
	 }
}
