package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttendanceDataDAO {

	private Connection conn;
	private PreparedStatement pStmt;
	DateTimeFormatter dFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	DateTimeFormatter tFormat = DateTimeFormatter.ofPattern("HH:mm");
	
	public void dbConnect() throws SQLException {
		ConnectDB cdb = new ConnectDB();
		conn = cdb.connect();
	}
	
	public boolean setStart(String enumber) {
		LocalDateTime now = LocalDateTime.now();
		
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				return false;
			} else {
				sql = "INSERT INTO attendance (enumber, work_date, start) VALUES (?, ?, ?);";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, enumber);
				pStmt.setString(2, now.format(dFormat));
				pStmt.setString(3, now.format(tFormat));
				pStmt.executeUpdate();
				return true;
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return false;
	}
	
	public boolean setFinish(String enumber) {
		LocalDateTime now = LocalDateTime.now();
		
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(!rs.next()) {
				return false;
			} else {
				sql = "UPDATE attendance SET finish = ? WHERE enumber = ? and  work_date = ?;";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, now.format(tFormat));
				pStmt.setString(2, enumber);
				pStmt.setString(3, now.format(dFormat));
				pStmt.executeUpdate();
				return true;
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return false;
	}
	
	public boolean setStartBreak(String enumber) {
		LocalDateTime now = LocalDateTime.now();
		
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(!rs.next()) {
				return false;
			} else {
				sql = "UPDATE attendance SET start_break = ? WHERE enumber = ? and work_date = ?;";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, now.format(tFormat));
				pStmt.setString(2, enumber);
				pStmt.setString(3, now.format(dFormat));
				pStmt.executeUpdate();
				return true;
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return false;
	}
	
	public boolean setFinishBreak(String enumber) {
		LocalDateTime now = LocalDateTime.now();
		
		try{
			dbConnect();		
			String sql = "SELECT * from attendance WHERE enumber = ? and work_date = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, enumber);
			pStmt.setString(2, now.format(dFormat));
			ResultSet rs = pStmt.executeQuery();
			
			if(!rs.next() && rs.getString(5) == null) {
				return false;
			} else {
				sql = "UPDATE attendance SET finish_break = ? WHERE enumber = ? and  work_date = ?;";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, now.format(tFormat));
				pStmt.setString(2, enumber);
				pStmt.setString(3, now.format(dFormat));
				pStmt.executeUpdate();
				return true;
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return false;
	}
}
