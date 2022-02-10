package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import model.LoginInfo;

public class EmployeeDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/timecard";
	private final String DB_USER = "ren";
	private final String DB_PASS = "92341013";
	
	public Employee findByLogin(LoginInfo li){
	
		Employee emp = null;
		
		try{
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    //
		}
		catch (ClassNotFoundException e) {
		    //
		}
	
		try{
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			
			String sql = "SELECT * FROM employee WHERE enumber = ? AND pass = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, li.getNumber());
			pStmt.setString(2, li.getPass());
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				String number = rs.getString("enumber");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				emp = new Employee(number, pass, name);
			}
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		
		 return emp;
	 }
}	