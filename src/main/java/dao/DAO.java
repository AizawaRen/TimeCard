package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import model.LoginInfo;

public class DAO {
	private final String JDBC_URL = "jdbc:postgres://bqpygyzdwvkzvj:3fef9018117dca59473bac1c390b79236216cfa57b89d50aeadb3f80a05d51d1@ec2-52-73-149-159.compute-1.amazonaws.com:5432/de3sgrkr4243o0";
	private final String DB_USER = "bqpygyzdwvkzvj";
	private final String DB_PASS = "3fef9018117dca59473bac1c390b79236216cfa57b89d50aeadb3f80a05d51d1";
	
	public Employee findByLogin(LoginInfo li){
	
		Employee emp = null;
		
		try{
		    Class.forName("org.postgresql.Driver");
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
