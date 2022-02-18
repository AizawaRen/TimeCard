package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	private static final String JDBC_URL = "jdbc:postgresql://ec2-52-73-149-159.compute-1.amazonaws.com:5432/de3sgrkr4243o0?user=bqpygyzdwvkzvj&password=3fef9018117dca59473bac1c390b79236216cfa57b89d50aeadb3f80a05d51d1";
	private final static String DB_USER = "bqpygyzdwvkzvj";
	private final static String DB_PASS = "3fef9018117dca59473bac1c390b79236216cfa57b89d50aeadb3f80a05d51d1";
	
	public Connection connect() throws SQLException{
		
		try{
		    Class.forName("org.postgresql.Driver");
		    //
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	}
}
